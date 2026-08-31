package com.example.basicviewsapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

class BasicViewsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    BasicViewsForm()
                }
            }
        }
    }

    @Composable
    fun BasicViewsForm() {
        var textValue by remember { mutableStateOf("") }
        var isChecked by remember { mutableStateOf(false) }
        var isToggled by remember { mutableStateOf(true) }
        var selectedOption by remember { mutableStateOf("Option 1") }
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(scrollState)
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start
        ) {
            // TextView equivalent
            Text(
                text = "Student Survey Form",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Demonstrating Basic Android Views",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // EditText equivalent
            Text("Personal Bio (EditText):", style = MaterialTheme.typography.labelLarge)
            OutlinedTextField(
                value = textValue,
                onValueChange = { textValue = it },
                placeholder = { Text("Type something here...") },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // CheckBox equivalent
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = isChecked, onCheckedChange = { isChecked = it })
                Text("Enable Premium Features (CheckBox)")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // ToggleButton / Switch equivalent
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text("App Notifications (ToggleButton/Switch)")
                Switch(checked = isToggled, onCheckedChange = { isToggled = it })
            }

            Spacer(modifier = Modifier.height(16.dp))

            // RadioGroup / RadioButton equivalent
            Text("Select Department (RadioGroup):", style = MaterialTheme.typography.labelLarge)
            val options = listOf("Computer Science", "Information Science", "Electronics")
            options.forEach { text ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = { selectedOption = text }
                    )
                    Text(text = text, modifier = Modifier.padding(start = 8.dp))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // ImageButton equivalent
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("More Info (ImageButton): ")
                IconButton(onClick = { 
                    Toast.makeText(this@BasicViewsActivity, "ImageButton Clicked!", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(imageVector = Icons.Default.Info, contentDescription = "Info", tint = MaterialTheme.colorScheme.primary)
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Button equivalent
            Button(
                onClick = { 
                    Toast.makeText(this@BasicViewsActivity, "Form Submitted Successfully!", Toast.LENGTH_LONG).show()
                },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Submit Form (Button)")
            }
        }
    }
}
