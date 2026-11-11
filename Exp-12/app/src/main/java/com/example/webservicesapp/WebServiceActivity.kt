package com.example.webservicesapp

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

class WebServiceActivity : ComponentActivity() {
    private val httpHelper = HttpWebServiceHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var endpointUrl by remember { mutableStateOf("https://jsonplaceholder.typicode.com/posts") }
            var webResponse by remember { mutableStateOf<WebServiceResponse?>(null) }
            var isLoading by remember { mutableStateOf(false) }
            val coroutineScope = rememberCoroutineScope()

            fun loadData() {
                isLoading = true
                coroutineScope.launch {
                    val result = httpHelper.fetchPostsFromWebService(endpointUrl.trim())
                    webResponse = result
                    isLoading = false
                    Toast.makeText(this@WebServiceActivity, "HTTP Request Completed (${result.statusCode})", Toast.LENGTH_SHORT).show()
                }
            }

            LaunchedEffect(Unit) {
                loadData()
            }

            MaterialTheme {
                Scaffold(
                    topBar = {
                        AppTopBar(
                            title = "Web Services (HTTP GET)",
                            context = this@WebServiceActivity
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
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "REST Web Services Access",
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Asynchronous HTTP Requests using HttpURLConnection & JSON Parsing",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.secondary,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )

                            // Endpoint URL Bar & Fetch Button
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                OutlinedTextField(
                                    value = endpointUrl,
                                    onValueChange = { endpointUrl = it },
                                    label = { Text("REST API Endpoint URL") },
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(56.dp),
                                    shape = RoundedCornerShape(12.dp),
                                    singleLine = true,
                                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) }
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Button(
                                    onClick = { loadData() },
                                    modifier = Modifier.height(56.dp),
                                    shape = RoundedCornerShape(12.dp),
                                    enabled = !isLoading
                                ) {
                                    Text("GET")
                                }
                            }

                            // Loading Progress Bar
                            if (isLoading) {
                                LinearProgressIndicator(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 12.dp),
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }

                            // HTTP Status Card
                            webResponse?.let { resp ->
                                Card(
                                    shape = RoundedCornerShape(14.dp),
                                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 12.dp)
                                ) {
                                    Column(modifier = Modifier.padding(14.dp)) {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(
                                                text = "STATUS: ${resp.statusCode} ${resp.responseMessage}",
                                                style = MaterialTheme.typography.titleMedium,
                                                fontWeight = FontWeight.Bold,
                                                color = MaterialTheme.colorScheme.onPrimaryContainer
                                            )
                                            Text(
                                                text = "${resp.responseTimeMs} ms",
                                                style = MaterialTheme.typography.labelMedium,
                                                color = MaterialTheme.colorScheme.primary
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Text(
                                            text = "Parsed ${resp.items.size} JSON records from response payload.",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onPrimaryContainer
                                        )
                                    }
                                }
                            }

                            // REST Web Service Items List
                            webResponse?.items?.let { items ->
                                LazyColumn(
                                    verticalArrangement = Arrangement.spacedBy(10.dp),
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    items(items, key = { it.id }) { item ->
                                        WebServiceCard(
                                            item = item,
                                            onShare = { sharePayload(item) },
                                            onOpenWeb = { url ->
                                                val intent = Intent(this@WebServiceActivity, WebViewActivity::class.java).apply {
                                                    putExtra("INITIAL_URL", url)
                                                }
                                                startActivity(intent)
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun sharePayload(item: WebServiceItem) {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "REST API Payload #${item.id}: ${item.title}")
            putExtra(Intent.EXTRA_TEXT, "Title: ${item.title}\n\nContent:\n${item.body}\n\nURL: ${item.url}")
        }
        startActivity(Intent.createChooser(shareIntent, "Share Web Service Payload Via"))
    }

    @Composable
    fun WebServiceCard(
        item: WebServiceItem,
        onShare: () -> Unit,
        onOpenWeb: (String) -> Unit
    ) {
        var menuExpanded by remember { mutableStateOf(false) }

        Card(
            shape = RoundedCornerShape(14.dp),
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
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    modifier = Modifier.size(42.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = "#${item.id}",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = item.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = item.body,
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 2
                    )
                }

                Box {
                    IconButton(onClick = { menuExpanded = true }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "Web Service Item Options")
                    }

                    DropdownMenu(
                        expanded = menuExpanded,
                        onDismissRequest = { menuExpanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Open API Endpoint in WebView") },
                            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                            onClick = {
                                menuExpanded = false
                                onOpenWeb(item.url)
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Share REST Payload") },
                            leadingIcon = { Icon(Icons.Default.Share, contentDescription = null) },
                            onClick = {
                                menuExpanded = false
                                onShare()
                            }
                        )
                    }
                }
            }
        }
    }
}
