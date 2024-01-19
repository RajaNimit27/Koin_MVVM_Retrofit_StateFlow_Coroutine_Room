package com.app.koin_mvvm_retrofit_flow_room.data.remote

import com.app.koin_mvvm_retrofit_flow_room.data.Post
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>
}