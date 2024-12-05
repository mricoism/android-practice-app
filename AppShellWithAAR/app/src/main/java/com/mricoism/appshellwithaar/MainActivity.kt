package com.mricoism.appshellwithaar

import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mricoism.appshellwithaar.databinding.ActivityMainBinding
import io.flutter.embedding.android.FlutterActivity

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

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