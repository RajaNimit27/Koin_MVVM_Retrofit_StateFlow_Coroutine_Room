package com.app.koin_mvvm_retrofit_flow_room.data

import android.content.Context
import com.app.koin_mvvm_retrofit_flow_room.data.remote.RemoteDataSource
import com.app.koin_mvvm_retrofit_flow_room.data.remote.toResultFlow
import com.app.koin_mvvm_retrofit_flow_room.utils.UiState
import kotlinx.coroutines.flow.Flow

class Repository(private val remoteDataSource: RemoteDataSource) {

    suspend fun getPostList(context: Context): Flow<UiState<List<Post>>> {
        return toResultFlow(context){
            remoteDataSource.getPosts()
        }
    }

}