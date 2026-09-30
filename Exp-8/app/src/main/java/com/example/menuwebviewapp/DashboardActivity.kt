package com.example.menuwebviewapp

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
            notificationHelper.showNotification("Success", "Notifications enabled for Experiment 8!")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notificationHelper = NotificationHelper(this)

        val name = intent.getStringExtra("USER_NAME") ?: "Student"
        val usn = intent.getStringExtra("USER_USN") ?: "25MCAR0199"

        setContent {
            MaterialTheme {
                Scaffold(
                    topBar = {
                        AppTopBar(
                            title = "Dashboard",
                            context = this@DashboardActivity,
                            userName = name,
                            userUsn = usn
                        )
                    }
                ) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(24.dp)
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Welcome, $name",
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Experiment 8: Menus & WebView",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.secondary
                            )

                            Spacer(modifier = Modifier.height(24.dp))

                            ModernNavButton("Adaptive List Showcase", Icons.Default.List) {
                                startActivity(Intent(this@DashboardActivity, AdaptiveListActivity::class.java))
                            }

                            ModernNavButton("Web Portal (WebView)", Icons.Default.Search) {
                                val intent = Intent(this@DashboardActivity, WebViewActivity::class.java).apply {
                                    putExtra("INITIAL_URL", "https://www.google.com")
                                }
                                startActivity(intent)
                            }

                            ModernNavButton("Trigger Notification", Icons.Default.Notifications) {
                                checkAndShowNotification(name)
                            }

                            HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

                            ModernNavButton("Home Screen", Icons.Default.Home) {
                                startActivity(Intent(this@DashboardActivity, HomeActivity::class.java))
                            }

                            ModernNavButton("Student Details", Icons.Default.Person) {
                                val intent = Intent(this@DashboardActivity, StudentDetailsActivity::class.java).apply {
                                    putExtra("USER_NAME", name)
                                    putExtra("USER_USN", usn)
                                }
                                startActivity(intent)
                            }

                            ModernNavButton("Account Details", Icons.Default.AccountCircle) {
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

    private fun checkAndShowNotification(name: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                notificationHelper.showNotification("Hi $name", "Experiment 8: Menus and WebView are active!")
            } else {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        } else {
            notificationHelper.showNotification("Hi $name", "Experiment 8: Menus and WebView are active!")
        }
    }
}

@Composable
fun ModernNavButton(text: String, icon: ImageVector, onClick: () -> Unit) {
    FilledTonalButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = icon, contentDescription = null)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = text, style = MaterialTheme.typography.titleMedium)
        }
    }
}
