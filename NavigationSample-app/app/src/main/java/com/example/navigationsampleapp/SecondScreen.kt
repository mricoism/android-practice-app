package com.example.navigationsampleapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SecondScreen(name: String, navigateToFirst: ()->Unit) {
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
        Text(text = "SecondScreen", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(text = "Your data: ${name}", fontSize = 24.sp, fontFamily = FontFamily.Monospace)
        Spacer(modifier = Modifier.height(16.dp))
        IconButton(colors = buttonColor,onClick = {
            navigateToFirst()
        }) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                contentDescription = "Move to next page"
            )
        }
    }
}

@Preview
@Composable
fun SecondPreview() {
    SecondScreen("riko",{})
}
