package com.example.databindingapp

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.databindingapp.databinding.ActivityMainBinding

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.textView.text = "Hola 1"
        binding.messsage = "Hola 2"

        binding.Button.text = "Check"
        binding.Button.setOnClickListener {
            println("RIKO")
            Log.d("hws", "Test")
        }

    }
}