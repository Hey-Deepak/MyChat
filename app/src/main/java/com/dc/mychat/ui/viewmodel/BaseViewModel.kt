package com.dc.mychat.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineExceptionHandler

open class BaseViewModel: ViewModel() {

    val loadingState = mutableStateOf(false)
    val showErrorState = mutableStateOf(false)
    val showErrorMessageState = mutableStateOf("")
    val showToastMessageState = mutableStateOf("")
    val showToastState = mutableStateOf(false)

    protected val exceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        loadingState.value = false
        showErrorState.value = true
        showErrorMessageState.value = throwable.message.toString()
    }

    protected fun showToast(message:String){
        showToastState.value = true
        showToastMessageState.value = message
    }
}