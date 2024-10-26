package com.example.intentsandintentfilters

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.intentsandintentfilters.ui.theme.IntentsAndIntentFiltersTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IntentsAndIntentFiltersTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//
//                }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = {
//                        Intent(applicationContext, SecondActivity::class.java).also {
//                            startActivity(it)
//                        }
                        
                        // Explicit Intent Launch YT
                        Intent(Intent.ACTION_MAIN).also {
                            it.`package` = "com.google.android.youtube"
//                            if (it.resolveActivity(packageManager) != null) {
//                                startActivity(it)
//                            }
                            try {
                                startActivity(it)
                            } catch (e: ActivityNotFoundException) {
                                e.printStackTrace()
                            }
                        }



                    }) {
                        Text(text = "Click Me!")
                    }
                }
            }
        }
    }
}

/*
Go to terminal and type this to get package name all app in simulator
1. adb shell
2. pm list packages
 */