package com.example.lifecycleapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = intent.getStringExtra("USER_NAME") ?: "Unknown"
        val usn = intent.getStringExtra("USER_USN") ?: "Unknown"

        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Welcome, $name", style = MaterialTheme.typography.headlineSmall)
                        Spacer(modifier = Modifier.height(32.dp))
                        
                        DashboardButton("Home") {
                            startActivity(Intent(this@DashboardActivity, HomeActivity::class.java))
                        }
                        DashboardButton("Activity Details") {
                            startActivity(Intent(this@DashboardActivity, LifecycleActivity::class.java))
                        }
                        DashboardButton("Account") {
                            val intent = Intent(this@DashboardActivity, AccountActivity::class.java).apply {
                                putExtra("USER_NAME", name)
                                putExtra("USER_USN", usn)
                            }
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DashboardButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
    ) {
        Text(text)
    }
}
