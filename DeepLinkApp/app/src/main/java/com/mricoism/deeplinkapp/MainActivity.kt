package com.mricoism.deeplinkapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mricoism.deeplinkapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // Using `by lazy` to initialize ViewBinding
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var uri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        handleDeepLink(intent)

        uri = intent.data

        if (uri != null) {
            // if the uri is not null then we are getting
            // the path segments and storing it in list.
            val parameter = uri!!.pathSegments

            // after that we are extracting string
            // from that parameters.
            val param = parameter[parameter.size -1]

            // on below line we are setting that
            // string to our text view which
            // we got as params.
            binding.idTVMessage.text = param
        }



    }

//    override fun onNewIntent(intent: Intent) {
//        super.onNewIntent(intent)
//
//        setIntent(intent)
//        val action: String? = intent?.action
//        val data: Uri? = intent?.data
//    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleDeepLink(intent)
    }

    private fun handleDeepLink(intent: Intent?) {
        // Retrieve the data (deep link URI) from the intent
        val deepLinkUri = intent?.data

        if (deepLinkUri != null) {
            // Extract information from the URI
            val host = deepLinkUri.host // Example: "www.example.com"
            val path = deepLinkUri.path // Example: "/path"
            val queryParams = deepLinkUri.query // Example: "param=value"

            // Debugging or logging the deep link data
            Log.d("DeepLink", "Host: $host, Path: $path, Query: $queryParams")

            // Use the extracted data to navigate or perform actions
            when (path) {
                "/path" -> {
                    // Navigate to a specific fragment or perform some action
                    showToast("Deep link to path: $path")
                }
                else -> {
                    // Handle unknown paths
                    showToast("Unknown path: $path")
                }
            }
        } else {
            showToast("No deep link data found")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}