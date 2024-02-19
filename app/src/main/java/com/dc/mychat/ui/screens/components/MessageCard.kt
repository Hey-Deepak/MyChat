package com.dc.mychat.ui.screens.components


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.dc.mychat.domain.model.Message
import com.dc.mychat.ui.viewmodel.SharedViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MessageCard(message: Message, sharedViewModel: SharedViewModel) {
    val isRight = sharedViewModel.senderProfile!!.mailId == message.senderMailId

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
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                ),
                shape = if (isRight) RoundedCornerShape(
                    8.dp,
                    0.dp,
                    8.dp,
                    8.dp
                ) else RoundedCornerShape(0.dp, 8.dp, 8.dp, 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.LightGray
                )
            ) {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (message.message.isEmpty()) {
                        AsyncImage(
                            model = message.imageUri,
                            contentDescription = "Message Image",
                            modifier = Modifier.fillMaxSize(0.75f)
                        )
                    } else {
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
}

