package com.example.contentproviderapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

class AccountActivity : ComponentActivity() {
    private lateinit var prefsManager: PreferencesManager
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var contactsHelper: ContactsHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefsManager = PreferencesManager(this)
        dbHelper = DatabaseHelper(this)
        contactsHelper = ContactsHelper(this)

        val name = prefsManager.getUserName()
        val count = dbHelper.getCourseCount()
        val contactsCount = contactsHelper.getDeviceContacts().size

        setContent {
            MaterialTheme {
                Scaffold(
                    topBar = {
                        AppTopBar(
                            title = "Account Overview",
                            context = this@AccountActivity,
                            userName = name
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
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.height(24.dp))
                            Box(
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.primaryContainer),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = null,
                                    modifier = Modifier.size(64.dp),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(text = name, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                            Text(text = "MCA Student - Experiment 10", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.secondary)

                            Spacer(modifier = Modifier.height(28.dp))

                            HorizontalDivider()
                            ListItem(
                                headlineContent = { Text("Contacts ContentProvider Sharing") },
                                supportingContent = { Text("Queried $contactsCount contacts via ContentResolver") }
                            )
                            HorizontalDivider()
                            ListItem(
                                headlineContent = { Text("SharedPreferences Persistence") },
                                supportingContent = { Text("Key-Value session storage & credentials") }
                            )
                            HorizontalDivider()
                            ListItem(
                                headlineContent = { Text("SQLite Relational Database") },
                                supportingContent = { Text("MCA_Courses.db storing $count active course items") }
                            )
                        }
                    }
                }
            }
        }
    }
}
