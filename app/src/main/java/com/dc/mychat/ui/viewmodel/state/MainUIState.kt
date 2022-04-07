package com.dc.mychat.ui.viewmodel.state

import com.dc.mychat.domain.model.Message

sealed class MainUIState {
    data class LoggedIn(val emailId: String) : MainUIState()
    data class Profile(val profile: com.dc.mychat.domain.model.Profile) : MainUIState()
    data class AllUsers(val listOfUsers: List<com.dc.mychat.domain.model.Profile>) : MainUIState()
    data class NewMessage(val listOfMessage: List<Message>): MainUIState()
    data class IsLoggedIn(val isLoggedIn: Boolean): MainUIState()
}
