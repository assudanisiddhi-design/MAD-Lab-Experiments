package com.example.lifecycleapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.example.lifecycleapp.ui.components.CustomToaster
import kotlinx.coroutines.launch

class LifecycleActivity : ComponentActivity() {
    private val TAG = "LifecycleActivity"
    private val lifecycleEvents = mutableStateListOf<String>()
    private val snackbarHostState = SnackbarHostState()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addEvent("onCreate")
        
        setContent {
            MaterialTheme {
                Scaffold(
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState) { data ->
                            CustomToaster(message = data.visuals.message)
                        }
                    }
                ) { padding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text("Activity Lifecycle Tracker", style = MaterialTheme.typography.headlineSmall)
                            Spacer(modifier = Modifier.height(16.dp))
                            
                            Button(
                                onClick = { addEvent("Manual Trigger") },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Test Professional Toaster")
                            }
                            
                            Spacer(modifier = Modifier.height(16.dp))
                            Text("Observe the method calls below:", style = MaterialTheme.typography.bodyMedium)
                            Spacer(modifier = Modifier.height(8.dp))
                            
                            LazyColumn(modifier = Modifier.weight(1f)) {
                                items(lifecycleEvents) { event ->
                                    Text("- $event", modifier = Modifier.padding(4.dp))
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        addEvent("onStart")
    }

    override fun onResume() {
        super.onResume()
        addEvent("onResume")
    }

    override fun onPause() {
        super.onPause()
        addEvent("onPause")
    }

    override fun onStop() {
        super.onStop()
        addEvent("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called")
    }

    override fun onRestart() {
        super.onRestart()
        addEvent("onRestart")
    }

    private fun addEvent(method: String) {
        val message = "$method called"
        Log.d(TAG, message)
        lifecycleEvents.add(message)
        
        // Use Lifecycle scope to show snackbar since we are in Activity context
        lifecycleScope.launch {
            snackbarHostState.showSnackbar(message)
        }
    }
}
