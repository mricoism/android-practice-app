package com.mricoism.shellappexample

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.mricoism.shellappexample.databinding.ActivityMainBinding
import io.flutter.embedding.android.FlutterActivity

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Button.setOnClickListener {
            Log.d("HWS", "ON CLICK BUTTON")
            startActivity(
                FlutterActivity
                    .withNewEngine()
                    .build(this))
        }
    }
}