package com.example.shopinglist

import android.Manifest
import android.content.Context
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController


data class ShopingData(
    val id: Int,
    var name: String,
    var quantity: Int,
    var isEditing: Boolean = false,
    var address: String = ""
)

@Composable
fun ShopingListApp(
    locationUtils: LocationUtils,
    viewModel: LocationViewModel,
    navController: NavController,
    context: Context,
    address: String
) {
    var datas by remember { mutableStateOf(listOf<ShopingData>()) }
    var showDialog by remember { mutableStateOf(false) }
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }

    val requestPermissionLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestMultiplePermissions()) { permission ->
            if (permission[Manifest.permission.ACCESS_COARSE_LOCATION] == true && permission[Manifest.permission.ACCESS_FINE_LOCATION] == true) {
                // I have access to location
                locationUtils.requestLocationUpdates(viewModel = viewModel)
            } else {
                // Ask For permission
                val rationalRequired = ActivityCompat.shouldShowRequestPermissionRationale(
                    context as MainActivity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) || ActivityCompat.shouldShowRequestPermissionRationale(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )

                if (rationalRequired) {
                    Toast.makeText(
                        context,
                        "Location is required for this feature to work",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        context,
                        "Location is required, PLEASE enable in android setting",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                showDialog = true
            },
        ) {
            Text(text = "Add item")
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            items(datas) {
                item ->
                if (item.isEditing) {
                    ItemEditor(item = item) {
                        editedName, editedQuantity ->
                        datas = datas.map { it.copy(isEditing = false) }
                        val editedItem = datas.find { it.id == item.id }
                        editedItem?.let {
                            it.name = editedName
                            it.quantity = editedQuantity
                            it.address = address
                        }
                    }
                } else {
                    ShopingListItem(item, {
                        datas = datas.map { it.copy(isEditing = it.id == item.id) }
                    }, {
                        datas -=item
                    })
                }


            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            title = {
                Text(text = "Add Shoping Item")
            },
            text = {
                Column {
                    OutlinedTextField(
                        value = itemName,
                        label = { Text(text = "Item")},
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        onValueChange = { value ->
                            itemName = value
                        })
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = itemQuantity,
                        label = { Text(text = "Quantity")},
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                        onValueChange = { value ->
                            itemQuantity = value
                        })
                    Button(onClick = {
                        if (locationUtils.hasLocationPermission(context)) {
                            locationUtils.requestLocationUpdates(viewModel)
                            navController.navigate("locationScreen") {
                                this.launchSingleTop
                            }
                        } else {
                            requestPermissionLauncher.launch(arrayOf(
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            ))
                        }
                    }) {
                        Text(text = "Address")
                    }
                }
            },
            confirmButton = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = {
                        if (itemName.isNotBlank()) {
                            val newItem = ShopingData(
                                id = datas.size + 1,
                                name = itemName,
                                quantity = itemQuantity.toIntOrNull()?: 1,
                                address = address
                            )
                            datas += newItem
                            showDialog = false
                            itemName = ""
                        }
                    }) {
                        Text(text = "Add")
                    }
                    Button(onClick = {
                        showDialog = false
                    }) {
                        Text(text = "Cancel")
                    }
                }
            })
    }
}

@Composable
fun ShopingListItem(
    item: ShopingData,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .border(
                border = BorderStroke(width = 2.dp, Color(83, 100, 147)),
                shape = RoundedCornerShape(20)
            )
    ) {
        Column(modifier = Modifier
            .weight(1f)
            .padding(8.dp)) {
            Row {
                Text(text = item.name, modifier = Modifier.padding(8.dp))
                Text(text = "Qyt: ${item.quantity}", modifier = Modifier.padding(8.dp))
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Icon(imageVector = Icons.Default.LocationOn, contentDescription = null)
                Text(text = item.address)
            }
        }
        Row(modifier = Modifier.padding(8.dp)) {
            IconButton(onClick = onEditClick) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = null)
            }
            IconButton(onClick = onDeleteClick) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = null)
            }
        }
    }
}

@Composable
fun ItemEditor(item: ShopingData, onEditCompelete: (String, Int) -> Unit) {
    var editedName by remember { mutableStateOf(item.name) }
    var editedQuality by remember { mutableStateOf(item.quantity.toString()) }
    var isEditing by remember { mutableStateOf(item.isEditing) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column {
            BasicTextField(
                value = editedName,
                singleLine = true,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp),
                onValueChange = {
                    editedName = it
                })
            BasicTextField(
                value = editedQuality,
                singleLine = true,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp),
                onValueChange = {
                    editedQuality = it
                })

        }
        Button(onClick = {
            isEditing = false
            onEditCompelete(editedName, editedQuality.toIntOrNull() ?: 1)
        }) {
            Text(text = "Save")
        }
    }
}

/*
Color i considerate as background : F3F3E0
you can go to color pallete or color hunt to find one then..
color converter : https://www.rapidtables.com/convert/color/hex-to-rgb.html?hex=536493



BUGS OR SOMETHING NEED TO IMPROVE IN THIS APP:
1. Handle input from text field. currently is force convert String to Int. Even tho using toIntOrNull and using if null return 1.
2. Layouting. If We input long text, the button will shrink or getting smaller then disappear. Its disturb user experience.
3. Button add Item can either change to be Floating button or placing to the top corner then change it with Icon button Plus.
4. Make better design item lazy list.

 */