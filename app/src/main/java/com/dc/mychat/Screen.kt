package com.dc.mychat

sealed class Screen(val route: String){
    object LoggedIn: Screen("loggedin_screen")
    object Profile: Screen("profile_screen")
    object AllUsers: Screen("allusers_screen")
    object Message: Screen("message_screen")
    object Home: Screen("home_screen")
}
