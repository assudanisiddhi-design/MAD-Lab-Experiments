package com.example.googlemapsapp

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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

class DashboardActivity : ComponentActivity() {
    private lateinit var prefsManager: PreferencesManager
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var notificationHelper: NotificationHelper
    private lateinit var contactsHelper: ContactsHelper

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            notificationHelper.showNotification("Success", "Notifications enabled for Experiment 11!")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefsManager = PreferencesManager(this)
        dbHelper = DatabaseHelper(this)
        notificationHelper = NotificationHelper(this)
        contactsHelper = ContactsHelper(this)

        setContent {
            val courseCount = dbHelper.getCourseCount()
            val contactsCount = contactsHelper.getDeviceContacts().size
            val name = prefsManager.getUserName()
            val usn = prefsManager.getUserUsn()

            MaterialTheme {
                Scaffold(
                    topBar = {
                        AppTopBar(
                            title = "Experiment 11 Dashboard",
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
                                .padding(16.dp)
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Welcome, $name",
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "USN: $usn | Exp 11 Google Maps API",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.secondary
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            // Status Card 1: Google Maps API
                            Card(
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text(
                                        text = "🗺️ Google Maps API Integration",
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text("Interactive Map Views with Landmarks, Markers & Map Types", style = MaterialTheme.typography.bodySmall)
                                }
                            }

                            Spacer(modifier = Modifier.height(6.dp))

                            // Status Card 2: Contacts ContentProvider
                            Card(
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text(
                                        text = "👥 Contacts ContentProvider Data Sharing",
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onTertiaryContainer
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text("Queried Contacts Available: $contactsCount records", style = MaterialTheme.typography.bodySmall)
                                }
                            }

                            Spacer(modifier = Modifier.height(6.dp))

                            // Status Card 3: SQLite Database
                            Card(
                                shape = RoundedCornerShape(16.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text(
                                        text = "🗄️ SQLite Database (MCA_Courses.db)",
                                        style = MaterialTheme.typography.titleMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.onSecondaryContainer
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text("Active Course Records: $courseCount items", style = MaterialTheme.typography.bodySmall)
                                }
                            }

                            Spacer(modifier = Modifier.height(14.dp))

                            ModernNavButton("Google Maps API Showcase", Icons.Default.Place) {
                                startActivity(Intent(this@DashboardActivity, MapsActivity::class.java))
                            }

                            ModernNavButton("Contacts ContentProvider (Share Data)", Icons.Default.Person) {
                                startActivity(Intent(this@DashboardActivity, ContactsActivity::class.java))
                            }

                            ModernNavButton("SQLite Course List (CRUD)", Icons.Default.List) {
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

                            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                            ModernNavButton("Student Profile (SharedPreferences)", Icons.Default.AccountBox) {
                                startActivity(Intent(this@DashboardActivity, StudentDetailsActivity::class.java))
                            }

                            ModernNavButton("Account Details", Icons.Default.AccountCircle) {
                                startActivity(Intent(this@DashboardActivity, AccountActivity::class.java))
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
                notificationHelper.showNotification("Hi $name", "Experiment 11: Google Maps API active!")
            } else {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        } else {
            notificationHelper.showNotification("Hi $name", "Experiment 11: Google Maps API active!")
        }
    }
}

@Composable
fun ModernNavButton(text: String, icon: ImageVector, onClick: () -> Unit) {
    FilledTonalButton(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(vertical = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = icon, contentDescription = null)
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = text, style = MaterialTheme.typography.titleMedium)
        }
    }
}
