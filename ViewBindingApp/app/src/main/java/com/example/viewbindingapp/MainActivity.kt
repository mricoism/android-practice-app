package com.example.viewbindingapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.viewbindingapp.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {

//    private lateinit var textView: TextView // OLD WAYS

    // NamaClass binding berdasarkan nama activity layout xml
    // Jika nama activity_main.xml maka ActivityMainBinding
    // Jika nama activity_login.xml maka ActivityLoginBinding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main) // OLD WAYS
//        textView = findViewById(R.id.textView) // OLD WAYS
//        textView.text = "RIKOOOO" // OLD WAYS

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView.text = "MAILLLL"
        binding.Button.setOnClickListener {
            // DO SOMETING HERE
        }


    }
}