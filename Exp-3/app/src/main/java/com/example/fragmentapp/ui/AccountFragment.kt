package com.example.fragmentapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.fragmentapp.UserViewModel

class AccountFragment : Fragment() {
    private val viewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val name by viewModel.name.collectAsState()
                
                MaterialTheme {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        Column(
                            modifier = Modifier.padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.height(60.dp))
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
                            Text(
                                text = name,
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "MAD Lab Explorer",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.secondary
                            )
                            
                            Spacer(modifier = Modifier.height(48.dp))
                            
                            HorizontalDivider()
                            ListItem(
                                headlineContent = { Text("Profile Information") },
                                supportingContent = { Text("Edit your personal details") },
                                trailingContent = { Text(">", color = MaterialTheme.colorScheme.outline) }
                            )
                            ListItem(
                                headlineContent = { Text("Security") },
                                supportingContent = { Text("Password and authentication settings") },
                                trailingContent = { Text(">", color = MaterialTheme.colorScheme.outline) }
                            )
                            ListItem(
                                headlineContent = { Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) { Text("Logout") } },
                                colors = ListItemDefaults.colors(headlineColor = MaterialTheme.colorScheme.error)
                            )
                        }
                    }
                }
            }
        }
    }
}
