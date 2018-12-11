package kz.kolesa.contactlistapp.view

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.google.gson.Gson
import kz.kolesa.contactlistapp.R
import kz.kolesa.contactlistapp.domain.MappedContact
import kz.kolesa.contactlistapp.domain.model.ContactGroup
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.inject

private const val GROUP_POSTFIX = "_GROUP"

class MainActivity : AppCompatActivity(), ItemClickListener {

    private val mainViewModel: MainViewModel by viewModel()
    private val sharedPrefs: SharedPreferences by inject()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MainAdapter
    private lateinit var fab: FloatingActionButton
    private val predefinedGroups = arrayOf(
        ContactGroup(1, "Group1", 1),
        ContactGroup(2, "Group2", 2),
        ContactGroup(3, "Group3", 3)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindViews()
        writePredefinedData()
        observeViewModel()
        initAdapter()
    }

    override fun onItemClick(pos: Int) {
        //todo open detail activity
    }

    private fun writePredefinedData() {
        predefinedGroups.forEach {
            val json = Gson().toJson(it)

            sharedPrefs.edit().putString(it.id.toString().plus(GROUP_POSTFIX), json).apply()
        }
    }

    private fun bindViews() {
        recyclerView = findViewById(R.id.activity_main_recycler_view)
        fab = findViewById(R.id.fab)
        fab.setOnClickListener { startActivity(Intent(this, AddContactActivity::class.java)) }
    }

    private fun observeViewModel() {
        mainViewModel.contactsLiveData.observe(this, Observer { onDataLoaded(it) })
    }

    private fun initAdapter() {
        adapter = MainAdapter(mainViewModel.getContactList(), this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    private fun onDataLoaded(list: List<MappedContact>?) {
        list ?: return

        adapter.setList(list)
    }
}
