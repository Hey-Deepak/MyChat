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
) : BaseViewModel(){

    var allUsersState = mutableStateOf(listOf(Profile()))

    fun getAllProfileFromFirebase() {
        viewModelScope.launch(exceptionHandler) {
            loadingState.value = true
            allUsersState.value = serverRepository.getAllProfile()
            loadingState.value = false
        }
    }
}