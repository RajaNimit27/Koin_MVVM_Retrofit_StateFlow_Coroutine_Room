package com.app.koin_mvvm_retrofit_flow_room.di

import com.app.koin_mvvm_retrofit_flow_room.data.remote.RemoteDataSource
import org.koin.dsl.module

val remoteDataSourceModule= module {
    factory {  RemoteDataSource(get()) }
}
