package com.example.navigationsampleapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun FirstScreen(navigateToSecond: (String) -> Unit) {
    val name = remember {
        mutableStateOf("")
    }
    val buttonColor = IconButtonColors(
        contentColor = Color.White,
        containerColor = Color(83, 100, 147),
        disabledContentColor = Color.White,
        disabledContainerColor = Color.DarkGray
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "FirstScreen", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))

        Row(
//                modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(value = name.value, onValueChange = {
                name.value = it
            },)
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(colors = buttonColor, onClick = {
                navigateToSecond(name.value)
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowForward,
                    contentDescription = "Move to next page"
                )
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun FirstPreview() {
    FirstScreen({})
}
