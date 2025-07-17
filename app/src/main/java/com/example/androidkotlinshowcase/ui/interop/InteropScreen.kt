package com.example.androidkotlinshowcase.ui.interop

import android.widget.TextView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

@Composable
fun InteropScreen(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                
                Text(
                    text = "Interop",
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center
                )
                
                Text(
                    text = "Integration with legacy Android Views and advanced layouts",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        
        item {
            AndroidViewDemo()
        }
        
        item {
            InteractiveAndroidViewDemo()
        }
        
        item {
            MixedLayoutDemo()
        }
        
        item {
            ComposeInAndroidViewDemo()
        }
    }
}

@Composable
private fun AndroidViewDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "AndroidView Basic",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Embedding a TextView in Compose:",
                style = MaterialTheme.typography.titleSmall
            )
            
            // Basic AndroidView usage
            AndroidView(
                factory = { context ->
                    TextView(context).apply {
                        text = "This is a native Android TextView!"
                        textSize = 16f
                        setBackgroundColor(Color.LightGray.toArgb())
                        setPadding(32, 16, 32, 16)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )
            
            Text(
                text = "AndroidView embeds traditional Android Views",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun InteractiveAndroidViewDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Interactive AndroidView",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            var composeText by remember { mutableStateOf("Hello from Compose!") }
            
            // Compose TextField
            OutlinedTextField(
                value = composeText,
                onValueChange = { composeText = it },
                label = { Text("Compose TextField") },
                modifier = Modifier.fillMaxWidth()
            )
            
            Text(
                text = "TextView updates from Compose state:",
                style = MaterialTheme.typography.titleSmall
            )
            
            // AndroidView that updates when Compose state changes
            AndroidView(
                factory = { context ->
                    TextView(context).apply {
                        textSize = 14f
                        setBackgroundColor(Color.Cyan.copy(alpha = 0.3f).toArgb())
                        setPadding(24, 12, 24, 12)
                    }
                },
                update = { textView ->
                    textView.text = "Updated: $composeText"
                },
                modifier = Modifier.fillMaxWidth()
            )
            
            Text(
                text = "AndroidView can respond to Compose state changes",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun MixedLayoutDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Mixed Layout",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Compose + AndroidView in the same layout:",
                style = MaterialTheme.typography.titleSmall
            )
            
            // Mixed layout with Compose and AndroidView
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Compose side
                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Compose",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                        
                        Button(
                            onClick = { /* Handle click */ }
                        ) {
                            Text("Compose Button")
                        }
                    }
                }
                
                // AndroidView side
                AndroidView(
                    factory = { context ->
                        TextView(context).apply {
                            text = "AndroidView\nSide"
                            textSize = 14f
                            textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                            setBackgroundColor(Color.Yellow.copy(alpha = 0.3f).toArgb())
                            setPadding(24, 24, 24, 24)
                        }
                    },
                    modifier = Modifier.weight(1f)
                )
            }
            
            Text(
                text = "Seamless integration of Compose and View systems",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ComposeInAndroidViewDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Performance Considerations",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                InfoCard(
                    title = "✅ Good Practices",
                    content = "• Use AndroidView for complex existing Views\n• Minimize AndroidView updates\n• Cache View instances when possible",
                    color = MaterialTheme.colorScheme.primaryContainer
                )
                
                InfoCard(
                    title = "⚠️ Considerations",
                    content = "• AndroidView can impact performance\n• Compose state changes trigger View updates\n• Test on different devices",
                    color = MaterialTheme.colorScheme.tertiaryContainer
                )
                
                InfoCard(
                    title = "🔄 Migration Tips",
                    content = "• Gradually migrate Views to Compose\n• Use AndroidView for gradual adoption\n• Consider ComposeView for reverse integration",
                    color = MaterialTheme.colorScheme.secondaryContainer
                )
            }
        }
    }
}

@Composable
private fun InfoCard(
    title: String,
    content: String,
    color: Color
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = color
        )
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = content,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}
