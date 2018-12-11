package kz.kolesa.contactlistapp.domain.database

import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * Created by Alikhan on 12/11/2018.
 */
abstract class ContactsDatabase : RoomDatabase() {

    abstract fun contactDataDao(): ContactDao

    companion object {

        fun createInstance(context: Context): ContactsDatabase {

            return Room.databaseBuilder(
                context,
                ContactsDatabase::class.java,
                "contacts.db"
            ).build()
        }
    }
}