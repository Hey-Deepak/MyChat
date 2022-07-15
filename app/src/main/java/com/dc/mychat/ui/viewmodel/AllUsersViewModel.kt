package com.dc.mychat.ui.viewmodel


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dc.mychat.domain.model.Profile
import com.dc.mychat.domain.repository.ServerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllUsersViewModel @Inject constructor(
    private val serverRepository: ServerRepository
) : ViewModel(){

    val showErrorState = mutableStateOf(false)
    val showErrorMessageState = mutableStateOf("")
    var allUsersState = mutableStateOf(listOf(Profile()))
    val loadingState = mutableStateOf(false)

    private val allUserExceptionHandler = CoroutineExceptionHandler{ _, throwable ->
        loadingState.value = false
        showErrorState.value = true
        showErrorMessageState.value = throwable.message.toString()
    }

    fun getAllProfileFromFirebase() {
        viewModelScope.launch(allUserExceptionHandler) {
            loadingState.value = true
            allUsersState.value = serverRepository.getAllProfile()
            loadingState.value = false
        }
    }


}