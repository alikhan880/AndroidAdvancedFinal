package kz.kolesa.contactlistapp

import android.arch.persistence.room.Room
import android.content.Context
import kz.kolesa.contactlistapp.domain.ContactRepository
import kz.kolesa.contactlistapp.domain.DefaultContactRepository
import kz.kolesa.contactlistapp.domain.SharedPrefContactRepository
import kz.kolesa.contactlistapp.domain.database.ContactsDatabase
import kz.kolesa.contactlistapp.view.MainViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

/**
 * Created by Alikhan on 12/11/2018.
 */
val contactModule: Module = applicationContext {
    viewModel { MainViewModel(contactRepository = get()) }
    bean { androidApplication() }
    bean { ContactsDatabase.createInstance(context = get()) }
//    bean {
//        DefaultContactRepository(
//            contactsDatabase = get()
//        ) as ContactRepository
//    }
    bean {
        SharedPrefContactRepository(sharedPrefs = get()) as ContactRepository
    }
    bean {
        androidApplication().getSharedPreferences("db", Context.MODE_PRIVATE)
    }
}