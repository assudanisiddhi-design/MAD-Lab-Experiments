package com.example.menuwebviewapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
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

class WebViewActivity : ComponentActivity() {
    private var webView: WebView? = null

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val initialUrl = intent.getStringExtra("INITIAL_URL") ?: "https://www.google.com"

        setContent {
            var urlText by remember { mutableStateOf(initialUrl) }
            var currentUrl by remember { mutableStateOf(initialUrl) }
            var progress by remember { mutableFloatStateOf(0f) }
            var isLoading by remember { mutableStateOf(false) }
            var canGoBack by remember { mutableStateOf(false) }
            var canGoForward by remember { mutableStateOf(false) }
            var menuExpanded by remember { mutableStateOf(false) }

            BackHandler(enabled = canGoBack) {
                webView?.goBack()
            }

            MaterialTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = "Web Portal",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold
                                )
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                            ),
                            actions = {
                                IconButton(onClick = { webView?.reload() }) {
                                    Icon(Icons.Default.Refresh, contentDescription = "Refresh")
                                }
                                Box {
                                    IconButton(onClick = { menuExpanded = true }) {
                                        Icon(Icons.Default.MoreVert, contentDescription = "Web Options")
                                    }
                                    DropdownMenu(
                                        expanded = menuExpanded,
                                        onDismissRequest = { menuExpanded = false }
                                    ) {
                                        DropdownMenuItem(
                                            text = { Text("Google") },
                                            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                                            onClick = {
                                                menuExpanded = false
                                                currentUrl = "https://www.google.com"
                                                urlText = currentUrl
                                            }
                                        )
                                        DropdownMenuItem(
                                            text = { Text("Wikipedia") },
                                            leadingIcon = { Icon(Icons.Default.Info, contentDescription = null) },
                                            onClick = {
                                                menuExpanded = false
                                                currentUrl = "https://www.wikipedia.org"
                                                urlText = currentUrl
                                            }
                                        )
                                        DropdownMenuItem(
                                            text = { Text("Kotlin Documentation") },
                                            leadingIcon = { Icon(Icons.Default.Star, contentDescription = null) },
                                            onClick = {
                                                menuExpanded = false
                                                currentUrl = "https://kotlinlang.org/docs/home.html"
                                                urlText = currentUrl
                                            }
                                        )
                                        DropdownMenuItem(
                                            text = { Text("Android Developers") },
                                            leadingIcon = { Icon(Icons.Default.Build, contentDescription = null) },
                                            onClick = {
                                                menuExpanded = false
                                                currentUrl = "https://developer.android.com"
                                                urlText = currentUrl
                                            }
                                        )
                                        HorizontalDivider()
                                        DropdownMenuItem(
                                            text = { Text("Clear Cache") },
                                            leadingIcon = { Icon(Icons.Default.Clear, contentDescription = null) },
                                            onClick = {
                                                menuExpanded = false
                                                webView?.clearCache(true)
                                            }
                                        )
                                    }
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        // URL Address Bar
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            OutlinedTextField(
                                value = urlText,
                                onValueChange = { urlText = it },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(56.dp),
                                shape = RoundedCornerShape(12.dp),
                                singleLine = true,
                                placeholder = { Text("Enter URL e.g. https://...") },
                                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Button(
                                onClick = {
                                    var formattedUrl = urlText.trim()
                                    if (!formattedUrl.startsWith("http://") && !formattedUrl.startsWith("https://")) {
                                        formattedUrl = "https://$formattedUrl"
                                    }
                                    currentUrl = formattedUrl
                                },
                                modifier = Modifier.height(56.dp),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text("Go")
                            }
                        }

                        // Progress Indicator
                        if (isLoading && progress < 1.0f) {
                            LinearProgressIndicator(
                                progress = { progress },
                                modifier = Modifier.fillMaxWidth(),
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        // WebView Container
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
                                        settings.builtInZoomControls = true
                                        settings.displayZoomControls = false

                                        webViewClient = object : WebViewClient() {
                                            override fun onPageFinished(view: WebView?, url: String?) {
                                                super.onPageFinished(view, url)
                                                isLoading = false
                                                url?.let {
                                                    urlText = it
                                                }
                                                canGoBack = canGoBack()
                                                canGoForward = canGoForward()
                                            }
                                        }

                                        webChromeClient = object : WebChromeClient() {
                                            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                                                progress = newProgress / 100f
                                                isLoading = newProgress < 100
                                                canGoBack = view?.canGoBack() == true
                                                canGoForward = view?.canGoForward() == true
                                            }
                                        }

                                        loadUrl(currentUrl)
                                        this@WebViewActivity.webView = this
                                    }
                                },
                                update = { view ->
                                    if (view.url != currentUrl) {
                                        view.loadUrl(currentUrl)
                                    }
                                },
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        // WebView Bottom Navigation Bar Controls
                        Surface(
                            tonalElevation = 4.dp,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp, vertical = 4.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                IconButton(
                                    onClick = { webView?.goBack() },
                                    enabled = canGoBack
                                ) {
                                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                                }
                                IconButton(
                                    onClick = { webView?.goForward() },
                                    enabled = canGoForward
                                ) {
                                    Icon(Icons.Default.ArrowForward, contentDescription = "Forward")
                                }
                                IconButton(onClick = { webView?.reload() }) {
                                    Icon(Icons.Default.Refresh, contentDescription = "Reload")
                                }
                                IconButton(onClick = {
                                    currentUrl = "https://www.google.com"
                                    urlText = currentUrl
                                }) {
                                    Icon(Icons.Default.Home, contentDescription = "Home URL")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
