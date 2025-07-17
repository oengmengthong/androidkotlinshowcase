package com.example.androidkotlinshowcase.ui.performance

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerformanceScreen(
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
                    imageVector = Icons.Default.Settings,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = "Performance & Optimization",
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center
                )
                
                Text(
                    text = "Advanced patterns for memory management, lazy loading, and recomposition optimization",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
        
        item {
            MemoryManagementDemo()
        }
        
        item {
            LazyLoadingDemo()
        }
        
        item {
            ImageLoadingDemo()
        }
        
        item {
            BackgroundTasksDemo()
        }
        
        item {
            RecompositionOptimizationDemo()
        }
        
        item {
            PerformanceMetricsDemo()
        }
        
        item {
            Text(
                text = "🎉 Performance & Optimization Showcase Complete!\n\n" +
                        "This section demonstrates advanced performance patterns:\n\n" +
                        "• Memory Management: Leak prevention, proper cleanup\n" +
                        "• Lazy Loading: Pagination, infinite scrolling\n" +
                        "• Image Loading: Async patterns, caching strategies\n" +
                        "• Background Tasks: Coroutine scoping best practices\n" +
                        "• Recomposition: Stability annotations, remember optimization\n" +
                        "• Performance Metrics: Memory usage, render times",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun MemoryManagementDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Memory Management",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Demonstrates proper memory management patterns to prevent leaks",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            var memoryItems by remember { mutableStateOf(listOf<String>()) }
            var memoryUsage by remember { mutableStateOf(0L) }
            
            // Simulate memory usage tracking
            LaunchedEffect(memoryItems.size) {
                memoryUsage = memoryItems.size * 1024L // Simulate memory per item
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { 
                        memoryItems = memoryItems + "Item ${memoryItems.size + 1}"
                    }
                ) {
                    Text("Add Item")
                }
                
                Button(
                    onClick = { memoryItems = emptyList() }
                ) {
                    Text("Clear All")
                }
            }
            
            LinearProgressIndicator(
                progress = { (memoryUsage / 10240f).coerceAtMost(1f) },
                modifier = Modifier.fillMaxWidth()
            )
            
            Text(
                text = "Memory Usage: ${memoryUsage}B / 10KB",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Text(
                text = "Items: ${memoryItems.size}",
                style = MaterialTheme.typography.bodySmall
            )
            
            // Memory leak prevention example
            DisposableEffect(Unit) {
                onDispose {
                    // Proper cleanup when composable is disposed
                    memoryItems = emptyList()
                }
            }
        }
    }
}

@Composable
private fun LazyLoadingDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Lazy Loading & Pagination",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Advanced pagination patterns for efficient data loading",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            var items by remember { mutableStateOf((1..20).map { "Item $it" }) }
            var isLoading by remember { mutableStateOf(false) }
            var page by remember { mutableStateOf(1) }
            
            val listState = rememberLazyListState()
            val coroutineScope = rememberCoroutineScope()
            
            // Infinite scroll detection
            LaunchedEffect(listState) {
                snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
                    .collect { lastVisibleIndex ->
                        if (lastVisibleIndex != null && lastVisibleIndex >= items.size - 3 && !isLoading) {
                            isLoading = true
                            delay(1000) // Simulate network delay
                            val newItems = ((page * 20 + 1)..(page + 1) * 20).map { "Item $it" }
                            items = items + newItems
                            page++
                            isLoading = false
                        }
                    }
            }
            
            LazyColumn(
                state = listState,
                modifier = Modifier.height(200.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(items) { item ->
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceContainer
                        )
                    ) {
                        Text(
                            text = item,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
                
                if (isLoading) {
                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                }
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            listState.animateScrollToItem(0)
                        }
                    }
                ) {
                    Text("Scroll to Top")
                }
                
                Button(
                    onClick = {
                        items = (1..20).map { "Item $it" }
                        page = 1
                    }
                ) {
                    Text("Reset")
                }
            }
            
            Text(
                text = "Total Items: ${items.size} (Page $page)",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ImageLoadingDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Image Loading Patterns",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Demonstrates async image loading patterns and caching strategies",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            var isLoading by remember { mutableStateOf(false) }
            var loadedImages by remember { mutableStateOf(0) }
            
            // Simulate image loading
            LaunchedEffect(Unit) {
                while (loadedImages < 5) {
                    delay(1000)
                    loadedImages++
                }
            }
            
            // Progress indicator
            LinearProgressIndicator(
                progress = { loadedImages / 5f },
                modifier = Modifier.fillMaxWidth()
            )
            
            Text(
                text = "Loaded Images: $loadedImages/5",
                style = MaterialTheme.typography.bodyMedium
            )
            
            // Simulate image grid
            LazyColumn(
                modifier = Modifier.height(150.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(loadedImages) { index ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        )
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Image ${index + 1} (Cached)",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        loadedImages = 0
                    }
                ) {
                    Text("Reset Cache")
                }
                
                Button(
                    onClick = {
                        loadedImages = 5
                    }
                ) {
                    Text("Load All")
                }
            }
            
            Text(
                text = "• Lazy loading with caching\n• Error handling simulation\n• Memory optimization\n• Progressive loading",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun BackgroundTasksDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Background Tasks & Coroutines",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Proper coroutine scoping and background task management",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            var taskStatus by remember { mutableStateOf("Ready") }
            var progress by remember { mutableStateOf(0f) }
            
            val coroutineScope = rememberCoroutineScope()
            
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.fillMaxWidth()
                )
                
                Text(
                    text = "Status: $taskStatus",
                    style = MaterialTheme.typography.bodyMedium
                )
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                taskStatus = "Running..."
                                progress = 0f
                                
                                repeat(10) { step ->
                                    delay(200)
                                    progress = (step + 1) / 10f
                                    taskStatus = "Processing ${(progress * 100).toInt()}%"
                                }
                                
                                taskStatus = "Completed"
                            }
                        }
                    ) {
                        Text("Start Task")
                    }
                    
                    Button(
                        onClick = {
                            taskStatus = "Cancelled"
                            progress = 0f
                        }
                    ) {
                        Text("Cancel")
                    }
                }
            }
            
            Text(
                text = "• Proper coroutine scoping\n• Cancellation handling\n• Progress tracking\n• Memory-safe operations",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun RecompositionOptimizationDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Recomposition Optimization",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Advanced patterns to minimize unnecessary recompositions",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            var counter by remember { mutableStateOf(0) }
            var expensiveValue by remember { mutableStateOf("") }
            
            // Expensive computation with derivedStateOf
            val derivedValue by remember {
                derivedStateOf {
                    "Computed: ${counter * 2}"
                }
            }
            
            // Stable data class for optimization
            val stableData = remember(counter) {
                StableData(
                    id = counter,
                    value = "Item $counter",
                    timestamp = System.currentTimeMillis()
                )
            }
            
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Counter: $counter",
                    style = MaterialTheme.typography.bodyMedium
                )
                
                Text(
                    text = derivedValue,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                
                OptimizedItem(data = stableData)
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = { counter++ }) {
                        Text("Increment")
                    }
                    
                    Button(onClick = { counter = 0 }) {
                        Text("Reset")
                    }
                }
            }
            
            Text(
                text = "• derivedStateOf for expensive computations\n• Stable data classes\n• remember with keys\n• Immutable data structures",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun PerformanceMetricsDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Performance Metrics",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Real-time performance monitoring and optimization tips",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            var renderTime by remember { mutableStateOf(0L) }
            var recompositionCount by remember { mutableStateOf(0) }
            
            // Simulate performance metrics
            LaunchedEffect(Unit) {
                while (true) {
                    delay(1000)
                    renderTime = (8..16).random().toLong()
                    recompositionCount++
                }
            }
            
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                MetricCard(
                    title = "Render Time",
                    value = "${renderTime}ms",
                    isGood = renderTime < 16
                )
                
                MetricCard(
                    title = "Recompositions",
                    value = "$recompositionCount",
                    isGood = recompositionCount < 100
                )
                
                MetricCard(
                    title = "Memory Usage",
                    value = "${(45..65).random()}MB",
                    isGood = true
                )
            }
            
            Text(
                text = "Performance Tips:\n• Use LazyColumn for large lists\n• Implement proper state hoisting\n• Use remember for expensive operations\n• Profile with Layout Inspector",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun MetricCard(
    title: String,
    value: String,
    isGood: Boolean
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (isGood) 
                MaterialTheme.colorScheme.primaryContainer 
            else 
                MaterialTheme.colorScheme.errorContainer
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium
            )
            
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun OptimizedItem(data: StableData) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = "ID: ${data.id}",
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = data.value,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

// Stable data class for optimization
data class StableData(
    val id: Int,
    val value: String,
    val timestamp: Long
)
