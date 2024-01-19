package com.app.koin_mvvm_retrofit_flow_room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.koin_mvvm_retrofit_flow_room.data.Post

@Database(entities = [(Post::class)], version = 1)
abstract class PostDataBase :RoomDatabase() {
    abstract fun getPostDao() : PostDao
}