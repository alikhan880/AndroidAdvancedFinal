package kz.kolesa.contactlistapp.view

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kz.kolesa.contactlistapp.R
import kz.kolesa.contactlistapp.domain.MappedContact
import org.koin.android.architecture.ext.viewModel

class ContactDetailActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var tvName: TextView
    private lateinit var tvPhone: TextView
    private lateinit var tvGroupName: TextView
    private lateinit var tvPriority: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)
        bindViews()
        mainViewModel.contactLiveData.observe(this, Observer {
            it ?: return@Observer

            showData(it)
        })
        mainViewModel.getContact(intent.getIntExtra("id", 0))
    }

    private fun bindViews() {
        tvName = findViewById(R.id.tv_name)
        tvPhone = findViewById(R.id.tv_phone)
        tvGroupName = findViewById(R.id.tv_group_name)
        tvPriority = findViewById(R.id.tv_priority)
    }

    private fun showData(it: MappedContact) {
        tvName.text = it.contact.name
        tvPhone.text = it.contact.mPhone
        tvGroupName.text = it.contactGroup.name
        tvPriority.text = it.contactGroup.priority.toString()
    }
}
