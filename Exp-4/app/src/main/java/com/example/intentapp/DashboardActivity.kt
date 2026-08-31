package com.example.intentapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

class DashboardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = intent.getStringExtra("USER_NAME") ?: ""
        val usn = intent.getStringExtra("USER_USN") ?: ""

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(40.dp))
                        Text(
                            text = "Dashboard",
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Linked via Explicit Intents",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        
                        Spacer(modifier = Modifier.height(48.dp))
                        
                        ModernNavButton("Home", Icons.Default.Home) {
                            startActivity(Intent(this@DashboardActivity, HomeActivity::class.java))
                        }
                        ModernNavButton("Student Details", Icons.Default.Person) {
                            val intent = Intent(this@DashboardActivity, StudentDetailsActivity::class.java).apply {
                                putExtra("USER_NAME", name)
                                putExtra("USER_USN", usn)
                            }
                            startActivity(intent)
                        }
                        ModernNavButton("Account", Icons.Default.AccountCircle) {
                            val intent = Intent(this@DashboardActivity, AccountActivity::class.java).apply {
                                putExtra("USER_NAME", name)
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
fun ModernNavButton(text: String, icon: ImageVector, onClick: () -> Unit) {
    FilledTonalButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.width(20.dp))
            Text(text = text, style = MaterialTheme.typography.titleLarge)
        }
    }
}
