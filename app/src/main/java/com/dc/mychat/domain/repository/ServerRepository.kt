package com.dc.mychat.domain.repository

import com.dc.mychat.domain.model.Profile
import com.dc.mychat.ui.viewmodel.MainViewModel

interface ServerRepository {

    fun createProfile(mainViewModel: MainViewModel)

    fun getAllProfile() : List<Profile>

}