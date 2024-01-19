package com.app.koin_mvvm_retrofit_flow_room

import android.app.Application
import com.app.koin_mvvm_retrofit_flow_room.di.dataBaseModule
import com.app.koin_mvvm_retrofit_flow_room.di.networkModule
import com.app.koin_mvvm_retrofit_flow_room.di.remoteDataSourceModule
import com.app.koin_mvvm_retrofit_flow_room.di.repositoryModule
import com.app.koin_mvvm_retrofit_flow_room.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            androidLogger()
            modules(networkModule, remoteDataSourceModule, repositoryModule, viewModelModule,
                dataBaseModule)
        }
    }
}
