package com.example.googlemapsapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

data class MapPoint(
    val title: String,
    val subtitle: String,
    val lat: Double,
    val lng: Double,
    val zoom: Int = 15
)

class MapsActivity : ComponentActivity() {
    private var webView: WebView? = null

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val landmarks = listOf(
            MapPoint("MCA Dept & MAD Lab", "Main Campus IT Block", 12.9716, 77.5946),
            MapPoint("University Central Library", "Academic Zone Block B", 12.9740, 77.5970),
            MapPoint("Computer Center", "High Performance Computing Lab", 12.9700, 77.5920),
            MapPoint("Student Activity Center", "Auditorium & Sports Complex", 12.9680, 77.5960)
        )

        setContent {
            var selectedPoint by remember { mutableStateOf(landmarks[0]) }
            var mapType by remember { mutableStateOf("m") } // m: Roadmap, k: Satellite, p: Terrain
            var progress by remember { mutableFloatStateOf(0f) }
            var isLoading by remember { mutableStateOf(false) }

            fun getMapHtml(point: MapPoint, type: String): String {
                return """
                    <!DOCTYPE html>
                    <html>
                    <head>
                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <style>
                            body, html { margin:0; padding:0; height:100%; width:100%; overflow:hidden; }
                            iframe { border:0; width:100%; height:100%; }
                        </style>
                    </head>
                    <body>
                        <iframe 
                            src="https://maps.google.com/maps?q=${point.lat},${point.lng}&t=$type&z=${point.zoom}&ie=UTF8&iwloc=&output=embed" 
                            allowfullscreen>
                        </iframe>
                    </body>
                    </html>
                """.trimIndent()
            }

            MaterialTheme {
                Scaffold(
                    topBar = {
                        AppTopBar(
                            title = "Google Maps API Showcase",
                            context = this@MapsActivity
                        )
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        Card(
                            shape = RoundedCornerShape(0.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Text(
                                    text = "📍 ${selectedPoint.title}",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                                Text(
                                    text = "${selectedPoint.subtitle} | Lat: ${selectedPoint.lat}, Lng: ${selectedPoint.lng}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer
                                )
                            }
                        }

                        if (isLoading && progress < 1.0f) {
                            LinearProgressIndicator(
                                progress = { progress },
                                modifier = Modifier.fillMaxWidth(),
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                        ) {
                            AndroidView(
                                factory = { context ->
                                    WebView(context).apply {
                                        settings.javaScriptEnabled = true
                                        settings.domStorageEnabled = true
                                        settings.setSupportZoom(true)

                                        webViewClient = object : WebViewClient() {
                                            override fun onPageFinished(view: WebView?, url: String?) {
                                                super.onPageFinished(view, url)
                                                isLoading = false
                                            }
                                        }

                                        webChromeClient = object : WebChromeClient() {
                                            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                                                progress = newProgress / 100f
                                                isLoading = newProgress < 100
                                            }
                                        }

                                        loadDataWithBaseURL("https://maps.google.com", getMapHtml(selectedPoint, mapType), "text/html", "UTF-8", null)
                                        this@MapsActivity.webView = this
                                    }
                                },
                                update = { view ->
                                    view.loadDataWithBaseURL("https://maps.google.com", getMapHtml(selectedPoint, mapType), "text/html", "UTF-8", null)
                                },
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        Surface(
                            tonalElevation = 4.dp,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    text = "Select Landmark Location:",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.secondary,
                                    modifier = Modifier.padding(bottom = 6.dp)
                                )
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    landmarks.forEach { point ->
                                        FilterChip(
                                            selected = selectedPoint == point,
                                            onClick = { selectedPoint = point },
                                            label = { Text(point.title.take(12) + "...") },
                                            leadingIcon = {
                                                if (selectedPoint == point) {
                                                    Icon(Icons.Default.Place, contentDescription = null)
                                                }
                                            }
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text("Map Type:", style = MaterialTheme.typography.labelMedium)

                                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                        Button(
                                            onClick = { mapType = "m" },
                                            colors = if (mapType == "m") ButtonDefaults.buttonColors() else ButtonDefaults.outlinedButtonColors()
                                        ) {
                                            Text("Roadmap")
                                        }
                                        Button(
                                            onClick = { mapType = "k" },
                                            colors = if (mapType == "k") ButtonDefaults.buttonColors() else ButtonDefaults.outlinedButtonColors()
                                        ) {
                                            Text("Satellite")
                                        }
                                        Button(
                                            onClick = { mapType = "p" },
                                            colors = if (mapType == "p") ButtonDefaults.buttonColors() else ButtonDefaults.outlinedButtonColors()
                                        ) {
                                            Text("Terrain")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
