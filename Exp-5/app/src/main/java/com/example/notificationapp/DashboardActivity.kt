package com.example.notificationapp

import android.content.Intent
import android.os.Bundle
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

class DashboardActivity : ComponentActivity() {
    private lateinit var notificationHelper: NotificationHelper

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            notificationHelper.showNotification("Success", "Notifications enabled successfully!")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notificationHelper = NotificationHelper(this)
        
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
                            text = "Notification Management",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        
                        Spacer(modifier = Modifier.height(40.dp))
                        
                        // New Notification Trigger Button
                        ModernNavButton("Trigger Notification", Icons.Default.Notifications) {
                            checkAndShowNotification(name)
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
                        
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

    private fun checkAndShowNotification(name: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                notificationHelper.showNotification("Welcome, $name", "Experiment 5: Notification demo is working!")
            } else {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        } else {
            notificationHelper.showNotification("Welcome, $name", "Experiment 5: Notification demo is working!")
        }
    }
}

@Composable
fun ModernNavButton(text: String, icon: ImageVector, onClick: () -> Unit) {
    FilledTonalButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(28.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = text, style = MaterialTheme.typography.titleMedium)
        }
    }
}
