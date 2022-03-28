package com.dc.mychat.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dc.mychat.R
import org.intellij.lang.annotations.JdkConstants

@Composable
fun ProfileScreen() {
    Column(modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center) {
        Image(painter = painterResource(id = R.drawable.ic_add_profile_picture),
            contentDescription = "",
            modifier = Modifier
                .size(200.dp, 200.dp)
                .clickable { onProfileClicked() }
        )
        var text by remember { mutableStateOf("") }
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter Your Name", fontSize = 20.sp) },
            maxLines = 1,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.SemiBold),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),

        )
        var textOfUserId by remember { mutableStateOf("") }
        OutlinedTextField(
            value = textOfUserId,
            onValueChange = { textOfUserId = it },
            label = { Text("Enter Your UserId Name", fontSize = 20.sp) },
            maxLines = 1,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.SemiBold),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            )
        
        Button(onClick = { createProfile() },
        modifier = Modifier.padding(8.dp),
        ) {
            Text(text = "Done", fontSize = 16.sp)
        }

    }
}

fun createProfile() {
    TODO("Not yet implemented")
}

fun onProfileClicked() {
    TODO("Not yet implemented")
}

@Preview
@Composable
fun viewProfileScreen() {
    ProfileScreen()
}