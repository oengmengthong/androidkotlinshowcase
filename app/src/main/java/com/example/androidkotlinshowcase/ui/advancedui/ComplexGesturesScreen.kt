package com.example.androidkotlinshowcase.ui.advancedui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.calculateCentroid
import androidx.compose.foundation.gestures.calculatePan
import androidx.compose.foundation.gestures.calculateRotation
import androidx.compose.foundation.gestures.calculateZoom
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@Composable
fun ComplexGesturesScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Complex Gestures",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Advanced gesture handling with multi-touch support",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        item {
            DragGestureDemo()
        }

        item {
            MultiTouchTransformDemo()
        }

        item {
            AdvancedTapGesturesDemo()
        }

        item {
            CustomGestureDetectorDemo()
        }

        item {
            SequentialGesturesDemo()
        }
    }
}

@Composable
private fun DragGestureDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Drag Gesture",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Drag the box around the container",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            var offsetX by remember { mutableStateOf(0f) }
            var offsetY by remember { mutableStateOf(0f) }
            
            val boxSize = 80.dp
            
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(boxSize / 2),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(boxSize)
                        .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.primary)
                        .pointerInput(Unit) {
                            detectDragGestures { change, dragAmount ->
                                change.consume()
                                offsetX += dragAmount.x
                                offsetY += dragAmount.y
                            }
                        }
                )
            }
        }
    }
}

@Composable
private fun MultiTouchTransformDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Multi-Touch Transform",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Use two fingers to pinch, zoom, rotate the element",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            var scale by remember { mutableFloatStateOf(1f) }
            var rotation by remember { mutableFloatStateOf(0f) }
            var offset by remember { mutableStateOf(Offset.Zero) }
            
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .offset { IntOffset(offset.x.roundToInt(), offset.y.roundToInt()) }
                        .graphicsLayer(
                            scaleX = scale,
                            scaleY = scale,
                            rotationZ = rotation
                        )
                        .background(MaterialTheme.colorScheme.secondary)
                        .border(2.dp, MaterialTheme.colorScheme.onSecondary, RoundedCornerShape(8.dp))
                        .clip(RoundedCornerShape(8.dp))
                        .pointerInput(Unit) {
                            detectTransformGestures { _, pan, zoom, rotationChange ->
                                scale *= zoom
                                rotation += rotationChange
                                offset += pan
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Face,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondary,
                        modifier = Modifier.size(50.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun AdvancedTapGesturesDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Advanced Tap Gestures",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Try single tap, double tap, and long press",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            var tapText by remember { mutableStateOf("Tap me!") }
            var bgColor by remember { mutableStateOf(Color.Blue) }
            val scope = rememberCoroutineScope()
            val scale = remember { Animatable(1f) }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(bgColor)
                    .scale(scale.value)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onTap = {
                                tapText = "Single tap!"
                                bgColor = Color.Blue
                                scope.launch {
                                    scale.animateTo(
                                        targetValue = 0.8f,
                                        animationSpec = spring(stiffness = Spring.StiffnessLow)
                                    )
                                    scale.animateTo(
                                        targetValue = 1f,
                                        animationSpec = spring(stiffness = Spring.StiffnessMedium)
                                    )
                                }
                            },
                            onDoubleTap = {
                                tapText = "Double tap!"
                                bgColor = Color.Green
                                scope.launch {
                                    scale.animateTo(
                                        targetValue = 1.2f,
                                        animationSpec = spring(stiffness = Spring.StiffnessLow)
                                    )
                                    scale.animateTo(
                                        targetValue = 1f,
                                        animationSpec = spring(stiffness = Spring.StiffnessMedium)
                                    )
                                }
                            },
                            onLongPress = {
                                tapText = "Long press!"
                                bgColor = Color.Red
                                scope.launch {
                                    scale.animateTo(
                                        targetValue = 0.9f,
                                        animationSpec = spring(stiffness = Spring.StiffnessLow)
                                    )
                                    scale.animateTo(
                                        targetValue = 1f,
                                        animationSpec = spring(stiffness = Spring.StiffnessMedium)
                                    )
                                }
                            }
                        )
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = tapText,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                )
            }
        }
    }
}

@Composable
private fun CustomGestureDetectorDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Custom Gesture Detector",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Hover over the shape and perform gestures",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            var gestureText by remember { mutableStateOf("Hover or interact with the circle") }
            var circleColor by remember { mutableStateOf(Color.Blue) }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(circleColor)
                        .pointerInput(Unit) {
                            awaitEachGesture {
                                // Wait for at least one pointer to press down
                                val down = awaitFirstDown(requireUnconsumed = false)
                                gestureText = "Down at ${down.position}"
                                circleColor = Color.Green
                                
                                do {
                                    val event = awaitPointerEvent()
                                    when (event.type) {
                                        PointerEventType.Move -> {
                                            gestureText = "Moving ${event.changes.size} pointers"
                                            circleColor = if (event.changes.size > 1) {
                                                Color.Cyan
                                            } else {
                                                Color.Green
                                            }
                                        }
                                        PointerEventType.Release -> {
                                            gestureText = "Released at ${event.changes[0].position}"
                                            circleColor = Color.Blue
                                        }
                                        else -> { /* Ignore */ }
                                    }
                                } while (event.changes.any { it.pressed })
                            }
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Touch",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            
            Text(
                text = gestureText,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
private fun SequentialGesturesDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Sequential Gestures",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "First tap to select, then drag to move",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            var selected by remember { mutableStateOf(false) }
            var position by remember { mutableStateOf(Offset.Zero) }
            val scope = rememberCoroutineScope()
            val offset = remember { Animatable(Offset.Zero, Offset.VectorConverter) }

            LaunchedEffect(position) {
                offset.animateTo(
                    position,
                    animationSpec = spring(stiffness = Spring.StiffnessMedium)
                )
            }
            
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .pointerInput(Unit) {
                        detectTapGestures { tapPosition ->
                            // If box is not selected and tap is outside the box, do nothing
                            if (!selected && !isPositionInBox(tapPosition, offset.value, 60f)) {
                                return@detectTapGestures
                            }
                            
                            // If box is not selected and tap is inside, select it
                            if (!selected && isPositionInBox(tapPosition, offset.value, 60f)) {
                                selected = true
                                return@detectTapGestures
                            }
                            
                            // If selected, deselect
                            if (selected) {
                                selected = false
                            }
                        }
                    }
                    .pointerInput(Unit) {
                        if (selected) {
                            detectDragGestures(
                                onDragEnd = { selected = false },
                                onDragCancel = { selected = false },
                                onDrag = { change, dragAmount ->
                                    change.consume()
                                    position = Offset(
                                        (position.x + dragAmount.x).coerceIn(-size.width / 2f + 60f, size.width / 2f - 60f),
                                        (position.y + dragAmount.y).coerceIn(-size.height / 2f + 60f, size.height / 2f - 60f)
                                    )
                                }
                            )
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .offset { IntOffset(offset.value.x.roundToInt(), offset.value.y.roundToInt()) }
                        .size(60.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (selected) Color.Cyan else Color.Blue)
                        .border(
                            width = if (selected) 3.dp else 1.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (selected) "Drag me" else "Tap me",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

private fun isPositionInBox(pointerPosition: Offset, boxPosition: Offset, boxSize: Float): Boolean {
    val halfSize = boxSize / 2
    return pointerPosition.x >= boxPosition.x - halfSize &&
           pointerPosition.x <= boxPosition.x + halfSize &&
           pointerPosition.y >= boxPosition.y - halfSize &&
           pointerPosition.y <= boxPosition.y + halfSize
}
