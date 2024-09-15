package com.example.walkthroughbmi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.walkthroughbmi.ui.theme.WalkthroughBMITheme
import androidx.compose.foundation.layout.Column as Column

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WalkthroughBMITheme {
                BmiApp()
            }
        }
    }
}

@Composable
fun BmiApp() {
    var heightInput by remember { mutableStateOf("") }
    var weightInput by remember { mutableStateOf("") }
    var bmi by remember { mutableStateOf(0f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "BMI Calculator",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center
        )

        OutlinedTextField(
            value = heightInput,
            onValueChange = {
                heightInput = it.replace(",", ".")
                calculateBmi(heightInput, weightInput)?.let { bmiResult ->
                    bmi = bmiResult
                }
            },
            label = { Text("Height (m)") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )

        OutlinedTextField(
            value = weightInput,
            onValueChange = {
                weightInput = it.replace(",", ".")
                calculateBmi(heightInput, weightInput)?.let { bmiResult ->
                    bmi = bmiResult
                }
            },
            label = { Text("Weight (kg)") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )

        Text(
            text = String.format("BMI: %.2f", bmi).replace(",", "."),
            fontSize = 20.sp,
            color = Color.Black
        )
    }
}

fun calculateBmi(height: String, weight: String): Float? {
    val heightValue = height.toFloatOrNull()
    val weightValue = weight.toFloatOrNull()

    if (heightValue != null && weightValue != null && heightValue > 0) {
        return weightValue / (heightValue * heightValue)
    }
    return null
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WalkthroughBMITheme {
        BmiApp()
    }
}