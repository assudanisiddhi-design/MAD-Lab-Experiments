package com.example.notificationapp

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = intent.getStringExtra("USER_NAME") ?: "User"

        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(60.dp))
                        Box(
                            modifier = Modifier
                                .size(120.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primaryContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                modifier = Modifier.size(80.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(
                            text = name,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "MAD Lab Explorer",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        
                        Spacer(modifier = Modifier.height(48.dp))
                        
                        HorizontalDivider()
                        ListItem(
                            headlineContent = { Text("App Version") },
                            supportingContent = { Text("1.0.0 (Experiment 5)") }
                        )
                    }
                }
            }
        }
    }
}
