package com.dc.mychat.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Top
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.text.PlaceholderVerticalAlign.Companion.Top
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.intellij.lang.annotations.JdkConstants

@Composable
fun ChatScreen() {
    Surface() {
        Column(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "All Users",
                    modifier = Modifier.padding(16.dp),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                )
                CardChat()
                CardChat()
                CardChat()
                CardChat()
                CardChat()
                CardChat()
                CardChat()
                CardChat()

            }
        }
    }
}

@Preview
@Composable
fun viewChatScreen() {
    ChatScreen()
}