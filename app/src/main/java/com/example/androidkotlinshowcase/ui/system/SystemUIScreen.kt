package com.example.androidkotlinshowcase.ui.system

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun SystemUIScreen(
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
                
                Text(
                    text = "System UI",
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center
                )
                
                Text(
                    text = "Window insets, system bars, and platform integration",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        
        item {
            WindowInsetsDemo()
        }
        
        item {
            SystemBarsDemo()
        }
        
        item {
            NavigationBarsDemo()
        }
        
        item {
            KeyboardInsetsDemo()
        }
        
        item {
            DisplayMetricsDemo()
        }
    }
}

@Composable
private fun WindowInsetsDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Window Insets",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            val windowInsets = WindowInsets.systemBars
            val density = LocalDensity.current
            val layoutDirection = LocalLayoutDirection.current
            
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "System Bars Insets:",
                    style = MaterialTheme.typography.titleSmall
                )
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    InfoChip("Top", "${windowInsets.getTop(density)}px")
                    InfoChip("Bottom", "${windowInsets.getBottom(density)}px")
                }
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    InfoChip("Left", "${windowInsets.getLeft(density, layoutDirection)}px")
                    InfoChip("Right", "${windowInsets.getRight(density, layoutDirection)}px")
                }
            }
        }
    }
}

@Composable
private fun SystemBarsDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "System Bars Padding",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Status Bar Padding:",
                style = MaterialTheme.typography.titleSmall
            )
            
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .statusBarsPadding()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Content with statusBarsPadding()",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            
            Text(
                text = "Navigation Bar Padding:",
                style = MaterialTheme.typography.titleSmall
            )
            
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .navigationBarsPadding()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Content with navigationBarsPadding()",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
private fun NavigationBarsDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Navigation Bars",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            val navigationBars = WindowInsets.navigationBars
            val density = LocalDensity.current
            val layoutDirection = LocalLayoutDirection.current
            
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Navigation Bar Measurements:",
                    style = MaterialTheme.typography.titleSmall
                )
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    InfoChip("Height", "${navigationBars.getBottom(density)}px")
                    InfoChip("Width", "${navigationBars.getLeft(density, layoutDirection) + navigationBars.getRight(density, layoutDirection)}px")
                }
                
                // Visual representation
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.tertiaryContainer)
                        .padding(horizontal = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Simulated Navigation Bar Area",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Composable
private fun KeyboardInsetsDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Keyboard Insets",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            val ime = WindowInsets.ime
            val density = LocalDensity.current
            val keyboardHeight = ime.getBottom(density)
            
            var text by remember { mutableStateOf("") }
            
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Keyboard Height: ${keyboardHeight}px",
                    style = MaterialTheme.typography.bodyMedium
                )
                
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Type to show keyboard") },
                    modifier = Modifier.fillMaxWidth()
                )
                
                if (keyboardHeight > 0) {
                    Text(
                        text = "✅ Keyboard is visible",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                } else {
                    Text(
                        text = "❌ Keyboard is hidden",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
private fun DisplayMetricsDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Display Metrics",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            val density = LocalDensity.current
            
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Screen Information:",
                    style = MaterialTheme.typography.titleSmall
                )
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    InfoChip("Density", "${density.density}")
                    InfoChip("Font Scale", "${density.fontScale}")
                }
                
                Text(
                    text = "Density Conversions:",
                    style = MaterialTheme.typography.titleSmall
                )
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    InfoChip("48dp", "${with(density) { 48.dp.toPx() }}px")
                    InfoChip("100px", "${with(density) { 100.toDp() }}dp")
                }
            }
        }
    }
}

@Composable
private fun InfoChip(label: String, value: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
