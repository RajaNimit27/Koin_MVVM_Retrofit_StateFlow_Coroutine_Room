package com.app.koin_mvvm_retrofit_flow_room.di

import com.app.koin_mvvm_retrofit_flow_room.data.Repository
import com.app.koin_mvvm_retrofit_flow_room.data.remote.RemoteDataSource
import org.koin.dsl.module


val repositoryModule = module {
    factory {  Repository(get()) }
}