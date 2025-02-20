package com.mricoism.cameraintent2

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mricoism.cameraintent2.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var photoUri: Uri

    // maybe this used just for set image from callback
    val takePictureUriLauncher = registerForActivityResult(contract = ActivityResultContracts.TakePicture()) { success ->
        if (success) {
            binding.imageView.setImageURI(photoUri)
        }
    }
    // Define a request code
    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.button.setOnClickListener {
            Log.d("HWS", "setOnClickListener")
            openSpecificCameraApps()
        }
    }

    private fun openSpecificCameraApps() {
        // Step 1: Create file and Uri for the image
//        val photoFile = File.createTempFile("IMG_", ".jpg", getExternalFilesDir(Environment.DIRECTORY_PICTURES))
//        photoUri = FileProvider.getUriForFile(this, "$packageName.fileprovider", photoFile)

        val image = File(applicationContext.filesDir, "camera_photo.png")
        photoUri = FileProvider.getUriForFile(applicationContext, "com.mricoism.takeimageapp.fileprovider", image)

        // Step 2: Prepare camera intent
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
            addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION) // Allow the camera to write to our Uri
        }

        // Step 3: Get available camera apps
        val packageManager = packageManager
        val cameraApps = packageManager.queryIntentActivities(cameraIntent, 0)

        // Step 4: Filter allowed apps (e.g., only Google or Samsung Camera)
        val allowedApps = cameraApps.filter {
            it.activityInfo.packageName.contains("google") || it.activityInfo.packageName.contains("samsung") || it.activityInfo.packageName.contains("camera")
        }
        Log.d("HWS", "${allowedApps}")

        // Step 5: Show chooser with only allowed apps
        if (allowedApps.isNotEmpty()) {
            val intentChooser = Intent.createChooser(cameraIntent, "Select Camera App").apply {
                putExtra(Intent.EXTRA_INITIAL_INTENTS, allowedApps.map {
                    Intent(cameraIntent).setPackage(it.activityInfo.packageName)
                }.toTypedArray())
            }
            startActivityForResult(intentChooser, REQUEST_IMAGE_CAPTURE)
        } else {
            Toast.makeText(this, "No allowed camera apps found", Toast.LENGTH_SHORT).show()
        }
    }

    // Handle the result (needed since we're using `startActivityForResult()`)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            binding.imageView.setImageURI(photoUri) // Display the captured image
        }
    }
}