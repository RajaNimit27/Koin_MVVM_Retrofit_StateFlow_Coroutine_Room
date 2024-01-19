package com.app.koin_mvvm_retrofit_flow_room.data

import android.content.Context
import com.app.koin_mvvm_retrofit_flow_room.utils.UiState

import com.app.koin_mvvm_retrofit_flow_room.utils.Utils
import kotlin.reflect.full.memberProperties

class ApiResultHandler<T>(private val context: Context,  private val onLoading: () -> Unit, private val onSuccess: (T?) -> Unit, private val onFailure: () -> Unit) {

    fun handleApiResult(result: UiState<T>) {
        when (result) {
           is UiState.Loading -> { onLoading() }
            is UiState.Success -> { onSuccess(result.data) }
            is UiState.Error -> { onFailure()
                result.data?.let { Utils.showAlertDialog(context, it.toString()) }
            }
        }
    }

    @Throws(IllegalAccessException::class, ClassCastException::class)
    inline fun <reified T> Any.getField(fieldName: String): T? {
        this::class.memberProperties.forEach { kCallable ->
            if (fieldName == kCallable.name) {
                return kCallable.getter.call(this) as T?
            }
        }
        return null
    }

}
