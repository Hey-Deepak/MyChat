package com.dc.mychat.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dc.mychat.domain.model.Message
import com.dc.mychat.domain.model.Profile
import com.dc.mychat.domain.repository.ServerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllUsersViewModel @Inject constructor(
    private val serverRepository: ServerRepository
) : ViewModel(){

    var allUsersState = mutableStateOf(listOf(Profile()))
    val loadingState = mutableStateOf(false)

    fun getAllProfileFromFirebase() {
        viewModelScope.launch {
            loadingState.value = true
            allUsersState.value = serverRepository.getAllProfile()
            loadingState.value = false
        }
    }


}