package com.mricoism.fragmentapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mricoism.fragmentapp.databinding.ActivityMainBinding


class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val TAG = "MainActivityRIKO"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firstFragment = FirstFragment()
        val secondFragment = SecondFragment()

        // Set initial fragment
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment, firstFragment)
            commit() // To Apply above changes
        }

        /*
        IMPORTANT!
        If we click back button on device. Then our activity will close.
        because those fragments currently don't have their own stack.
        So when ever we replace such fragment here, then we don't push that fragment on top of the fragment or activities stack.
        So we can navigate back, with button back device to the fragment we navigated to previously.
         */

        binding.btnFragment1.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, firstFragment)

                // we can navigate back, with button back device to the fragment we navigated to previously.
                addToBackStack(null) // its take a name as a parameter to later detect which fragment we push on that back stack, So to give this fragment a unique name on our back stack. But we can pass null if we dont care about the name.

                commit() // To Apply above changes
            }
        }

        binding.btnFragment2.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment, secondFragment)
                addToBackStack(null)
                commit() // To Apply above changes
            }
        }



    }
}

/*
To Create new fragment you can do :
 double tap inside folder com.mricoism.fragmentapp
 new > Fragment > chose fragment


 */
