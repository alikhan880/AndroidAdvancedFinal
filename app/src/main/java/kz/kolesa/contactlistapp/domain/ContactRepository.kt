package kz.kolesa.contactlistapp.domain

import kz.kolesa.contactlistapp.domain.model.Contact

/**
 * Created by Alikhan on 12/11/2018.
 */
interface ContactRepository {

    suspend fun getAllContacts(): Response<List<MappedContact>>

    suspend fun getContact(id: Int): Response<MappedContact?>

    fun addContact(contact: Contact)
}