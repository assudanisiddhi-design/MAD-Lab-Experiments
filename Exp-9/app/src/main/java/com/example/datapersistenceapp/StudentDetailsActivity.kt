package com.example.datapersistenceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

class StudentDetailsActivity : ComponentActivity() {
    private lateinit var prefsManager: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefsManager = PreferencesManager(this)

        val name = prefsManager.getUserName()
        val usn = prefsManager.getUserUsn()
        val rememberMe = prefsManager.isRememberMe()
        val lastLogin = prefsManager.getLastLogin()

        setContent {
            MaterialTheme {
                Scaffold(
                    topBar = {
                        AppTopBar(
                            title = "Student Profile",
                            context = this@StudentDetailsActivity,
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
                            modifier = Modifier.padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Card(
                                shape = RoundedCornerShape(24.dp),
                                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(
                                    modifier = Modifier.padding(28.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        "SharedPreferences Profile",
                                        style = MaterialTheme.typography.headlineMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                    Spacer(modifier = Modifier.height(20.dp))
                                    DetailItem("Student Name", name)
                                    HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp))
                                    DetailItem("USN Number", usn)
                                    HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp))
                                    DetailItem("Remember Session", if (rememberMe) "Enabled (Persisted)" else "Disabled")
                                    HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp))
                                    DetailItem("Last Login Timestamp", lastLogin)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DetailItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.secondary)
        Text(text = value, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Medium)
    }
}
