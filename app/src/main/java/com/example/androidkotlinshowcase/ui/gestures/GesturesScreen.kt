package com.example.androidkotlinshowcase.ui.gestures

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun GesturesScreen(
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
                text = "Gestures",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )
        }
        
        item {
            Text(
                text = "Touch and input handling with gesture detection",
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        
        item {
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Clickable Gestures",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    var clickCount by remember { mutableIntStateOf(0) }
                    var longPressCount by remember { mutableIntStateOf(0) }
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Card(
                            modifier = Modifier
                                .size(100.dp)
                                .clickable { clickCount++ },
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                            )
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text("Tap Me")
                                    Text("$clickCount", style = MaterialTheme.typography.headlineSmall)
                                }
                            }
                        }
                        
                        Card(
                            modifier = Modifier
                                .size(100.dp)
                                .pointerInput(Unit) {
                                    detectTapGestures(
                                        onLongPress = { longPressCount++ }
                                    )
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer
                            )
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text("Long Press")
                                    Text("$longPressCount", style = MaterialTheme.typography.headlineSmall)
                                }
                            }
                        }
                    }
                }
            }
        }
        
        item {
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Draggable Element",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    var offset by remember { mutableStateOf(Offset.Zero) }
                    
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Box(
                            modifier = Modifier
                                .offset { IntOffset(offset.x.roundToInt(), offset.y.roundToInt()) }
                                .size(60.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary)
                                .pointerInput(Unit) {
                                    detectDragGestures { change, dragAmount ->
                                        offset = Offset(
                                            (offset.x + dragAmount.x).coerceIn(0f, size.width - 60.dp.toPx()),
                                            (offset.y + dragAmount.y).coerceIn(0f, size.height - 60.dp.toPx())
                                        )
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "🎯",
                                style = MaterialTheme.typography.headlineSmall
                            )
                        }
                    }
                    
                    Text(
                        text = "Drag the circle around",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
        
        item {
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Scrollable Content",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    var scrollOffset by remember { mutableFloatStateOf(0f) }
                    val scrollState = rememberScrollableState { delta ->
                        scrollOffset += delta
                        delta
                    }
                    
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .scrollable(
                                state = scrollState,
                                orientation = Orientation.Horizontal
                            )
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Box(
                            modifier = Modifier
                                .offset { IntOffset(scrollOffset.roundToInt(), 0) }
                                .size(80.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.tertiary)
                                .align(Alignment.CenterStart),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "📱",
                                style = MaterialTheme.typography.headlineSmall
                            )
                        }
                    }
                    
                    Text(
                        text = "Scroll horizontally: ${scrollOffset.roundToInt()}px",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
        
        item {
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Transform Gestures",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    var scale by remember { mutableFloatStateOf(1f) }
                    var rotation by remember { mutableFloatStateOf(0f) }
                    var offset by remember { mutableStateOf(Offset.Zero) }
                    
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Card(
                            modifier = Modifier
                                .size(80.dp)
                                .align(Alignment.Center)
                                .offset { IntOffset(offset.x.roundToInt(), offset.y.roundToInt()) }
                                .pointerInput(Unit) {
                                    detectTransformGestures { _, pan, zoom, rotationChange ->
                                        scale = (scale * zoom).coerceIn(0.5f, 3f)
                                        rotation += rotationChange
                                        offset = Offset(
                                            (offset.x + pan.x).coerceIn(-100f, 100f),
                                            (offset.y + pan.y).coerceIn(-100f, 100f)
                                        )
                                    }
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "🔄",
                                    style = MaterialTheme.typography.headlineSmall
                                )
                            }
                        }
                    }
                    
                    Text(
                        text = "Pinch to zoom, rotate, and pan",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(
                            onClick = {
                                scale = 1f
                                rotation = 0f
                                offset = Offset.Zero
                            }
                        ) {
                            Text("Reset")
                        }
                    }
                }
            }
        }
        
        item {
            Text(
                text = "🎉 Comprehensive Gesture Examples Complete!\n\n" +
                        "This section demonstrates:\n\n" +
                        "• Click and Long Press detection\n" +
                        "• Draggable elements with bounds\n" +
                        "• Scrollable content with state\n" +
                        "• Transform gestures (pan, zoom, rotate)\n" +
                        "• Pointer input handling\n" +
                        "• Gesture state management",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}
