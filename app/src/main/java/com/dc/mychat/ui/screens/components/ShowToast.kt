package com.dc.mychat.ui.screens.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun ShowToast(
    message: String,
    isToastShowing: (Boolean) -> Unit
) {
    val context = LocalContext.current
    if (message.isNotEmpty()) Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    isToastShowing(false)
}