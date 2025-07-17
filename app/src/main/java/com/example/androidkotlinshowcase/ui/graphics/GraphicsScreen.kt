package com.example.androidkotlinshowcase.ui.graphics

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun GraphicsScreen(
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
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                
                Text(
                    text = "Graphics",
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center
                )
                
                Text(
                    text = "Custom drawing with Canvas and graphics utilities",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        
        item {
            CanvasBasicsDemo()
        }
        
        item {
            DrawBehindDemo()
        }
        
        item {
            DrawWithContentDemo()
        }
        
        item {
            GradientDemo()
        }
        
        item {
            PathDemo()
        }
        
        item {
            BlendModeDemo()
        }
    }
}

@Composable
private fun CanvasBasicsDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Canvas Basics",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant,
                        RoundedCornerShape(8.dp)
                    )
            ) {
                val canvasWidth = size.width
                val canvasHeight = size.height
                
                // Draw circles
                drawCircle(
                    color = Color.Red,
                    radius = 50f,
                    center = Offset(canvasWidth * 0.25f, canvasHeight * 0.5f)
                )
                
                drawCircle(
                    color = Color.Blue,
                    radius = 50f,
                    center = Offset(canvasWidth * 0.75f, canvasHeight * 0.5f)
                )
                
                // Draw rectangle
                drawRect(
                    color = Color.Green,
                    topLeft = Offset(canvasWidth * 0.4f, canvasHeight * 0.3f),
                    size = Size(canvasWidth * 0.2f, canvasHeight * 0.4f)
                )
                
                // Draw line
                drawLine(
                    color = Color.Magenta,
                    start = Offset(0f, 0f),
                    end = Offset(canvasWidth, canvasHeight),
                    strokeWidth = 5f
                )
            }
            
            Text(
                text = "Basic shapes: circles, rectangles, and lines",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun DrawBehindDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "drawBehind Modifier",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .drawBehind {
                        // Draw background pattern
                        val step = 40f
                        for (x in 0 until (size.width / step).toInt()) {
                            for (y in 0 until (size.height / step).toInt()) {
                                drawCircle(
                                    color = Color.Gray.copy(alpha = 0.3f),
                                    radius = 15f,
                                    center = Offset(x * step + step/2, y * step + step/2)
                                )
                            }
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Text(
                        text = "Content on top",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            
            Text(
                text = "drawBehind draws behind the content",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun DrawWithContentDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "drawWithContent Modifier",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .drawWithContent {
                        // Draw content first
                        drawContent()
                        
                        // Draw overlay
                        drawRect(
                            color = Color.Red.copy(alpha = 0.3f),
                            topLeft = Offset(0f, 0f),
                            size = Size(size.width, 20f)
                        )
                        
                        drawRect(
                            color = Color.Blue.copy(alpha = 0.3f),
                            topLeft = Offset(0f, size.height - 20f),
                            size = Size(size.width, 20f)
                        )
                    },
                contentAlignment = Alignment.Center
            ) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                ) {
                    Text(
                        text = "Content with overlay",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            
            Text(
                text = "drawWithContent can draw before and after content",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun GradientDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Gradients",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Linear Gradient
                Canvas(
                    modifier = Modifier.size(80.dp)
                ) {
                    drawRect(
                        brush = Brush.linearGradient(
                            colors = listOf(Color.Red, Color.Blue),
                            start = Offset(0f, 0f),
                            end = Offset(size.width, size.height)
                        )
                    )
                }
                
                // Radial Gradient
                Canvas(
                    modifier = Modifier.size(80.dp)
                ) {
                    drawCircle(
                        brush = Brush.radialGradient(
                            colors = listOf(Color.Yellow, Color.Green),
                            center = Offset(size.width/2, size.height/2),
                            radius = size.minDimension / 2
                        )
                    )
                }
                
                // Sweep Gradient
                Canvas(
                    modifier = Modifier.size(80.dp)
                ) {
                    drawCircle(
                        brush = Brush.sweepGradient(
                            colors = listOf(Color.Magenta, Color.Cyan, Color.Magenta),
                            center = Offset(size.width/2, size.height/2)
                        )
                    )
                }
            }
            
            Text(
                text = "Linear, Radial, and Sweep gradients",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun PathDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Path Operations",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant,
                        RoundedCornerShape(8.dp)
                    )
            ) {
                val path = Path().apply {
                    moveTo(size.width * 0.1f, size.height * 0.8f)
                    
                    // Create a wave pattern
                    val waveLength = size.width * 0.2f
                    val waveHeight = size.height * 0.3f
                    
                    for (i in 0..4) {
                        val x = size.width * 0.1f + i * waveLength
                        val y = size.height * 0.5f + sin(i * 0.5f) * waveHeight
                        
                        if (i == 0) {
                            lineTo(x, y)
                        } else {
                            val prevX = size.width * 0.1f + (i - 1) * waveLength
                            val controlX = (prevX + x) / 2
                            quadraticBezierTo(
                                controlX, size.height * 0.2f,
                                x, y
                            )
                        }
                    }
                }
                
                drawPath(
                    path = path,
                    color = Color.Blue,
                    style = Stroke(width = 4f)
                )
                
                // Draw a star using path
                val starPath = createStarPath(
                    center = Offset(size.width * 0.8f, size.height * 0.3f),
                    outerRadius = 40f,
                    innerRadius = 20f
                )
                
                drawPath(
                    path = starPath,
                    color = Color.Red
                )
            }
            
            Text(
                text = "Custom paths with curves and complex shapes",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun BlendModeDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Blend Modes",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Multiply
                Canvas(modifier = Modifier.size(80.dp)) {
                    drawCircle(
                        color = Color.Red,
                        radius = 30f,
                        center = Offset(size.width * 0.4f, size.height * 0.5f)
                    )
                    drawCircle(
                        color = Color.Blue,
                        radius = 30f,
                        center = Offset(size.width * 0.6f, size.height * 0.5f),
                        blendMode = BlendMode.Multiply
                    )
                }
                
                // Screen
                Canvas(modifier = Modifier.size(80.dp)) {
                    drawCircle(
                        color = Color.Red,
                        radius = 30f,
                        center = Offset(size.width * 0.4f, size.height * 0.5f)
                    )
                    drawCircle(
                        color = Color.Blue,
                        radius = 30f,
                        center = Offset(size.width * 0.6f, size.height * 0.5f),
                        blendMode = BlendMode.Screen
                    )
                }
                
                // Overlay
                Canvas(modifier = Modifier.size(80.dp)) {
                    drawCircle(
                        color = Color.Red,
                        radius = 30f,
                        center = Offset(size.width * 0.4f, size.height * 0.5f)
                    )
                    drawCircle(
                        color = Color.Blue,
                        radius = 30f,
                        center = Offset(size.width * 0.6f, size.height * 0.5f),
                        blendMode = BlendMode.Overlay
                    )
                }
            }
            
            Text(
                text = "Multiply, Screen, and Overlay blend modes",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private fun createStarPath(
    center: Offset,
    outerRadius: Float,
    innerRadius: Float,
    points: Int = 5
): Path {
    val path = Path()
    val angleStep = kotlin.math.PI / points
    
    for (i in 0 until points * 2) {
        val radius = if (i % 2 == 0) outerRadius else innerRadius
        val angle = i * angleStep - kotlin.math.PI / 2
        
        val x = center.x + cos(angle).toFloat() * radius
        val y = center.y + sin(angle).toFloat() * radius
        
        if (i == 0) {
            path.moveTo(x, y)
        } else {
            path.lineTo(x, y)
        }
    }
    
    path.close()
    return path
}
