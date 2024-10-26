package com.example.intentsandintentfilters

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.example.intentsandintentfilters.ui.theme.IntentsAndIntentFiltersTheme

class SecondActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // intent.getStringExtra() gamabaran bagaimana activity lain bisa menerima value Extra
        setContent {
            IntentsAndIntentFiltersTheme {
                Text(text = "Second Activity")
            }
        }
    }
}