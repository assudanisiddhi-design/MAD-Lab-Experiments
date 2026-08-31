package com.example.basicviewsapp

import android.content.Intent
import android.os.Bundle
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
            notificationHelper.showNotification("Success", "Notifications enabled!")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notificationHelper = NotificationHelper(this)
        
        val name = intent.getStringExtra("USER_NAME") ?: ""
        val usn = intent.getStringExtra("USER_USN") ?: ""

        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier.padding(24.dp).fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(40.dp))
                        Text("Dashboard", style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
                        Text("UI Components Showcase", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.secondary)
                        
                        Spacer(modifier = Modifier.height(40.dp))
                        
                        ModernNavButton("Basic Views Showcase", Icons.Default.Build) {
                            startActivity(Intent(this@DashboardActivity, BasicViewsActivity::class.java))
                        }
                        
                        ModernNavButton("Trigger Notification", Icons.Default.Notifications) {
                            checkAndShowNotification(name)
                        }

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
                notificationHelper.showNotification("Hi $name", "Experiment 6: View Showcase is ready!")
            } else {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        } else {
            notificationHelper.showNotification("Hi $name", "Experiment 6: View Showcase is ready!")
        }
    }
}

@Composable
fun ModernNavButton(text: String, icon: ImageVector, onClick: () -> Unit) {
    FilledTonalButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().height(64.dp).padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = icon, contentDescription = null)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = text)
        }
    }
}
