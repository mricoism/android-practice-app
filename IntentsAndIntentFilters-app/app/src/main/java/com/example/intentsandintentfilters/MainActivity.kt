package com.example.intentsandintentfilters

import android.content.ActivityNotFoundException
import android.content.ContentValues.TAG
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
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
import coil.compose.AsyncImage
import com.example.intentsandintentfilters.ui.theme.IntentsAndIntentFiltersTheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<ImageViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            Log.d(TAG, "onCreate() Restoring previous state")
            /* restore state */
        } else {
            Log.d(TAG, "onCreate() No saved state available")
            /* initialize app */
        }

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
                    viewModel.uri?.let {
                        Log.d("hws a", "1")
                        AsyncImage(model = viewModel.uri, contentDescription = null)
                    }

                    Button(onClick = {
                        println("hws f")
                        Log.d("hws f", "1")
//                        Intent(applicationContext, SecondActivity::class.java).also {
//                            startActivity(it)
//                        }
                        /*
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

                         */
                        val intent = Intent(Intent.ACTION_SEND).apply {
                            type = "text/plain"
                            putExtra(Intent.EXTRA_EMAIL, arrayOf("mricoism@gmail.com"))
                            putExtra(Intent.EXTRA_SUBJECT, "This is my subject")
                            putExtra(Intent.EXTRA_TEXT, "Hi.. There!, let me introduce my self")
                        }
                        if (intent.resolveActivity(packageManager) != null) {
                            startActivity(intent)
                        }
                    }) {
                        Log.d("hws b", "1")
                        Text(text = "Click Me!")
                    }
                }
            }
        }
    }
    /*
    INTENT FILTER
    By default.. if our app is already open, it will launch new instance, and then will get that intent at onCreate.
    BUT!
    On AndroidManifest.xml, we already set android:launchMode="singleTop".
    witch means if our app is already open we will just take that active instance and just send the data to that active instance.
    bcs of that its will trigger onNewIntent().

    FINALY! FIX ISSUE:
    https://stackoverflow.com/a/73595857
     */

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        Log.d("hws x", "oNewIntent")
        val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Log.d("hws y", "inside if true")
            intent.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java) // it will give warning. So press (option + enter) to show option action then click "Surround with if"
        } else {
            Log.d("hws z", "inside false")
            TODO("VERSION.SDK_INT < TIRAMISU")
            intent.getParcelableExtra(Intent.EXTRA_STREAM)
        }
        viewModel.updateUri(uri)
    }
}

/*
Go to terminal and type this to get package name all app in simulator
1. adb shell
2. pm list packages
 */