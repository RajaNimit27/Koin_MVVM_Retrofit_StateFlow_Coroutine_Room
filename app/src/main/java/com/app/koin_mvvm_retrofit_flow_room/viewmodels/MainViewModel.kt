package com.app.koin_mvvm_retrofit_flow_room.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.koin_mvvm_retrofit_flow_room.data.Post
import com.app.koin_mvvm_retrofit_flow_room.data.Repository
import com.app.koin_mvvm_retrofit_flow_room.db.PostDao
import com.app.koin_mvvm_retrofit_flow_room.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository, application: Application, private val dao: PostDao): BaseViewModel(application) {


    val _uiStatePostList = MutableStateFlow<UiState<List<Post>>>(UiState.Loading)
    val uiStatePostList: StateFlow<UiState<List<Post>>> = _uiStatePostList
    val _dbPosts = MutableLiveData<List<Post>>()

    fun getPostsList() = viewModelScope.launch {
        repository.getPostList(context).collect {
            when (it) {
                is UiState.Success -> {
                    _uiStatePostList.value = UiState.Success(it.data)
                }
                is UiState.Loading -> {
                    _uiStatePostList.value = UiState.Loading
                }
                is UiState.Error -> {
                    //Handle Error
                    _uiStatePostList.value = UiState.Error(it.data,it.message)
                }
            }
        }
    }

    // insert into room
    fun insertAllPostIntoDb(posts: List<Post>) = viewModelScope.launch {
        dao.insertAllPost(posts)
    }

    //get data from db
    fun getAllPostsFromDb() = viewModelScope.launch {
        dao.getPost().collect { values ->
            _dbPosts.value = values
        }
    }

}