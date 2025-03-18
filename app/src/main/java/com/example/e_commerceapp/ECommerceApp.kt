package com.example.e_commerceapp

import android.app.Application
import com.example.e_commerceapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ECommerceApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ECommerceApp)
            androidLogger()
            modules(appModule)
        }
    }
}