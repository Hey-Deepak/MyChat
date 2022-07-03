package com.dc.mychat

sealed class Screen(val route: String){
    object Splash: Screen("splash_screen")
    object Login: Screen("login_screen")
    object Profile: Screen("profile_screen")
    object AllUsers: Screen("allusers_screen")
    object Message: Screen("message_screen")
}
