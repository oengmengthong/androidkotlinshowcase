package com.example.androidkotlinshowcase.ui.advancedui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt
import kotlin.random.Random

@Composable
fun CustomLayoutsScreen() {
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
                        text = "Custom Layouts",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Advanced layout composition with custom measurement and placement",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
        
        item {
            BasicCustomLayoutDemo()
        }
        
        item {
            CircleLayoutDemo()
        }
        
        item {
            StaggeredGridDemo()
        }
        
        item {
            SubcomposeLayoutDemo()
        }
        
        item {
            FlowLayoutDemo()
        }
    }
}

@Composable
private fun BasicCustomLayoutDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Basic Custom Layout",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "A layout that centers all children with a specified padding between them",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                CenteredWithPaddingLayout(
                    modifier = Modifier,
                    paddingBetweenItems = 16.dp
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                    )
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.secondary)
                    )
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.tertiary)
                    )
                }
            }
        }
    }
}

@Composable
private fun CenteredWithPaddingLayout(
    modifier: Modifier = Modifier,
    paddingBetweenItems: androidx.compose.ui.unit.Dp,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        
        // Measure all children with unconstrained width and height
        val placeables = measurables.map { measurable ->
            measurable.measure(Constraints())
        }
        
        // Calculate the height needed for all children plus spacing
        val totalHeight = placeables.foldIndexed(0) { index, acc, placeable ->
            acc + placeable.height + if (index < placeables.size - 1) paddingBetweenItems.roundToPx() else 0
        }
        
        // Calculate max width
        val maxWidth = placeables.maxOf { it.width }
        
        // Set layout width and height
        val layoutWidth = maxWidth.coerceAtLeast(constraints.minWidth)
        val layoutHeight = totalHeight.coerceAtLeast(constraints.minHeight)
        
        layout(layoutWidth, layoutHeight) {
            var yPosition = 0
            
            placeables.forEach { placeable ->
                val xPosition = (layoutWidth - placeable.width) / 2
                
                placeable.placeRelative(x = xPosition, y = yPosition)
                
                yPosition += placeable.height + paddingBetweenItems.roundToPx()
            }
        }
    }
}

@Composable
private fun CircleLayoutDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Circle Layout",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Places child components in a circle around a central point",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                CircleLayout(
                    radius = 80.dp,
                    modifier = Modifier.fillMaxSize()
                ) {
                    repeat(8) { index ->
                        val color = when (index % 4) {
                            0 -> MaterialTheme.colorScheme.primary
                            1 -> MaterialTheme.colorScheme.secondary
                            2 -> MaterialTheme.colorScheme.tertiary
                            else -> MaterialTheme.colorScheme.error
                        }
                        
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(color),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${index + 1}",
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CircleLayout(
    radius: androidx.compose.ui.unit.Dp,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        // Measure all children
        val placeables = measurables.map { measurable ->
            measurable.measure(Constraints())
        }
        
        // Set the size of the layout
        val layoutWidth = constraints.maxWidth
        val layoutHeight = constraints.maxHeight
        
        layout(layoutWidth, layoutHeight) {
            // Calculate the center of the circle
            val centerX = layoutWidth / 2
            val centerY = layoutHeight / 2
            
            // Place children in a circle
            val angleStep = 360f / placeables.size
            
            placeables.forEachIndexed { index, placeable ->
                val angle = Math.toRadians((index * angleStep).toDouble())
                
                // Calculate x and y coordinates on the circle
                val x = (centerX + radius.toPx() * kotlin.math.cos(angle)).roundToInt()
                val y = (centerY + radius.toPx() * kotlin.math.sin(angle)).roundToInt()
                
                // Place the element, making sure to center it on the calculated point
                placeable.placeRelative(
                    x = x - placeable.width / 2,
                    y = y - placeable.height / 2
                )
            }
        }
    }
}

@Composable
private fun StaggeredGridDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Staggered Grid",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "A staggered grid layout that places items based on available space",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(8.dp)
            ) {
                StaggeredGrid(
                    columns = 3,
                    modifier = Modifier.fillMaxSize()
                ) {
                    repeat(9) { index ->
                        val height = if (index % 3 == 0) 80.dp else 40.dp
                        val color = when (index % 5) {
                            0 -> MaterialTheme.colorScheme.primary
                            1 -> MaterialTheme.colorScheme.secondary
                            2 -> MaterialTheme.colorScheme.tertiary
                            3 -> MaterialTheme.colorScheme.error
                            else -> MaterialTheme.colorScheme.inversePrimary
                        }
                        
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(height)
                                .padding(4.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(color),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${index + 1}",
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun StaggeredGrid(
    columns: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        val columnWidth = (constraints.maxWidth / columns)
        val columnHeights = IntArray(columns) { 0 } // Track height of each column
        
        // Measure children
        val placeables = measurables.map { measurable ->
            val columnConstraints = constraints.copy(
                minWidth = 0,
                maxWidth = columnWidth
            )
            
            measurable.measure(columnConstraints)
        }
        
        // Calculate layout height based on tallest column
        var layoutHeight = 0
        
        // Place children
        val positions = placeables.map { placeable ->
            // Find column with smallest height
            val columnIndex = columnHeights.indices.minByOrNull { columnHeights[it] } ?: 0
            
            // Calculate x and y position
            val xPos = columnIndex * columnWidth
            val yPos = columnHeights[columnIndex]
            
            // Update column height
            columnHeights[columnIndex] += placeable.height
            
            // Update max layout height
            layoutHeight = columnHeights.maxOrNull()?.coerceAtLeast(constraints.minHeight) ?: constraints.minHeight
            
            androidx.compose.ui.geometry.Offset(xPos.toFloat(), yPos.toFloat())
        }
        
        layout(constraints.maxWidth, layoutHeight) {
            placeables.forEachIndexed { index, placeable ->
                val position = positions[index]
                placeable.placeRelative(
                    x = position.x.roundToInt(),
                    y = position.y.roundToInt()
                )
            }
        }
    }
}

@Composable
private fun SubcomposeLayoutDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "SubcomposeLayout Example",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Layout that measures some components first and then uses those measurements to influence others",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                BadgeLayout {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Content",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    
                    // This is the badge
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.error),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "8",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun BadgeLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    SubcomposeLayout(modifier = modifier) { constraints ->
        // Extract the 2 composables
        val contentPlaceables = subcompose("content", content).map { it.measure(constraints) }
        
        // Main content should be at index 0, badge at index 1
        require(contentPlaceables.size == 2) { "BadgeLayout requires exactly 2 children" }
        
        val mainContent = contentPlaceables[0]
        val badge = contentPlaceables[1]
        
        // Calculate the position for the badge (top-right corner of main content)
        val badgeX = mainContent.width - badge.width / 2
        val badgeY = -badge.height / 2
        
        layout(mainContent.width, mainContent.height) {
            // Place main content at origin
            mainContent.placeRelative(0, 0)
            
            // Place badge at the calculated position
            badge.placeRelative(badgeX, badgeY)
        }
    }
}

@Composable
private fun FlowLayoutDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Flow Layout",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "A layout that flows items horizontally and wraps to the next line when needed",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(8.dp)
            ) {
                FlowLayout(
                    horizontalSpacing = 8.dp,
                    verticalSpacing = 8.dp
                ) {
                    val tagColors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.secondary,
                        MaterialTheme.colorScheme.tertiary,
                        MaterialTheme.colorScheme.error,
                        MaterialTheme.colorScheme.inversePrimary
                    )
                    
                    val tags = listOf(
                        "Jetpack Compose", "Android", "Kotlin", "Custom Layout",
                        "Flow", "UI", "Design", "Material", "Layout", "Mobile"
                    )
                    
                    tags.forEachIndexed { index, tag ->
                        val color = tagColors[index % tagColors.size]
                        
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(16.dp))
                                .background(color)
                                .padding(horizontal = 12.dp, vertical = 6.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = tag,
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun FlowLayout(
    horizontalSpacing: androidx.compose.ui.unit.Dp = 0.dp,
    verticalSpacing: androidx.compose.ui.unit.Dp = 0.dp,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        val horizontalSpacingPx = horizontalSpacing.roundToPx()
        val verticalSpacingPx = verticalSpacing.roundToPx()
        
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }
        
        layout(constraints.maxWidth, constraints.maxHeight) {
            var xPos = 0
            var yPos = 0
            var rowMaxHeight = 0
            
            placeables.forEach { placeable ->
                // If this item doesn't fit in the current row, move to next row
                if (xPos + placeable.width > constraints.maxWidth) {
                    xPos = 0
                    yPos += rowMaxHeight + verticalSpacingPx
                    rowMaxHeight = 0
                }
                
                placeable.placeRelative(
                    x = xPos,
                    y = yPos
                )
                
                // Update x position for next item
                xPos += placeable.width + horizontalSpacingPx
                
                // Update the row's max height
                rowMaxHeight = kotlin.math.max(rowMaxHeight, placeable.height)
            }
        }
    }
}
