package com.example.androidkotlinshowcase.ui.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
fun LayoutsScreen(
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
                    imageVector = Icons.Default.Build,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                
                Text(
                    text = "Layouts & Arrangement",
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center
                )
                
                Text(
                    text = "Advanced layout patterns and arrangement strategies",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        
        item {
            ConstraintLayoutDemo()
        }
        
        item {
            BoxWithConstraintsDemo()
        }
        
        item {
            FlowLayoutDemo()
        }
        
        item {
            ArrangementDemo()
        }
        
        item {
            SpacerAndDividerDemo()
        }
        
        item {
            CustomLayoutDemo()
        }
    }
}

@Composable
private fun ConstraintLayoutDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "ConstraintLayout",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Complex layouts with constraints and guidelines:",
                style = MaterialTheme.typography.titleSmall
            )
            
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant,
                        RoundedCornerShape(8.dp)
                    )
                    .padding(16.dp)
            ) {
                val (title, subtitle, button, image) = createRefs()
                
                // Create guidelines
                val topGuideline = createGuidelineFromTop(0.3f)
                val endGuideline = createGuidelineFromEnd(0.3f)
                
                // Profile image placeholder
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(
                            MaterialTheme.colorScheme.primary,
                            RoundedCornerShape(30.dp)
                        )
                        .constrainAs(image) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "IMG",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
                
                // Title
                Text(
                    text = "John Doe",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.constrainAs(title) {
                        top.linkTo(image.top)
                        start.linkTo(image.end, 16.dp)
                        end.linkTo(endGuideline)
                        width = Dimension.fillToConstraints
                    }
                )
                
                // Subtitle
                Text(
                    text = "Software Engineer",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.constrainAs(subtitle) {
                        top.linkTo(title.bottom, 4.dp)
                        start.linkTo(title.start)
                        end.linkTo(title.end)
                        width = Dimension.fillToConstraints
                    }
                )
                
                // Button
                Button(
                    onClick = { /* Action */ },
                    modifier = Modifier.constrainAs(button) {
                        top.linkTo(topGuideline)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                ) {
                    Text("Follow")
                }
            }
            
            Text(
                text = "ConstraintLayout provides flexible positioning with constraints, guidelines, and barriers",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun BoxWithConstraintsDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "BoxWithConstraints",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Adaptive layouts based on available space:",
                style = MaterialTheme.typography.titleSmall
            )
            
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.primaryContainer,
                        RoundedCornerShape(8.dp)
                    )
                    .padding(16.dp)
            ) {
                val isWide = maxWidth > 300.dp
                val currentMaxWidth = maxWidth
                
                if (isWide) {
                    // Wide layout - horizontal arrangement
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Build,
                            contentDescription = null,
                            modifier = Modifier.size(40.dp),
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Wide Layout",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Text(
                                text = "Available width: ${currentMaxWidth.value.toInt()}dp",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }
                } else {
                    // Narrow layout - vertical arrangement
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Build,
                            contentDescription = null,
                            modifier = Modifier.size(32.dp),
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        
                        Text(
                            text = "Narrow Layout",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            textAlign = TextAlign.Center
                        )
                        
                        Text(
                            text = "Width: ${currentMaxWidth.value.toInt()}dp",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }
            
            Text(
                text = "BoxWithConstraints adapts layout based on available space constraints",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
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
                text = "Flow Layout Pattern",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Custom flow layout with wrapping items:",
                style = MaterialTheme.typography.titleSmall
            )
            
            // Simulate FlowRow with custom implementation
            FlowRowCustom(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.tertiaryContainer,
                        RoundedCornerShape(8.dp)
                    )
                    .padding(16.dp),
                horizontalSpacing = 8.dp,
                verticalSpacing = 8.dp
            ) {
                val tags = listOf("Jetpack", "Compose", "Material3", "Kotlin", "Android", "UI", "Development", "Mobile")
                
                tags.forEach { tag ->
                    AssistChip(
                        onClick = { /* Handle click */ },
                        label = { Text(tag) }
                    )
                }
            }
            
            Text(
                text = "FlowRow wraps items to next line when space is limited",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ArrangementDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Arrangement Patterns",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Different arrangement strategies:",
                style = MaterialTheme.typography.titleSmall
            )
            
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // SpaceBetween
                ArrangementExample(
                    title = "SpaceBetween",
                    arrangement = Arrangement.SpaceBetween
                )
                
                // SpaceAround
                ArrangementExample(
                    title = "SpaceAround",
                    arrangement = Arrangement.SpaceAround
                )
                
                // SpaceEvenly
                ArrangementExample(
                    title = "SpaceEvenly",
                    arrangement = Arrangement.SpaceEvenly
                )
            }
            
            Text(
                text = "Arrangement controls how items are distributed in available space",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ArrangementExample(
    title: String,
    arrangement: Arrangement.Horizontal
) {
    Column {
        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Medium
        )
        
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(
                    MaterialTheme.colorScheme.surfaceVariant,
                    RoundedCornerShape(4.dp)
                )
                .padding(horizontal = 8.dp),
            horizontalArrangement = arrangement,
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(3) { index ->
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .background(
                            MaterialTheme.colorScheme.primary,
                            RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${index + 1}",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}

@Composable
private fun SpacerAndDividerDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Spacer & Divider",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Layout spacing and visual separation:",
                style = MaterialTheme.typography.titleSmall
            )
            
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant,
                        RoundedCornerShape(8.dp)
                    )
                    .padding(16.dp)
            ) {
                Text("Item 1", style = MaterialTheme.typography.bodyMedium)
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text("Item 2 (8dp spacer above)", style = MaterialTheme.typography.bodyMedium)
                
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                Text("Item 3 (divider above)", style = MaterialTheme.typography.bodyMedium)
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Left", style = MaterialTheme.typography.bodyMedium)
                    
                    Spacer(modifier = Modifier.weight(1f))
                    
                    Text("Right", style = MaterialTheme.typography.bodyMedium)
                }
            }
            
            Text(
                text = "Spacer creates flexible space, Divider adds visual separation",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun CustomLayoutDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Custom Layout",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Custom measuring and positioning:",
                style = MaterialTheme.typography.titleSmall
            )
            
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.primaryContainer,
                        RoundedCornerShape(8.dp)
                    )
                    .padding(16.dp)
            ) {
                CustomCircularLayout {
                    repeat(6) { index ->
                        Card(
                            modifier = Modifier.size(40.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "${index + 1}",
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    style = MaterialTheme.typography.labelMedium
                                )
                            }
                        }
                    }
                }
            }
            
            Text(
                text = "Custom layouts enable unique positioning patterns",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun CustomCircularLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    androidx.compose.ui.layout.Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }
        
        val radius = 60.dp.toPx()
        val centerX = constraints.maxWidth / 2
        val centerY = constraints.maxHeight / 2
        
        layout(constraints.maxWidth, constraints.maxHeight) {
            placeables.forEachIndexed { index, placeable ->
                val angle = (index * 2 * Math.PI / placeables.size).toFloat()
                val x = (centerX + radius * kotlin.math.cos(angle) - placeable.width / 2).toInt()
                val y = (centerY + radius * kotlin.math.sin(angle) - placeable.height / 2).toInt()
                
                placeable.placeRelative(x, y)
            }
        }
    }
}

@Composable
private fun FlowRowCustom(
    modifier: Modifier = Modifier,
    horizontalSpacing: androidx.compose.ui.unit.Dp = 0.dp,
    verticalSpacing: androidx.compose.ui.unit.Dp = 0.dp,
    content: @Composable () -> Unit
) {
    androidx.compose.ui.layout.Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val placeables = measurables.map { measurable ->
            measurable.measure(constraints)
        }
        
        val rows = mutableListOf<List<androidx.compose.ui.layout.Placeable>>()
        var currentRow = mutableListOf<androidx.compose.ui.layout.Placeable>()
        var currentRowWidth = 0
        
        placeables.forEach { placeable ->
            val itemWidth = placeable.width + horizontalSpacing.roundToPx()
            
            if (currentRowWidth + itemWidth <= constraints.maxWidth || currentRow.isEmpty()) {
                currentRow.add(placeable)
                currentRowWidth += itemWidth
            } else {
                rows.add(currentRow)
                currentRow = mutableListOf(placeable)
                currentRowWidth = itemWidth
            }
        }
        
        if (currentRow.isNotEmpty()) {
            rows.add(currentRow)
        }
        
        val totalHeight = rows.sumOf { row ->
            row.maxOf { it.height }
        } + (rows.size - 1) * verticalSpacing.roundToPx()
        
        layout(constraints.maxWidth, totalHeight) {
            var yPosition = 0
            
            rows.forEach { row ->
                var xPosition = 0
                val rowHeight = row.maxOf { it.height }
                
                row.forEach { placeable ->
                    placeable.placeRelative(xPosition, yPosition)
                    xPosition += placeable.width + horizontalSpacing.roundToPx()
                }
                
                yPosition += rowHeight + verticalSpacing.roundToPx()
            }
        }
    }
}
