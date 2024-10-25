package com.example.unitconverter

import android.graphics.drawable.Icon
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf // Saya tambahkan
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import androidx.compose.runtime.getValue // Saya tambahkan
import androidx.compose.runtime.setValue // Saya tambahkan
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import kotlin.math.roundToInt


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    innerPadding ->

                    UnitContverter(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun UnitContverter(modifier: Modifier = Modifier) {

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpand by remember { mutableStateOf(false) }
    var oExpand by remember { mutableStateOf(false) }
    var iConversionFactor = remember { mutableStateOf(1.00) }
    var oConversionFactor = remember { mutableStateOf(1.00) }

    val customTextStyle = TextStyle(
        fontFamily = FontFamily.Monospace,
        fontSize = 30.sp,
        color = Color.DarkGray
    )

    fun convertUnits() {
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * iConversionFactor.value * 100.0 / oConversionFactor.value).roundToInt() / 100.0
        outputValue = result.toString()
    }

    fun iSelected(unit: String, value: Double) {
        iExpand = false
        inputUnit = unit
        iConversionFactor.value = value
        convertUnits()
    }

    fun oSelected(unit: String, value: Double) {
        oExpand = false
        outputUnit = unit
        oConversionFactor.value = value
        convertUnits()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = "Unit Converter",
            textAlign = TextAlign.Center,
            style = customTextStyle)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
            // Do Something here when somthing happen
            inputValue = it
                convertUnits()
            },
            label = {
                Text(text = "Enter value")
            })
        Spacer(modifier = Modifier.height(16.dp))
        Row {

            Box {
                Button(onClick = {
                    iExpand = true
                }) {
                    Text(text = inputUnit)
                    Icon(imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "None")
                }
                DropdownMenu(expanded = iExpand, onDismissRequest = {
                    iExpand = false
                }) {
                    DropdownMenuItem(text = { Text(text = "Meters") }
                        , onClick = {
                            iSelected("Meters", 1.0)
                        })
                    DropdownMenuItem(text = { Text(text = "Centimeters") }
                        , onClick = {
                            iSelected("Centimeters", 0.01)
                        })
                    DropdownMenuItem(text = { Text(text = "Milimeters") }
                        , onClick = {
                            iSelected("Milimeters", 0.001)
                        })
                }

            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = {
                    oExpand = true
                }) {
                    Text(text = outputUnit)
                    Icon(imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "None")
                }
                DropdownMenu(expanded = oExpand, onDismissRequest = { /*TODO*/
                    oExpand = false
                }) {
                    DropdownMenuItem(text = { Text(text = "Meters") }
                        , onClick = {
                            oSelected("Meters", 1.0)
                        })
                    DropdownMenuItem(text = { Text(text = "Centimeters") }
                        , onClick = {
                            oSelected("Centimeters", 0.01)
                        })
                    DropdownMenuItem(text = { Text(text = "Milimeters") }
                        , onClick = {
                            oSelected("Milimeters", 0.001)
                        })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result : $outputValue $outputUnit", style = MaterialTheme.typography.headlineSmall)
    }
}

@Preview(showBackground = true)
@Composable
fun LayoutPreview() {
    UnitContverter()
}

