package com.vintech.chat

import android.app.Application
import com.vintech.chat.ui.inject.appModule
import com.vintech.chat.ui.screens.home.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class ChatApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ChatApplication)
            modules(appModule)
        }

    }
}