package com.mricoism.wishlistapp

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun HomeView() {
    val contextHV = LocalContext.current
    Scaffold(topBar = {
        AppBarView(title = "WishlistApp") {
            Log.d("HWS", "a 1")
            Toast.makeText(contextHV, "Button Clicked!", Toast.LENGTH_LONG).show();
            Log.d("HWS", "a 2")
        }
    }) {
        paddingValues ->
        LazyColumn(modifier = Modifier.fillMaxSize().padding(paddingValues)) {

        }
    }
}