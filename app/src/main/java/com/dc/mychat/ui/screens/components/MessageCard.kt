package com.dc.mychat.ui.screens.components

import com.dc.mychat.repository.MessageRepository


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dc.mychat.model.Message

@Composable
fun MessageCard(message: Message) {

    Card(
        elevation = 4.dp,
        backgroundColor = Color.LightGray,
        shape = RoundedCornerShape(0.dp, 8.dp, 8.dp, 8.dp),
        modifier = Modifier.wrapContentWidth().padding(8.dp)
    ) {
        Row(
            modifier = Modifier.wrapContentWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = message.message,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(8.dp).weight(5f, fill = false)

            )

            Text(
                text = message.timestamp,
                fontSize = 8.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(8.dp).weight(1f, fill = false)
            )
        }

    }
}

@Preview
@Composable
fun CardMessagesPreview() {
    val message = MessageRepository().getAllMessages()
    MessageCard(message = message[4])
}