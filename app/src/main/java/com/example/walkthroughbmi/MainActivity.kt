package com.example.walkthroughbmi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.walkthroughbmi.ui.theme.WalkthroughBMITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BmiApp()
        }
    }
}

@Composable
fun BmiApp() {
    var heightInput by remember { mutableStateOf("") }
    var weightInput by remember { mutableStateOf("") }
    var bmi by remember { mutableStateOf(0f) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
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
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
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
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
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
    val weightValue = weight.toIntOrNull()

    if (heightValue != null && weightValue != null && heightValue > 0) {
        return weightValue / (heightValue * heightValue)
    }
    return null
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BmiApp()
}