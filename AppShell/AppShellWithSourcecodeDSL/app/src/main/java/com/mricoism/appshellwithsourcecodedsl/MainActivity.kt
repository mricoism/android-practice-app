package com.mricoism.appshellwithsourcecodedsl

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mricoism.appshellwithsourcecodedsl.databinding.ActivityMainBinding
import io.flutter.embedding.android.FlutterActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var binding: ActivityMainBinding

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView.text = "Move to another app or module"
        binding.Button.setOnClickListener {
            startActivity(
                FlutterActivity.withNewEngine().build(this)
            )
        }
    }
}