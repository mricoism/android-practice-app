package com.mricoism.imagecompression

import android.webkit.MimeTypeMap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.launch

@Composable
fun PhotoPickerScreen(
    imageCompressor: ImageCompressor,
    fileManager: FileManager,
    modifier: Modifier = Modifier
) {

    val scope = rememberCoroutineScope()

    val context = LocalContext.current
    val photoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
        contentUri ->
            if (contentUri == null) {
                return@rememberLauncherForActivityResult
            }

            val mimeType = context.contentResolver.getType(contentUri)
            val extension = MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType)
            scope.launch {
                fileManager.saveImage(contentUri, "UnCompressed.$extension")
            }

            // 1024L is 1kb
            // 200 is value we want to mutiple
            // so 200 * 1024L is 200KB
            scope.launch {
                val compressedImage = imageCompressor.comressImage(
                    contentUri = contentUri,
                    compressionThresHold = 200 * 1024L
                )

                //  ?: return@launch bellow here is if compressedImage is null, then we will return
                fileManager.saveImage(
                    bytes = compressedImage ?: return@launch,
                    fileName = "Compressed.$extension"
                    )
            }
    })
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = {
            photoPicker.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        }) {
            Text(text = "Pick an image")
        }
    }


}