package com.dc.mychat.domain.model

import android.net.Uri

data class Profile(
    var displayName: String,
    val mailId: String,
    val displayPhoto: String
)