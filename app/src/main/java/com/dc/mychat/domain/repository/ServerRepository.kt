package com.dc.mychat.domain.repository

import com.dc.mychat.domain.model.Profile
import com.dc.mychat.ui.viewmodel.MainViewModel

interface ServerRepository {

    suspend fun createProfile(mainViewModel: MainViewModel)

    suspend fun getAllProfile(mainViewModel: MainViewModel)

    fun getProfile(name: String) : Profile

}