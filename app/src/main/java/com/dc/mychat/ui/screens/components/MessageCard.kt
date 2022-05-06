package com.dc.mychat.ui.screens.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dc.mychat.domain.model.Message
import com.dc.mychat.ui.viewmodel.MainViewModel
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MessageCard(message: Message, mainViewModel: MainViewModel) {
    Log.d("TAG", "Inside Message card ${message}")
    val isRight = mainViewModel.senderMailIdState.value.equals(message.senderMailId)
    Log.d("TAg 11 Message Card", isRight.toString())

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = if (isRight) Arrangement.End else Arrangement.Start
        ) {
            Card(
                modifier = Modifier
                    .wrapContentWidth(),
                elevation = 8.dp,
                shape = if (isRight) RoundedCornerShape(8.dp, 0.dp, 8.dp, 8.dp ) else RoundedCornerShape(0.dp, 8.dp, 8.dp, 8.dp ),
                backgroundColor = Color.LightGray
            ) {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = message.message,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(8.dp)

                    )
                    Text(
                        text = SimpleDateFormat(
                            "h:mm a",
                            Locale.US
                        ).format(message.timestamp.toDate()),
                        fontSize = 8.sp,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(8.dp)
                    )
                }

            }

        }

    }
}

