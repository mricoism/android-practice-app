package com.mricoism.copycatbafbpkb

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.mricoism.copycatbafbpkb.databinding.ActivityMainBinding
import io.flutter.embedding.android.FlutterActivity
import com.mricoism.copycatbafbpkb.BuildConfig

class MainActivity : AppCompatActivity() {

    private val BASE_URL = BuildConfig.SERVER_URL
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.button.text = "Trigger crash!"
        binding.button.text = "open flutter"
        binding.button.setOnClickListener {
            Log.d("HWS", "setOnClickListener")
//            throw RuntimeException("Test Crash RIKO")

            startActivity(
                FlutterActivity.withNewEngine().build(this)
            )
        }
    }
}