package com.example.dioinovationmvvm.application

import android.app.Application
import com.example.dioinovationmvvm.di.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TaskApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TaskApplication)
            modules(mainModule)
        }
    }
}