package kz.kolesa.contactlistapp.domain.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import kz.kolesa.contactlistapp.domain.model.Contact
import kz.kolesa.contactlistapp.domain.model.ContactGroup

/**
 * Created by Alikhan on 12/11/2018.
 */
@Dao
interface ContactDao {

    @Insert
    fun insert(contact: Contact): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllGroups(vararg groups: ContactGroup)

    @Query("select * from contact")
    fun getAll(): List<Contact>

    @Query("select * from contact where id = :contactId")
    fun getContact(contactId: Int): Contact

    @Query("select * from contactGroup where id = :groupId")
    fun getContactGroup(groupId: Int): ContactGroup
}