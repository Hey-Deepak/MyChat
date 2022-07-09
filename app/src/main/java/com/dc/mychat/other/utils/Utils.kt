package com.dc.mychat.other.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import androidx.activity.ComponentActivity
import com.dc.mychat.domain.model.Profile
import com.google.gson.Gson

class Utils {

    companion object {

        fun getProfileFromPrefs(context: Context): Profile? {
            val sharedPrefs = context.getSharedPreferences("main", MODE_PRIVATE)
            val profileJson = sharedPrefs.getString("profile", null)
            return Gson().fromJson(profileJson, Profile::class.java)

        }
    }
}