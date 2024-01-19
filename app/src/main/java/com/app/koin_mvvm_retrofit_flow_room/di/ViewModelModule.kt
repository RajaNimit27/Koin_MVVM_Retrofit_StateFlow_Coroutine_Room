package com.app.koin_mvvm_retrofit_flow_room.di

import com.app.koin_mvvm_retrofit_flow_room.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule= module {
    viewModel{MainViewModel(get(),get(),get())}
}


