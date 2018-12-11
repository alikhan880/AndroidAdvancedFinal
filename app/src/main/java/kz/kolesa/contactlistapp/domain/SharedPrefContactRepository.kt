package kz.kolesa.contactlistapp.domain

import android.content.SharedPreferences
import com.google.gson.Gson
import kz.kolesa.contactlistapp.domain.model.Contact
import kz.kolesa.contactlistapp.domain.model.ContactGroup

/**
 * Created by Alikhan on 12/11/2018.
 */

private const val GROUP_POSTFIX = "_GROUP"
private const val CONTACT_POSTFIX = "_CONTACT"

class SharedPrefContactRepository(
    private val sharedPrefs: SharedPreferences
) : ContactRepository {

    override suspend fun getAllContacts(): Response<ArrayList<MappedContact>> {
        val allEntries = sharedPrefs.all
        val result = arrayListOf<MappedContact>()
        allEntries.keys.forEach{key ->
            if (key.contains(CONTACT_POSTFIX)) {
                val json = sharedPrefs.getString(key, "")
                val contact = Gson().fromJson(json, Contact::class.java)
                val groupJson = sharedPrefs.getString(contact.contactGroup.toString().plus(GROUP_POSTFIX), "")
                val group = Gson().fromJson(groupJson, ContactGroup::class.java)

                result.add(MappedContact(contact, group))
            }
        }

        return Response(result)
    }

    override suspend fun getContact(id: Int): Response<MappedContact?> {
        val json = sharedPrefs.getString(id.toString().plus(CONTACT_POSTFIX), "")

        val contact = Gson().fromJson(json, Contact::class.java)
        val groupJson = sharedPrefs.getString(contact.contactGroup.toString().plus(GROUP_POSTFIX), "")
        val group = Gson().fromJson(groupJson, ContactGroup::class.java)

        return Response(MappedContact(contact, group))
    }

    override fun addContact(contact: Contact) {
        val json = Gson().toJson(contact)
        sharedPrefs.edit().putString(contact.id.toString().plus(CONTACT_POSTFIX), json).apply()
    }
}
