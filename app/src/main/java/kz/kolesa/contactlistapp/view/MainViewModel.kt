package kz.kolesa.contactlistapp.view

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import kz.kolesa.contactlistapp.domain.ContactRepository
import kz.kolesa.contactlistapp.domain.MappedContact
import kz.kolesa.contactlistapp.domain.model.Contact

/**
 * Created by Alikhan on 12/11/2018.
 */
class MainViewModel(
    private val contactRepository: ContactRepository
) : ViewModel() {

    val contactsLiveData = MutableLiveData<ArrayList<MappedContact>>().apply {
        this.value ?: getAllContacts()
        emptyList<Contact>()
    }
    val contactLiveData = MutableLiveData<MappedContact>()

    fun getContact(id: Int) {
        GlobalScope.launch(Dispatchers.Main) {
            val result = async(Dispatchers.IO) {
                contactRepository.getContact(id)
            }.await()
            if (result.isSuccess) {
                contactLiveData.value = result.result
            }
        }
    }

    fun getContactList(): ArrayList<MappedContact> = contactsLiveData.value ?: arrayListOf<MappedContact>()

    fun getAllContacts() {
        GlobalScope.launch(Dispatchers.Main) {
            val result = async(Dispatchers.IO) {
                contactRepository.getAllContacts()
            }.await()
            if (result.isSuccess) {
                contactsLiveData.value = result.result
            }
        }
    }
}