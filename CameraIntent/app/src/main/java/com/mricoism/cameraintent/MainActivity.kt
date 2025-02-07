package com.mricoism.cameraintent

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.mricoism.cameraintent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var imageUri: Uri

    private val ALLOWED_CAMERA_APPS = listOf(
        "com.android.camera",    // Default system camera
        "com.google.android.GoogleCamera", // Google Camera
        "com.sec.android.app.camera" // Samsung Camera
    )

    private val cameraLauncher = registerForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
        Log.d("HWS", "A 1 | ${result.resultCode} | ${Activity.RESULT_OK}")
        if (result.resultCode == Activity.RESULT_OK) {
            Log.d("HWS", "A 2")
            // Handle the captured image
//            val capturedImageUri = result.data?.data
            val capturedImageUri = result.data?.extras!!["data"] as Bitmap?
            Log.d("HWS", "A 3 | $capturedImageUri")
            capturedImageUri?.let {
                // Process the image (display, upload, etc.)
                Log.d("HWS", "A 4 | $it")
                binding.imageView.setImageBitmap(it)
//                binding.imageView.setImageURI(it)
                Log.d("HWS", "A 5")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)



        binding.button.setOnClickListener {
            launchAllowedCameraApp(context = this@MainActivity)
        }
    }

    private fun launchAllowedCameraApp(context: Context) {
        val packageManager = context.packageManager
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        val resolveInfos = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY)
        for (resolveInfo in resolveInfos) {
            val packageName = resolveInfo.activityInfo.packageName
            if (ALLOWED_CAMERA_APPS.contains(packageName)) {
                intent.setPackage(packageName) // Force the allowed camera app
                cameraLauncher.launch(intent)
                return // Exit after launching
            }
        }
        // No allowed camera app found
        Toast.makeText(context, "No allowed camera apps installed", Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}