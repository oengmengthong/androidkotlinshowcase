package com.example.androidkotlinshowcase.ui.animation

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun AnimationScreen(
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
                text = "Animations",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )
        }
        
        item {
            Text(
                text = "Motion and transitions with Compose animation APIs",
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
                        text = "AnimatedVisibility",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    var isVisible by remember { mutableStateOf(true) }
                    
                    Button(
                        onClick = { isVisible = !isVisible },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(if (isVisible) "Hide" else "Show")
                    }
                    
                    AnimatedVisibility(
                        visible = isVisible,
                        enter = slideInVertically() + fadeIn(),
                        exit = slideOutVertically() + fadeOut()
                    ) {
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                            )
                        ) {
                            Text(
                                text = "🎉 Animated Content!",
                                modifier = Modifier.padding(16.dp),
                                textAlign = TextAlign.Center
                            )
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
                        text = "Crossfade",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    var currentPage by remember { mutableStateOf(0) }
                    val pages = listOf("Page 1", "Page 2", "Page 3")
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        pages.forEachIndexed { index, page ->
                            Button(
                                onClick = { currentPage = index },
                                colors = if (currentPage == index) {
                                    ButtonDefaults.buttonColors()
                                } else {
                                    ButtonDefaults.outlinedButtonColors()
                                }
                            ) {
                                Text(page)
                            }
                        }
                    }
                    
                    Crossfade(
                        targetState = currentPage,
                        animationSpec = tween(durationMillis = 300)
                    ) { page ->
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = when (page) {
                                    0 -> MaterialTheme.colorScheme.primaryContainer
                                    1 -> MaterialTheme.colorScheme.secondaryContainer
                                    else -> MaterialTheme.colorScheme.tertiaryContainer
                                }
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp)
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Content for ${pages[page]}",
                                    style = MaterialTheme.typography.bodyLarge
                                )
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
                        text = "AnimatedContent",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    var count by remember { mutableStateOf(0) }
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(onClick = { count-- }) {
                            Text("-")
                        }
                        
                        AnimatedContent(
                            targetState = count,
                            transitionSpec = {
                                if (targetState > initialState) {
                                    slideInVertically { height -> height } + fadeIn() togetherWith
                                            slideOutVertically { height -> -height } + fadeOut()
                                } else {
                                    slideInVertically { height -> -height } + fadeIn() togetherWith
                                            slideOutVertically { height -> height } + fadeOut()
                                }.using(SizeTransform(clip = false))
                            }
                        ) { targetCount ->
                            Text(
                                text = "$targetCount",
                                style = MaterialTheme.typography.headlineLarge
                            )
                        }
                        
                        Button(onClick = { count++ }) {
                            Text("+")
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
                        text = "Animate*AsState",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    var isExpanded by remember { mutableStateOf(false) }
                    
                    val animatedSize by animateDpAsState(
                        targetValue = if (isExpanded) 200.dp else 100.dp,
                        animationSpec = spring(
                            dampingRatio = Spring.DampingRatioMediumBouncy,
                            stiffness = Spring.StiffnessLow
                        )
                    )
                    
                    val animatedColor by animateColorAsState(
                        targetValue = if (isExpanded) 
                            MaterialTheme.colorScheme.primary 
                        else 
                            MaterialTheme.colorScheme.secondary,
                        animationSpec = tween(durationMillis = 300)
                    )
                    
                    val animatedRotation by animateFloatAsState(
                        targetValue = if (isExpanded) 45f else 0f,
                        animationSpec = tween(durationMillis = 300)
                    )
                    
                    Button(
                        onClick = { isExpanded = !isExpanded },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(if (isExpanded) "Collapse" else "Expand")
                    }
                    
                    Box(
                        modifier = Modifier
                            .size(animatedSize)
                            .clip(RoundedCornerShape(16.dp))
                            .background(animatedColor)
                            .rotate(animatedRotation)
                            .align(Alignment.CenterHorizontally),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Favorite,
                            contentDescription = "Animated Icon",
                            tint = Color.White
                        )
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
                        text = "Infinite Animation",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    val infiniteTransition = rememberInfiniteTransition()
                    
                    val animatedScale by infiniteTransition.animateFloat(
                        initialValue = 1f,
                        targetValue = 1.2f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(durationMillis = 1000),
                            repeatMode = RepeatMode.Reverse
                        )
                    )
                    
                    val animatedRotation by infiniteTransition.animateFloat(
                        initialValue = 0f,
                        targetValue = 360f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(durationMillis = 2000, easing = LinearEasing),
                            repeatMode = RepeatMode.Restart
                        )
                    )
                    
                    val animatedColor by infiniteTransition.animateColor(
                        initialValue = MaterialTheme.colorScheme.primary,
                        targetValue = MaterialTheme.colorScheme.secondary,
                        animationSpec = infiniteRepeatable(
                            animation = tween(durationMillis = 1500),
                            repeatMode = RepeatMode.Reverse
                        )
                    )
                    
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .scale(animatedScale)
                            .rotate(animatedRotation)
                            .clip(CircleShape)
                            .background(animatedColor)
                            .align(Alignment.CenterHorizontally),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Favorite,
                            contentDescription = "Infinite Animation",
                            tint = Color.White
                        )
                    }
                }
            }
        }
        
        item {
            Text(
                text = "🎉 Comprehensive Animation Examples Complete!\n\n" +
                        "This section demonstrates:\n\n" +
                        "• AnimatedVisibility with custom transitions\n" +
                        "• Crossfade for content switching\n" +
                        "• AnimatedContent with directional slides\n" +
                        "• Animate*AsState for property animations\n" +
                        "• Infinite animations with various specs\n" +
                        "• Spring, Tween, and Easing animations",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}
