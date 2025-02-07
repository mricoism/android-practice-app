package com.mricoism.takeimageapp

import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mricoism.takeimageapp.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var imageUri: Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        val pickMedia = registerForActivityResult(contract = ActivityResultContracts.PickVisualMedia(), callback = {
                uri  ->
            if (uri != null) {
                binding.imageView.setImageURI(uri)
            }

        })
        binding.buttonChoose.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        imageUri = getPicture()
        val takePicture = registerForActivityResult(contract = ActivityResultContracts.TakePicture(), callback = {
            uri ->
            if (imageUri != null) {
                binding.imageView.setImageURI(imageUri)
            }
        })
        binding.buttonCapture.setOnClickListener {
            takePicture.launch(imageUri)

        }
    }

    fun getPicture(): Uri {
        val image = File(applicationContext.filesDir, "camera_photo.png")

        return FileProvider.getUriForFile(applicationContext, "com.mricoism.takeimageapp.fileprovider", image)
    }
}