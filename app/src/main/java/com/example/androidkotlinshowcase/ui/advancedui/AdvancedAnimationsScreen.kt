package com.example.androidkotlinshowcase.ui.advancedui

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun AdvancedAnimationsScreen() {
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
                        text = "Advanced Animations",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Physics-based animations and advanced animation techniques",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        item {
            SpringAnimationDemo()
        }

        item {
            DecayAnimationDemo()
        }

        item {
            KeyframeAnimationDemo()
        }

        item {
            ChainedAnimationDemo()
        }

        item {
            PhysicsAnimationDemo()
        }
    }
}

@Composable
private fun SpringAnimationDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Spring Animation",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Physics-based spring animations with different damping ratios",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            var triggered by remember { mutableStateOf(false) }
            
            Button(
                onClick = { triggered = !triggered },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Toggle Spring Animation")
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                listOf(
                    Spring.DampingRatioNoBouncy to "No Bouncy",
                    Spring.DampingRatioLowBouncy to "Low Bouncy",
                    Spring.DampingRatioMediumBouncy to "Medium Bouncy",
                    Spring.DampingRatioHighBouncy to "High Bouncy"
                ).forEach { (dampingRatio, label) ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        val scale by animateFloatAsState(
                            targetValue = if (triggered) 1.5f else 1f,
                            animationSpec = spring(
                                dampingRatio = dampingRatio,
                                stiffness = Spring.StiffnessLow
                            )
                        )
                        
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .scale(scale)
                                .background(
                                    MaterialTheme.colorScheme.primary,
                                    CircleShape
                                )
                        )
                        
                        Text(
                            text = label,
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DecayAnimationDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Decay Animation",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Decay animation that slows down over time",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            var triggered by remember { mutableStateOf(false) }
            
            Button(
                onClick = { triggered = !triggered },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Start Decay Animation")
            }

            val rotation by animateFloatAsState(
                targetValue = if (triggered) 360f else 0f,
                animationSpec = tween(
                    durationMillis = 2000,
                    easing = EaseOutCubic
                )
            )

            Box(
                modifier = Modifier
                    .size(80.dp)
                    .rotate(rotation)
                    .background(
                        MaterialTheme.colorScheme.secondary,
                        CircleShape
                    )
                    .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}

@Composable
private fun KeyframeAnimationDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Keyframe Animation",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Complex animations using keyframes",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            var triggered by remember { mutableStateOf(false) }
            
            Button(
                onClick = { triggered = !triggered },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Start Keyframe Animation")
            }

            val animationSpec = keyframes<Float> {
                durationMillis = 2000
                0f at 0 with LinearEasing
                0.5f at 500 with EaseInOutCubic
                1.2f at 1000 with EaseOutBounce
                1f at 2000 with EaseInOutCubic
            }

            val scale by animateFloatAsState(
                targetValue = if (triggered) 1f else 0f,
                animationSpec = animationSpec
            )

            Box(
                modifier = Modifier
                    .size(80.dp)
                    .scale(scale)
                    .background(
                        MaterialTheme.colorScheme.tertiary,
                        RoundedCornerShape(16.dp)
                    )
                    .align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
private fun ChainedAnimationDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Chained Animation",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Sequential animations that trigger one after another",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            var triggered by remember { mutableStateOf(false) }
            
            Button(
                onClick = { triggered = !triggered },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Start Chained Animation")
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(4) { index ->
                    val animatable = remember { Animatable(0f) }
                    
                    LaunchedEffect(triggered) {
                        if (triggered) {
                            delay(index * 200L)
                            animatable.animateTo(
                                targetValue = 1f,
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioMediumBouncy,
                                    stiffness = Spring.StiffnessLow
                                )
                            )
                        } else {
                            animatable.animateTo(0f)
                        }
                    }
                    
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .scale(animatable.value)
                            .background(
                                MaterialTheme.colorScheme.primary,
                                CircleShape
                            )
                    )
                }
            }
        }
    }
}

@Composable
private fun PhysicsAnimationDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Physics-based Animation",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Realistic physics simulation with gravity and bounce",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            var triggered by remember { mutableStateOf(false) }
            
            Button(
                onClick = { triggered = !triggered },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Drop Ball")
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant,
                        RoundedCornerShape(8.dp)
                    )
            ) {
                val animatable = remember { Animatable(0f) }
                
                LaunchedEffect(triggered) {
                    if (triggered) {
                        animatable.snapTo(0f)
                        animatable.animateTo(
                            targetValue = 1f,
                            animationSpec = keyframes {
                                durationMillis = 1500
                                0f at 0 with EaseIn
                                0.7f at 800 with EaseOutBounce
                                1f at 1500 with EaseInOut
                            }
                        )
                    } else {
                        animatable.snapTo(0f)
                    }
                }
                
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .offset(y = (animatable.value * 150).dp)
                        .background(
                            MaterialTheme.colorScheme.error,
                            CircleShape
                        )
                        .align(Alignment.TopCenter)
                )
            }
        }
    }
}
