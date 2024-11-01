package com.example.shopinglist

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

//@SuppressLint("UnrememberedMutableState") // Ada Error di Marker(position = MarkerState
@Composable
fun LocationSelectionScreen(
    location: LocationData,
    locationSelected: (LocationData) -> Unit
) {
    Log.d("hws a", "1")
    val userLocation = remember {
        mutableStateOf(LatLng(location.latitude, location.longitude))
    }

    var cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(userLocation.value, 10f)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier
                .weight(1f)
                .padding(top = 16.dp),
            cameraPositionState = cameraPositionState,
            onMapClick = {
                userLocation.value = it
            }
        ) {
            Log.d("hws a", "2")
            Marker(state = MarkerState(userLocation.value))
            Log.d("hws a", "3")
        }

        var newLocation: LocationData

        Button(onClick = {
            Log.d("hws a", "4")
            newLocation = LocationData(userLocation.value.latitude, userLocation.value.longitude)
            locationSelected(newLocation)
        }) {
            Log.d("hws a", "5")
            Text(text = "Set Location")
        }
    }
}