package com.vintech.chat.ui.inject

import com.vintech.chat.ui.screens.home.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { com.vintech.data.usecase.MainUseCase() }
    viewModel { MainViewModel(get()) }
}