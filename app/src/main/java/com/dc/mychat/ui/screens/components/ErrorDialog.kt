package com.dc.mychat.ui.screens.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

@Composable
fun ErrorDialog(
    isDialogShowing: Boolean,
    errorMessage: String,
    stateChanged:(Boolean)->Unit
) {

    if (isDialogShowing)
    AlertDialog(
        onDismissRequest = {},
        confirmButton = {
            TextButton(onClick = { stateChanged(false) })
            { Text(text = "OK") }
        },
        dismissButton = {},
        title = { Text(text = "Error!") },
        text = { Text(text = errorMessage) }
    )
}