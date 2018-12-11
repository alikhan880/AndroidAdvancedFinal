package kz.kolesa.contactlistapp.view

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import kz.kolesa.contactlistapp.domain.model.Contact
import kz.kolesa.contactlistapp.domain.ContactRepository
import kz.kolesa.contactlistapp.domain.MappedContact

/**
 * Created by Alikhan on 12/11/2018.
 */
class MainViewModel(
    private val contactRepository: ContactRepository
) : ViewModel() {

    val contactsLiveData = MutableLiveData<List<MappedContact>>().apply {
        this.value ?: getAllContacts()
        emptyList<Contact>()
    }

    fun getContactList(): List<MappedContact> = contactsLiveData.value ?: emptyList()

    private fun getAllContacts() {

    }
}