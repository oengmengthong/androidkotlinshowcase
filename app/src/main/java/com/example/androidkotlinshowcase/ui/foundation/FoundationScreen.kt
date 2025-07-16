package com.example.androidkotlinshowcase.ui.foundation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Architecture
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.ViewColumn
import androidx.compose.material.icons.filled.FormatListBulleted
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material.icons.filled.Style
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun FoundationScreen(
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Architecture,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = "Foundation Building Blocks",
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center
                )
                
                Text(
                    text = "Core layout primitives, scrolling containers, text components, and theming fundamentals",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
        
        // Demo Overview Cards
        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 4.dp)
            ) {
                items(foundationDemoItems) { demo ->
                    DemoOverviewCard(
                        title = demo.title,
                        icon = demo.icon,
                        description = demo.description,
                        modifier = Modifier.width(200.dp)
                    )
                }
            }
        }
        
        // Layout Primitives Demo
        item {
            LayoutPrimitivesDemo()
        }
        
        // Text Components Demo
        item {
            TextComponentsDemo()
        }
        
        // Surface & Cards Demo
        item {
            SurfaceAndCardsDemo()
        }
        
        // Scrolling & Lists Demo
        item {
            ScrollingAndListsDemo()
        }
        
        // Theming Demo
        item {
            ThemingDemo()
        }
        
        // Advanced Layout Demo
        item {
            AdvancedLayoutDemo()
        }
    }
}

@Composable
private fun DemoOverviewCard(
    title: String,
    icon: ImageVector,
    description: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(32.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun LayoutPrimitivesDemo() {
    DemoCard(
        title = "Layout Primitives",
        subtitle = "Row, Column, Box, BoxWithConstraints"
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Row example
            Column {
                Text(
                    text = "Row Layout:",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    repeat(3) { index ->
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                            )
                        ) {
                            Text(
                                text = "Item ${index + 1}",
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    }
                }
            }
            
            // Column example
            Column {
                Text(
                    text = "Column Layout:",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    repeat(3) { index ->
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.secondaryContainer
                            ),
                            modifier = Modifier.padding(vertical = 2.dp)
                        ) {
                            Text(
                                text = "Column Item ${index + 1}",
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    }
                }
            }
            
            // Box example
            Column {
                Text(
                    text = "Box Layout (Overlay):",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .background(
                            MaterialTheme.colorScheme.surfaceContainer,
                            RoundedCornerShape(8.dp)
                        )
                ) {
                    Card(
                        modifier = Modifier.align(Alignment.TopStart),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.tertiaryContainer
                        )
                    ) {
                        Text(
                            text = "Top Start",
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                    
                    Card(
                        modifier = Modifier.align(Alignment.Center),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        )
                    ) {
                        Text(
                            text = "Center",
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                    
                    Card(
                        modifier = Modifier.align(Alignment.BottomEnd),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.errorContainer
                        )
                    ) {
                        Text(
                            text = "Bottom End",
                            modifier = Modifier.padding(8.dp)
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
                        text = "Text Components",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    Text(
                        text = "Display Large",
                        style = MaterialTheme.typography.displayLarge
                    )
                    
                    Text(
                        text = "Headline Medium",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    
                    Text(
                        text = "Title Large",
                        style = MaterialTheme.typography.titleLarge
                    )
                    
                    Text(
                        text = "Body Large - This is a longer text to demonstrate the body text style in Material 3 design system.",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    
                    Text(
                        text = "Label Small",
                        style = MaterialTheme.typography.labelSmall
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
                        text = "Surface & Cards",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        ElevatedCard(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Elevated Card",
                                modifier = Modifier.padding(16.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                        
                        Spacer(modifier = Modifier.width(8.dp))
                        
                        OutlinedCard(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Outlined Card",
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
                        text = "Lazy Lists & Grids",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    Text(
                        text = "LazyRow:",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(10) { index ->
                            Card(
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.tertiaryContainer
                                )
                            ) {
                                Text(
                                    text = "Item $index",
                                    modifier = Modifier.padding(8.dp)
                                )
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = "LazyVerticalGrid:",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        modifier = Modifier.height(120.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        items(9) { index ->
                            Card(
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer
                                )
                            ) {
                                Text(
                                    text = "${index + 1}",
                                    modifier = Modifier.padding(8.dp),
                                    textAlign = TextAlign.Center
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
                        text = "BoxWithConstraints & Adaptive Layouts",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    BoxWithConstraints(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                    ) {
                        val itemWidth = if (maxWidth > 300.dp) 100.dp else 80.dp
                        val itemCount = (maxWidth / itemWidth).toInt()
                        
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            repeat(itemCount) { index ->
                                Card(
                                    modifier = Modifier
                                        .size(itemWidth, 60.dp)
                                        .clip(RoundedCornerShape(8.dp)),
                                    colors = CardDefaults.cardColors(
                                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                                    )
                                ) {
                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = "${index + 1}",
                                            style = MaterialTheme.typography.bodyMedium
                                        )
                                    }
                                }
                            }
                        }
                    }
                    
                    Text(
                        text = "Adaptive layout based on available width",
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
                        text = "Spacing & Arrangement",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    Text(
                        text = "SpaceEvenly:",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        repeat(3) { index ->
                            Card(
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.primaryContainer
                                )
                            ) {
                                Text(
                                    text = "${index + 1}",
                                    modifier = Modifier.padding(8.dp)
                                )
                            }
                        }
                    }
                    
                    Text(
                        text = "SpaceBetween:",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        repeat(3) { index ->
                            Card(
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                                )
                            ) {
                                Text(
                                    text = "${index + 1}",
                                    modifier = Modifier.padding(8.dp)
                                )
                            }
                        }
                    }
                    
                    Text(
                        text = "SpaceAround:",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        repeat(3) { index ->
                            Card(
                                colors = CardDefaults.cardColors(
                                    containerColor = MaterialTheme.colorScheme.tertiaryContainer
                                )
                            ) {
                                Text(
                                    text = "${index + 1}",
                                    modifier = Modifier.padding(8.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
        
        item {
            Text(
                text = "🎉 Enhanced Foundation Components Complete!\n\n" +
                        "This section now includes:\n\n" +
                        "• Layout Primitives (Row, Column, Box)\n" +
                        "• Typography System (All Material 3 styles)\n" +
                        "• Lazy Lists & Grids (LazyRow, LazyVerticalGrid)\n" +
                        "• BoxWithConstraints & Adaptive Layouts\n" +
                        "• Spacing & Arrangement Strategies\n" +
                        "• Surface & Card Components",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}
