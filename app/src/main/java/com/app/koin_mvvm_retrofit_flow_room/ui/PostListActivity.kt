package com.app.koin_mvvm_retrofit_flow_room.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.app.koin_mvvm_retrofit_flow_room.R
import com.app.koin_mvvm_retrofit_flow_room.databinding.ActivityPostListBinding
import com.app.koin_mvvm_retrofit_flow_room.data.ApiResultHandler
import com.app.koin_mvvm_retrofit_flow_room.data.Post
import com.app.koin_mvvm_retrofit_flow_room.viewmodels.MainViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class PostListActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by inject()
    lateinit var activityMainBinding: ActivityPostListBinding
    lateinit var postListAdapter: PostListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this@PostListActivity, R.layout.activity_post_list)
        init()
        getPostsFromDB()
        observePostDBData()
    }

    private fun init(){
        try {
            postListAdapter = PostListAdapter()
            activityMainBinding.list.apply { adapter= postListAdapter }
            activityMainBinding.swipeRefreshLayout.setOnRefreshListener {
                getPostsAPI()
            }
        } catch (e: Exception) {
            e.stackTrace
        }
    }

    private fun showProgress(isShown:Boolean)= if(isShown) activityMainBinding.progress.visibility = View.VISIBLE else activityMainBinding.progress.visibility = View.GONE

    private fun isSwipeRefreshing(isRefreshing: Boolean) {
        activityMainBinding.swipeRefreshLayout.isRefreshing = isRefreshing
    }

    private fun getPostsAPI() {
        mainViewModel.getPostsList()
        try {
            lifecycleScope.launch {
                mainViewModel.uiStatePostList.collect {
                    val apiResultHandler = ApiResultHandler<List<Post>>(this@PostListActivity,
                        onLoading = {
                            showProgress(true)
                        },
                        onSuccess = { data ->
                            showProgress(false)
                            isSwipeRefreshing(false)
                            data?.let {
                                mainViewModel.insertAllPostIntoDb(it)
                            }
                            getPostsFromDB()
                        },
                        onFailure = {
                            showProgress(false)
                            isSwipeRefreshing(false)
                        })
                    apiResultHandler.handleApiResult(it)
                }
            }
        }catch (e:Exception){
            e.stackTrace
        }
    }

    private fun getPostsFromDB() {
        mainViewModel.getAllPostsFromDb()
    }

    private fun observePostDBData() {
        try {
            mainViewModel._dbPosts.observe(this) { data ->
                if(data.isNotEmpty()){
                    data?.let { postListAdapter.setPosts(it) }
                }else{
                    getPostsAPI()
                }
            }
        } catch (e: Exception) {
            e.stackTrace
        }
    }
}