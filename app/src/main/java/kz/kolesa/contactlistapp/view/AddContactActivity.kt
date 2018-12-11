package kz.kolesa.contactlistapp.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import kz.kolesa.contactlistapp.R
import kz.kolesa.contactlistapp.domain.ContactRepository
import kz.kolesa.contactlistapp.domain.model.Contact
import org.koin.android.ext.android.inject
import java.util.*

class AddContactActivity : AppCompatActivity() {

    private val contactRepository: ContactRepository by inject()
    private lateinit var etName: EditText
    private lateinit var etmPhone: EditText
    private lateinit var ethPhone: EditText
    private lateinit var etwPhone: EditText
    private lateinit var buttonSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)
        bindViews()
    }

    private fun bindViews() {
        etName = findViewById(R.id.et_name)
        etmPhone = findViewById(R.id.et_mphone)
        ethPhone = findViewById(R.id.et_hphone)
        etwPhone = findViewById(R.id.et_wphone)
        buttonSave = findViewById(R.id.btn_save)
        buttonSave.setOnClickListener { saveData() }
    }

    private fun saveData() {
        val name = etName.text.toString().trim()
        val mPhone = etmPhone.text.toString().trim()
        val hPhone = ethPhone.text.toString().trim()
        val wPhone = etwPhone.text.toString().trim()
        contactRepository.addContact(Contact(Random().nextInt(), name, mPhone, hPhone, wPhone, "", Random().nextInt(3)))
        finish()
    }
}
