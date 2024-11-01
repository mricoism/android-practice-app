package com.example.shopinglist

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LocationViewModel: ViewModel() {
    private val _location = mutableStateOf<LocationData?>(null)
    val location: State<LocationData?> = _location

    private val _address = mutableStateOf(listOf<GeocodingResult>())
    val address: State<List<GeocodingResult>> = _address



    fun updateLocation(newLocation: LocationData) {
        Log.d("hws b", "1")
        _location.value = newLocation
        Log.d("hws b", "2")
    }

    fun fetchAddress(latLng: String) {
        Log.d("hws b", "3")
        try {
            viewModelScope.launch {
                Log.d("hws b", "4 | ${latLng}")
                val result = RetrofitClient.create().getAddressFromCoordinates(
                    latlng = latLng,
                    apiKey = "AIzaSyCx-Rf6jxXgfZabGsRRLwdRW0OSciI_UDw"
                )
                Log.d("hws b", "5 ${result.status} | ")
                _address.value = result.results
                Log.d("hws b", "6")
            }
            Log.d("hws b", "7")
        } catch (e: Exception) {
            Log.d("hws b", "8")
            Log.d("res1", "${e.cause} ${e.message}")
        }
    }
}