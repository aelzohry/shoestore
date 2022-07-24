package com.udacity.aelzohry.shoestore

import android.app.Application
import timber.log.Timber

class ShoeStoreApp: Application() {

    override fun onCreate() {
        super.onCreate()

        // Setup Logging Library
        Timber.plant(Timber.DebugTree())
    }
}