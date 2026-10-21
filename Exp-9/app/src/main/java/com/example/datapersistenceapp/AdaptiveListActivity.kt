package com.example.datapersistenceapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

class AdaptiveListActivity : ComponentActivity() {
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dbHelper = DatabaseHelper(this)

        setContent {
            var courses by remember { mutableStateOf(dbHelper.getAllCourses()) }
            var showAddDialog by remember { mutableStateOf(false) }

            fun refreshCourses() {
                courses = dbHelper.getAllCourses()
            }

            MaterialTheme {
                Scaffold(
                    topBar = {
                        AppTopBar(
                            title = "SQLite Adaptive List",
                            context = this@AdaptiveListActivity
                        )
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = { showAddDialog = true },
                            containerColor = MaterialTheme.colorScheme.primary
                        ) {
                            Icon(Icons.Default.Add, contentDescription = "Add Course to SQLite")
                        }
                    }
                ) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "MCA Curriculum (SQLite Backed)",
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 2.dp)
                            )
                            Text(
                                text = "Total Records in SQLite: ${courses.size} items",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )

                            if (courses.isEmpty()) {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text("No records in SQLite database. Tap + to add!")
                                }
                            } else {
                                LazyColumn(
                                    verticalArrangement = Arrangement.spacedBy(10.dp),
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    items(courses, key = { it.id }) { course ->
                                        CourseCard(
                                            course = course,
                                            onDelete = {
                                                dbHelper.deleteCourse(course.id)
                                                Toast.makeText(this@AdaptiveListActivity, "Deleted '${course.title}' from SQLite!", Toast.LENGTH_SHORT).show()
                                                refreshCourses()
                                            },
                                            onOpenWeb = { url ->
                                                val intent = Intent(this@AdaptiveListActivity, WebViewActivity::class.java).apply {
                                                    putExtra("INITIAL_URL", url)
                                                }
                                                startActivity(intent)
                                            }
                                        )
                                    }
                                }
                            }
                        }

                        // Add Course to SQLite Dialog
                        if (showAddDialog) {
                            AddCourseDialog(
                                onDismiss = { showAddDialog = false },
                                onSave = { title, code, desc, url ->
                                    val rowId = dbHelper.insertCourse(title, code, "info", desc, url)
                                    if (rowId != -1L) {
                                        Toast.makeText(this@AdaptiveListActivity, "Saved to SQLite Database!", Toast.LENGTH_SHORT).show()
                                        refreshCourses()
                                    }
                                    showAddDialog = false
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun CourseCard(
        course: CourseItem,
        onDelete: () -> Unit,
        onOpenWeb: (String) -> Unit
    ) {
        var menuExpanded by remember { mutableStateOf(false) }

        val iconVector: ImageVector = when (course.iconType) {
            "menu" -> Icons.Default.Menu
            "settings" -> Icons.Default.Settings
            "lock" -> Icons.Default.Lock
            "star" -> Icons.Default.Star
            "list" -> Icons.Default.List
            else -> Icons.Default.Info
        }

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(14.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .padding(4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = iconVector,
                        contentDescription = course.title,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = course.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Code: ${course.code} | ID: #${course.id}",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = course.description,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 2
                    )
                }

                Box {
                    IconButton(onClick = { menuExpanded = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Item Options")
                    }

                    DropdownMenu(
                        expanded = menuExpanded,
                        onDismissRequest = { menuExpanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Open Web Reference") },
                            leadingIcon = { Icon(Icons.Default.Share, contentDescription = null) },
                            onClick = {
                                menuExpanded = false
                                onOpenWeb(course.referenceUrl)
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Delete Record (SQLite)") },
                            leadingIcon = { Icon(Icons.Default.Delete, contentDescription = null) },
                            onClick = {
                                menuExpanded = false
                                onDelete()
                            }
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun AddCourseDialog(
        onDismiss: () -> Unit,
        onSave: (String, String, String, String) -> Unit
    ) {
        var title by remember { mutableStateOf("") }
        var code by remember { mutableStateOf("") }
        var desc by remember { mutableStateOf("") }
        var url by remember { mutableStateOf("https://") }

        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Add Course to SQLite DB") },
            text = {
                Column {
                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("Course Title") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = code,
                        onValueChange = { code = it },
                        label = { Text("Course Code") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = desc,
                        onValueChange = { desc = it },
                        label = { Text("Description") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = url,
                        onValueChange = { url = it },
                        label = { Text("Reference URL") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (title.isNotBlank() && code.isNotBlank()) {
                            onSave(title, code, desc.ifBlank { "Custom MCA course" }, url.ifBlank { "https://www.google.com" })
                        }
                    }
                ) {
                    Text("Save to SQLite")
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text("Cancel")
                }
            }
        )
    }
}
