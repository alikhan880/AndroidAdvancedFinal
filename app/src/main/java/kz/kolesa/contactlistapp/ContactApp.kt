package kz.kolesa.contactlistapp

import android.app.Application
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.Module
import java.util.*

/**
 * Created by Alikhan on 12/11/2018.
 */
class ContactApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoinDi()
    }

    private fun initKoinDi() {
        val diModules = Arrays.asList<Module>(contactModule)
        startKoin(this, diModules)
    }
}