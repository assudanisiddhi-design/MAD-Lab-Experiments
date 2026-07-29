package com.example.lifecycleapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class LifecycleActivity : ComponentActivity() {
    private val TAG = "LifecycleActivity"
    private val lifecycleEvents = mutableStateListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addEvent("onCreate")
        
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Activity Lifecycle Tracker", style = MaterialTheme.typography.headlineSmall)
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
        // Toast might not show reliably here
    }

    override fun onRestart() {
        super.onRestart()
        addEvent("onRestart")
    }

    private fun addEvent(method: String) {
        val message = "$method called"
        Log.d(TAG, message)
        lifecycleEvents.add(message)
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
