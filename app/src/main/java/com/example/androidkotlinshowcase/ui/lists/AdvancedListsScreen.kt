package com.example.androidkotlinshowcase.ui.lists

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.staggeredgrid.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import kotlin.random.Random

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AdvancedListsScreen(
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
                    imageVector = Icons.Default.List,
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                
                Text(
                    text = "Advanced Lists",
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center
                )
                
                Text(
                    text = "Grids, staggered layouts, pagers, and list optimizations",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        
        item {
            LazyVerticalGridDemo()
        }
        
        item {
            LazyHorizontalGridDemo()
        }
        
        item {
            LazyStaggeredGridDemo()
        }
        
        item {
            HorizontalPagerDemo()
        }
        
        item {
            ListStateDemo()
        }
        
        item {
            StickyHeadersDemo()
        }
    }
}

@Composable
private fun LazyVerticalGridDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "LazyVerticalGrid",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Responsive grid with adaptive columns:",
                style = MaterialTheme.typography.titleSmall
            )
            
            LazyVerticalGrid(
                columns = GridCells.Adaptive(80.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(
                        MaterialTheme.colorScheme.surfaceVariant,
                        RoundedCornerShape(8.dp)
                    )
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(24) { index ->
                    Card(
                        modifier = Modifier
                            .aspectRatio(1f)
                            .fillMaxWidth(),
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
            
            Text(
                text = "GridCells.Adaptive creates responsive columns based on minimum size",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun LazyHorizontalGridDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "LazyHorizontalGrid",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Horizontal scrolling grid with fixed rows:",
                style = MaterialTheme.typography.titleSmall
            )
            
            LazyHorizontalGrid(
                rows = GridCells.Fixed(3),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(
                        MaterialTheme.colorScheme.primaryContainer,
                        RoundedCornerShape(8.dp)
                    )
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(18) { index ->
                    Card(
                        modifier = Modifier
                            .size(40.dp)
                            .aspectRatio(1f),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${index + 1}",
                                color = MaterialTheme.colorScheme.primaryContainer,
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }
                }
            }
            
            Text(
                text = "LazyHorizontalGrid scrolls horizontally with fixed row count",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun LazyStaggeredGridDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "LazyStaggeredGrid",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Pinterest-style staggered grid layout:",
                style = MaterialTheme.typography.titleSmall
            )
            
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(
                        MaterialTheme.colorScheme.tertiaryContainer,
                        RoundedCornerShape(8.dp)
                    )
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalItemSpacing = 8.dp
            ) {
                items(16) { index ->
                    val randomHeight = Random.nextInt(60, 120)
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(randomHeight.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.onTertiaryContainer
                        )
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${index + 1}",
                                color = MaterialTheme.colorScheme.tertiaryContainer,
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                }
            }
            
            Text(
                text = "Staggered grid adjusts item placement to minimize gaps",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun HorizontalPagerDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "HorizontalPager",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Swipeable pages with page indicators:",
                style = MaterialTheme.typography.titleSmall
            )
            
            val pagerState = rememberPagerState(pageCount = { 5 })
            
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                ) { page ->
                    Card(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = when (page % 3) {
                                0 -> MaterialTheme.colorScheme.primaryContainer
                                1 -> MaterialTheme.colorScheme.secondaryContainer
                                else -> MaterialTheme.colorScheme.tertiaryContainer
                            }
                        )
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Page ${page + 1}",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                
                                Text(
                                    text = "Swipe to navigate",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                }
                
                // Page indicator
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    repeat(5) { index ->
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .background(
                                    if (index == pagerState.currentPage) {
                                        MaterialTheme.colorScheme.primary
                                    } else {
                                        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f)
                                    },
                                    RoundedCornerShape(4.dp)
                                )
                        )
                    }
                }
            }
            
            Text(
                text = "HorizontalPager provides smooth page transitions with gesture support",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ListStateDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "List State Management",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Scroll position and state preservation:",
                style = MaterialTheme.typography.titleSmall
            )
            
            val listState = rememberLazyListState()
            val coroutineScope = rememberCoroutineScope()
            
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                LazyRow(
                    state = listState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .background(
                            MaterialTheme.colorScheme.surfaceVariant,
                            RoundedCornerShape(8.dp)
                        )
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(50) { index ->
                        Card(
                            modifier = Modifier
                                .size(64.dp)
                                .aspectRatio(1f),
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
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            coroutineScope.launch {
                                listState.animateScrollToItem(0)
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Start", style = MaterialTheme.typography.labelMedium)
                    }
                    
                    OutlinedButton(
                        onClick = {
                            coroutineScope.launch {
                                listState.animateScrollToItem(24)
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Middle", style = MaterialTheme.typography.labelMedium)
                    }
                    
                    OutlinedButton(
                        onClick = {
                            coroutineScope.launch {
                                listState.animateScrollToItem(49)
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("End", style = MaterialTheme.typography.labelMedium)
                    }
                }
                
                Text(
                    text = "First visible: ${listState.firstVisibleItemIndex + 1}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            Text(
                text = "LazyListState provides programmatic scroll control and position tracking",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun StickyHeadersDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Sticky Headers",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = "Headers that stick to top during scroll:",
                style = MaterialTheme.typography.titleSmall
            )
            
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(
                        MaterialTheme.colorScheme.primaryContainer,
                        RoundedCornerShape(8.dp)
                    )
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                val sections = listOf("Section A", "Section B", "Section C")
                
                sections.forEach { section ->
                    stickyHeader(key = section) {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text(
                                text = section,
                                modifier = Modifier.padding(12.dp),
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                    
                    items(8) { index ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(
                                containerColor = MaterialTheme.colorScheme.surface
                            )
                        ) {
                            Text(
                                text = "$section - Item ${index + 1}",
                                modifier = Modifier.padding(12.dp),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
            
            Text(
                text = "Sticky headers remain visible during scroll for better navigation",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
