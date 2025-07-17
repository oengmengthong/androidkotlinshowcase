package com.example.androidkotlinshowcase.ui.runtime

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RuntimeScreen(
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
            Text(
                text = "Runtime & Effects",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )
        }
        
        item {
            Text(
                text = "State management, side effects, and coroutines integration",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        
        item {
            StateManagementDemo()
        }
        
        item {
            LaunchedEffectDemo()
        }
        
        item {
            DisposableEffectDemo()
        }
        
        item {
            ProduceStateDemo()
        }
        
        item {
            RememberCoroutineScopeDemo()
        }
    }
}

@Composable
private fun StateManagementDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "State Management",
                style = MaterialTheme.typography.titleMedium
            )
            
            var counter by remember { mutableIntStateOf(0) }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = { counter-- }) {
                    Text("-")
                }
                
                Text(
                    text = "Count: $counter",
                    style = MaterialTheme.typography.titleLarge
                )
                
                Button(onClick = { counter++ }) {
                    Text("+")
                }
            }
            
            Text(
                text = "Uses remember { mutableIntStateOf(0) }",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun LaunchedEffectDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "LaunchedEffect",
                style = MaterialTheme.typography.titleMedium
            )
            
            var timer by remember { mutableIntStateOf(0) }
            var isRunning by remember { mutableStateOf(false) }
            
            LaunchedEffect(isRunning) {
                while (isRunning) {
                    delay(1000)
                    timer++
                }
            }
            
            Text(
                text = "Timer: ${timer}s",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = { isRunning = !isRunning }) {
                    Text(if (isRunning) "Stop" else "Start")
                }
                
                Button(onClick = { 
                    timer = 0
                    isRunning = false
                }) {
                    Text("Reset")
                }
            }
            
            Text(
                text = "LaunchedEffect runs when isRunning changes",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun DisposableEffectDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "DisposableEffect",
                style = MaterialTheme.typography.titleMedium
            )
            
            var isActive by remember { mutableStateOf(false) }
            var status by remember { mutableStateOf("Not active") }
            
            if (isActive) {
                DisposableEffect(Unit) {
                    status = "Effect activated"
                    onDispose {
                        status = "Effect disposed"
                    }
                }
            }
            
            Text(
                text = "Status: $status",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            
            Button(
                onClick = { isActive = !isActive },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (isActive) "Deactivate" else "Activate")
            }
            
            Text(
                text = "DisposableEffect cleans up when component is disposed",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ProduceStateDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "produceState",
                style = MaterialTheme.typography.titleMedium
            )
            
            var input by remember { mutableStateOf("") }
            
            val processedText by produceState(initialValue = "", input) {
                if (input.isNotEmpty()) {
                    value = "Processing..."
                    delay(1000) // Simulate processing
                    value = "Processed: ${input.uppercase()}"
                } else {
                    value = ""
                }
            }
            
            OutlinedTextField(
                value = input,
                onValueChange = { input = it },
                label = { Text("Enter text") },
                modifier = Modifier.fillMaxWidth()
            )
            
            if (processedText.isNotEmpty()) {
                Text(
                    text = processedText,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
            
            Text(
                text = "produceState processes input with delay",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun RememberCoroutineScopeDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "rememberCoroutineScope",
                style = MaterialTheme.typography.titleMedium
            )
            
            val scope = rememberCoroutineScope()
            var message by remember { mutableStateOf("Ready") }
            
            Button(
                onClick = {
                    scope.launch {
                        message = "Starting async task..."
                        delay(2000)
                        message = "Async task completed!"
                        delay(2000)
                        message = "Ready"
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Run Async Task")
            }
            
            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            
            Text(
                text = "rememberCoroutineScope for manual coroutine management",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
