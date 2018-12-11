package kz.kolesa.contactlistapp.domain

import android.database.SQLException
import kz.kolesa.contactlistapp.domain.database.ContactsDatabase
import kz.kolesa.contactlistapp.domain.model.Contact

/**
 * Created by Alikhan on 12/11/2018.
 */
class DefaultContactRepository(
    private val contactsDatabase: ContactsDatabase
) : ContactRepository {
    override suspend fun getAllContacts(): Response<List<MappedContact>> {
        return try {
            val result = arrayListOf<MappedContact>()
            val contactList = contactsDatabase.contactDataDao().getAll()
            contactList.forEach { contact ->
                val group = contactsDatabase.contactDataDao().getContactGroup(contact.contactGroup)
                result.add(MappedContact(contact, group))
            }

            Response(result)
        } catch (e: SQLException) {

            Response(e)
        }

    }

    override suspend fun getContact(id: Int): Response<MappedContact?> {
        return try {
            val contact = contactsDatabase.contactDataDao().getContact(id)
            val group = contactsDatabase.contactDataDao().getContactGroup(contact.contactGroup)

            Response(MappedContact(contact, group))
        } catch (e: SQLException) {

            Response(e)
        }
    }

    override fun addContact(contact: Contact) {
        return try {
            val result = contactsDatabase.contactDataDao().insert(contact)

        } catch (e: SQLException) {

        }
    }
}