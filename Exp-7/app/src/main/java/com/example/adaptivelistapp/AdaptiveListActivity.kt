package com.example.adaptivelistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

data class Course(val title: String, val code: String, val icon: ImageVector, val description: String)

class AdaptiveListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val courses = listOf(
            Course("Mobile App Development", "22MCA31", Icons.Default.Info, "Build Android apps using Kotlin and Compose."),
            Course("Data Science with Python", "22MCA32", Icons.Default.Menu, "Analyze and visualize complex datasets."),
            Course("Cloud Computing", "22MCA33", Icons.Default.Settings, "Learn AWS, Azure and cloud deployment strategies."),
            Course("Cyber Security", "22MCA34", Icons.Default.Lock, "Fundamentals of network security and cryptography."),
            Course("AI & Machine Learning", "22MCA35", Icons.Default.Star, "Deep dive into neural networks and predictive models."),
            Course("Big Data Analytics", "22MCA36", Icons.Default.List, "Processing massive data with Hadoop and Spark."),
            Course("Internet of Things", "22MCA37", Icons.Default.Add, "Connecting hardware devices to the internet."),
            Course("Software Engineering", "22MCA38", Icons.Default.Build, "Project management and SDLC methodologies.")
        )

        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    AdaptiveLayout(courses)
                }
            }
        }
    }

    @Composable
    fun AdaptiveLayout(courses: List<Course>) {
        // BoxWithConstraints allows us to build an adaptive UI based on screen size/orientation
        BoxWithConstraints {
            val isWideScreen = maxWidth > 600.dp

            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Text(
                    text = "MCA Curriculum 2026",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Adaptive List & Image Showcase",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                if (isWideScreen) {
                    // Two-column grid logic could go here for very wide screens (tablets)
                    // For now, we'll focus on a professional adaptive list
                    CourseList(courses)
                } else {
                    CourseList(courses)
                }
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
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // ImageView equivalent (Icon used for simplicity/professional vector look)
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = course.icon,
                        contentDescription = course.title,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                
                Spacer(modifier = Modifier.width(16.dp))
                
                Column {
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
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = course.description,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 2
                    )
                }
            }
        }
    }
}
