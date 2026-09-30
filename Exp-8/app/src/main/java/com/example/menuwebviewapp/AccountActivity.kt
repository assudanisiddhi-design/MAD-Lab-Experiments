package com.example.menuwebviewapp

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
        val name = intent.getStringExtra("USER_NAME") ?: "Siddhi Assudani"

        setContent {
            MaterialTheme {
                Scaffold(
                    topBar = {
                        AppTopBar(
                            title = "Account Details",
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
                            Spacer(modifier = Modifier.height(40.dp))
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
                            Text(text = name, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                            Text(text = "MCA Student - Experiment 8", style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.secondary)

                            Spacer(modifier = Modifier.height(36.dp))

                            HorizontalDivider()
                            ListItem(
                                headlineContent = { Text("Menu Integration") },
                                supportingContent = { Text("Options Menu & Context Popup Menus") }
                            )
                            HorizontalDivider()
                            ListItem(
                                headlineContent = { Text("WebView Integration") },
                                supportingContent = { Text("AndroidView with WebChromeClient & Progress") }
                            )
                        }
                    }
                }
            }
        }
    }
}
