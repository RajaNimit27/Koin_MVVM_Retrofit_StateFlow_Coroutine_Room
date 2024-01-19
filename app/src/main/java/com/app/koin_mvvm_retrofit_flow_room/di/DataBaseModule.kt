package com.app.koin_mvvm_retrofit_flow_room.di

import android.app.Application
import androidx.room.Room
import com.app.koin_mvvm_retrofit_flow_room.db.PostDao
import com.app.koin_mvvm_retrofit_flow_room.db.PostDataBase
import com.app.koin_mvvm_retrofit_flow_room.viewmodels.MainViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.concurrent.TimeUnit


fun provideDataBase(application: Application): PostDataBase =
     Room.databaseBuilder(
        application,
        PostDataBase::class.java,
        "table_post"
    ).
     fallbackToDestructiveMigration().build()

fun provideDao(postDataBase: PostDataBase): PostDao = postDataBase.getPostDao()


val dataBaseModule= module {
    single { provideDataBase(get()) }
    single { provideDao(get()) }
}


