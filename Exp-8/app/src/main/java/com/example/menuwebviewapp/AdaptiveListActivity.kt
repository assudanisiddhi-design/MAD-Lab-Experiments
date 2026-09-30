package com.example.menuwebviewapp

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

data class Course(
    val title: String,
    val code: String,
    val icon: ImageVector,
    val description: String,
    val referenceUrl: String
)

class AdaptiveListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val courses = listOf(
            Course("Mobile App Development", "22MCA31", Icons.Default.Info, "Build Android apps using Kotlin & Compose.", "https://developer.android.com"),
            Course("Data Science with Python", "22MCA32", Icons.Default.Menu, "Analyze and visualize complex datasets.", "https://www.python.org"),
            Course("Cloud Computing", "22MCA33", Icons.Default.Settings, "Learn AWS, Azure & cloud deployment.", "https://aws.amazon.com"),
            Course("Cyber Security", "22MCA34", Icons.Default.Lock, "Network security & cryptography fundamentals.", "https://www.cisa.gov"),
            Course("AI & Machine Learning", "22MCA35", Icons.Default.Star, "Neural networks and predictive models.", "https://www.tensorflow.org"),
            Course("Big Data Analytics", "22MCA36", Icons.Default.List, "Processing data with Hadoop and Spark.", "https://spark.apache.org"),
            Course("Internet of Things", "22MCA37", Icons.Default.Add, "Connecting hardware devices to internet.", "https://www.arduino.cc"),
            Course("Software Engineering", "22MCA38", Icons.Default.Build, "Project management & SDLC methodologies.", "https://www.agilealliance.org")
        )

        setContent {
            MaterialTheme {
                Scaffold(
                    topBar = {
                        AppTopBar(
                            title = "Adaptive List Showcase",
                            context = this@AdaptiveListActivity
                        )
                    }
                ) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        AdaptiveLayout(courses)
                    }
                }
            }
        }
    }

    @Composable
    fun AdaptiveLayout(courses: List<Course>) {
        BoxWithConstraints {
            val isWideScreen = maxWidth > 600.dp

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = "MCA Curriculum 2026",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "Adaptive List with Context Menus & Web References",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                CourseList(courses)
            }
        }
    }

    @Composable
    fun CourseList(courses: List<Course>) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(courses) { course ->
                CourseCard(course)
            }
        }
    }

    @Composable
    fun CourseCard(course: Course) {
        var menuExpanded by remember { mutableStateOf(false) }

        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(52.dp)
                        .padding(4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = course.icon,
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
                        text = "Code: ${course.code}",
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

                // Item Context / Popup Menu
                Box {
                    IconButton(onClick = { menuExpanded = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Course Options")
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
                                val intent = Intent(this@AdaptiveListActivity, WebViewActivity::class.java).apply {
                                    putExtra("INITIAL_URL", course.referenceUrl)
                                }
                                startActivity(intent)
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Course Info") },
                            leadingIcon = { Icon(Icons.Default.Info, contentDescription = null) },
                            onClick = {
                                menuExpanded = false
                                Toast.makeText(this@AdaptiveListActivity, "${course.title} (${course.code})", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                }
            }
        }
    }
}
