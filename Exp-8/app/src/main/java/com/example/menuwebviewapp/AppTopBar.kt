package com.example.menuwebviewapp

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    context: Context,
    userName: String = "",
    userUsn: String = "",
    showBackButton: Boolean = true
) {
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text(text = title) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        actions = {
            Box {
                IconButton(onClick = { expanded = true }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Options Menu"
                    )
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Home Screen") },
                        leadingIcon = { Icon(Icons.Default.Home, contentDescription = null) },
                        onClick = {
                            expanded = false
                            context.startActivity(Intent(context, HomeActivity::class.java))
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Dashboard") },
                        leadingIcon = { Icon(Icons.Default.Menu, contentDescription = null) },
                        onClick = {
                            expanded = false
                            val intent = Intent(context, DashboardActivity::class.java).apply {
                                putExtra("USER_NAME", userName)
                                putExtra("USER_USN", userUsn)
                            }
                            context.startActivity(intent)
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Adaptive List") },
                        leadingIcon = { Icon(Icons.Default.List, contentDescription = null) },
                        onClick = {
                            expanded = false
                            context.startActivity(Intent(context, AdaptiveListActivity::class.java))
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Web Portal (WebView)") },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                        onClick = {
                            expanded = false
                            val intent = Intent(context, WebViewActivity::class.java).apply {
                                putExtra("INITIAL_URL", "https://www.google.com")
                            }
                            context.startActivity(intent)
                        }
                    )
                    HorizontalDivider()
                    DropdownMenuItem(
                        text = { Text("Student Profile") },
                        leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                        onClick = {
                            expanded = false
                            val intent = Intent(context, StudentDetailsActivity::class.java).apply {
                                putExtra("USER_NAME", userName)
                                putExtra("USER_USN", userUsn)
                            }
                            context.startActivity(intent)
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Account Details") },
                        leadingIcon = { Icon(Icons.Default.AccountCircle, contentDescription = null) },
                        onClick = {
                            expanded = false
                            val intent = Intent(context, AccountActivity::class.java).apply {
                                putExtra("USER_NAME", userName)
                            }
                            context.startActivity(intent)
                        }
                    )
                    HorizontalDivider()
                    DropdownMenuItem(
                        text = { Text("Logout") },
                        leadingIcon = { Icon(Icons.Default.ExitToApp, contentDescription = null) },
                        onClick = {
                            expanded = false
                            val intent = Intent(context, LoginActivity::class.java).apply {
                                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            }
                            context.startActivity(intent)
                        }
                    )
                }
            }
        }
    )
}
