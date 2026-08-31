package com.example.notificationapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var name by remember { mutableStateOf("") }
            var usn by remember { mutableStateOf("") }

            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Authentication",
                            style = MaterialTheme.typography.displaySmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "Experiment 5 - Notifications",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.height(48.dp))
                        
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Enter Name") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        OutlinedTextField(
                            value = usn,
                            onValueChange = { usn = it },
                            label = { Text("Enter USN") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        )
                        Spacer(modifier = Modifier.height(32.dp))
                        
                        Button(
                            onClick = {
                                if (name.isNotBlank() && usn.isNotBlank()) {
                                    val intent = Intent(this@LoginActivity, DashboardActivity::class.java).apply {
                                        putExtra("USER_NAME", name)
                                        putExtra("USER_USN", usn)
                                    }
                                    startActivity(intent)
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text("Login to Dashboard", style = MaterialTheme.typography.titleMedium)
                        }
                    }
                }
            }
        }
    }
}
