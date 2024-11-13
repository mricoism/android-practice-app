package com.mricoism.firebasefirestoreapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.mricoism.firebasefirestoreapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis


class MainActivity: AppCompatActivity() {

    val TAG = "MainActivityRIKO"
    private lateinit var binding: ActivityMainBinding
    private val personCollectionRef = Firebase.firestore.collection("persons")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnUploadData.setOnClickListener {
            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val age = binding.etAge.text.toString().toInt()

            val person = Person(firstName, lastName, age)
            savePerson(person)
        }

        subscribeToRealtimeUpdates()


        binding.btnRetrieveData.setOnClickListener {
            retrivePersons()
        }

    }

    private fun subscribeToRealtimeUpdates() {
        // we can also give query .whereEqualTo() before .addSnapshotListener below
        personCollectionRef.addSnapshotListener {
            querySnapshot, firebaseFirestoreException ->
            firebaseFirestoreException?.let {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                return@addSnapshotListener
            }
            querySnapshot?.let {
                val sb = StringBuilder()
                for (document in it.documents) {
                    val person = document.toObject<Person>()
                    sb.append("$person\n")
                }
                binding.tvPersons.text = sb.toString()
            }
        }
    }

    private fun retrivePersons() = CoroutineScope(Dispatchers.IO).launch {
        val fromAge = binding.etFrom.text.toString().toInt()
        val toAge = binding.etTo.text.toString().toInt()

        try {
//            val querySnapshot = personCollectionRef.get().await()

            val querySnapshot = personCollectionRef
                .whereGreaterThan("age", fromAge)
                .whereLessThan("age", toAge)
                .orderBy("age")
                .get()
                .await()
            val sb = StringBuilder()
            for (document in querySnapshot.documents) {
//                val person = document.toObject(Person::class.java) // old ways without ktx
                val person = document.toObject<Person>()
                sb.append("$person\n")

            }
            withContext(Dispatchers.Main) {
                binding.tvPersons.text = sb.toString()
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun savePerson(person: Person) = CoroutineScope(Dispatchers.IO).launch {
        try {
            personCollectionRef.add(person).await()
            withContext(Dispatchers.Main) {
                Toast.makeText(this@MainActivity, "Successfully saved data.", Toast.LENGTH_LONG).show()
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}