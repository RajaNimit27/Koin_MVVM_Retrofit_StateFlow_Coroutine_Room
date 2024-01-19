package com.app.koin_mvvm_retrofit_flow_room.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel


open class BaseViewModel(application: Application) : AndroidViewModel(application) {
  protected val context
    get() = getApplication<Application>()
}