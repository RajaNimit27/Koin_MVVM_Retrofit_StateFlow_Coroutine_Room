package com.app.koin_mvvm_retrofit_flow_room.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.koin_mvvm_retrofit_flow_room.data.Post
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPost(post: List<Post>)

    @Query("SELECT * FROM table_post")
    fun getPost(): Flow<List<Post>>
}