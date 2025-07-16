package com.example.androidkotlinshowcase.ui.material3

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun Material3Screen(
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
                text = "Material 3 Components",
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )
        }
        
        item {
            Text(
                text = "Complete demonstration of Material 3 UI components",
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
                        text = "Buttons",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(onClick = { }) {
                            Text("Button")
                        }
                        
                        ElevatedButton(onClick = { }) {
                            Text("Elevated")
                        }
                        
                        FilledTonalButton(onClick = { }) {
                            Text("Tonal")
                        }
                    }
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        OutlinedButton(onClick = { }) {
                            Text("Outlined")
                        }
                        
                        TextButton(onClick = { }) {
                            Text("Text")
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
                        text = "Selection Controls",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    var checkedState by remember { mutableStateOf(false) }
                    var switchState by remember { mutableStateOf(false) }
                    var radioSelection by remember { mutableStateOf("Option 1") }
                    var sliderValue by remember { mutableFloatStateOf(0.5f) }
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = checkedState,
                            onCheckedChange = { checkedState = it }
                        )
                        Text("Checkbox")
                        
                        Spacer(modifier = Modifier.width(16.dp))
                        
                        Switch(
                            checked = switchState,
                            onCheckedChange = { switchState = it }
                        )
                        Text("Switch")
                    }
                    
                    Column {
                        Text("Radio Buttons:")
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = radioSelection == "Option 1",
                                onClick = { radioSelection = "Option 1" }
                            )
                            Text("Option 1")
                            
                            Spacer(modifier = Modifier.width(16.dp))
                            
                            RadioButton(
                                selected = radioSelection == "Option 2",
                                onClick = { radioSelection = "Option 2" }
                            )
                            Text("Option 2")
                        }
                    }
                    
                    Column {
                        Text("Slider: ${(sliderValue * 100).toInt()}%")
                        Slider(
                            value = sliderValue,
                            onValueChange = { sliderValue = it },
                            modifier = Modifier.fillMaxWidth()
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
                        text = "Progress Indicators",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CircularProgressIndicator()
                        
                        Column {
                            Text("Linear Progress")
                            LinearProgressIndicator(
                                progress = { 0.7f },
                                modifier = Modifier.width(120.dp)
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
                        text = "Text Input",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    var textFieldValue by remember { mutableStateOf("") }
                    var outlinedTextFieldValue by remember { mutableStateOf("") }
                    
                    TextField(
                        value = textFieldValue,
                        onValueChange = { textFieldValue = it },
                        label = { Text("Filled TextField") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    
                    OutlinedTextField(
                        value = outlinedTextFieldValue,
                        onValueChange = { outlinedTextFieldValue = it },
                        label = { Text("Outlined TextField") },
                        modifier = Modifier.fillMaxWidth()
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
                        text = "Icon Buttons & FABs",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { }) {
                            Icon(Icons.Default.Favorite, contentDescription = "Favorite")
                        }
                        
                        FilledIconButton(onClick = { }) {
                            Icon(Icons.Default.Add, contentDescription = "Add")
                        }
                        
                        FilledTonalIconButton(onClick = { }) {
                            Icon(Icons.Default.Edit, contentDescription = "Edit")
                        }
                        
                        OutlinedIconButton(onClick = { }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete")
                        }
                    }
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        FloatingActionButton(onClick = { }) {
                            Icon(Icons.Default.Add, contentDescription = "Add")
                        }
                        
                        SmallFloatingActionButton(onClick = { }) {
                            Icon(Icons.Default.Edit, contentDescription = "Edit")
                        }
                        
                        LargeFloatingActionButton(onClick = { }) {
                            Icon(Icons.Default.Star, contentDescription = "Star")
                        }
                    }
                    
                    ExtendedFloatingActionButton(
                        onClick = { },
                        icon = { Icon(Icons.Default.Add, contentDescription = "Add") },
                        text = { Text("Extended FAB") }
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
                        text = "Chips",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    var filterChipSelected by remember { mutableStateOf(false) }
                    var inputChipText by remember { mutableStateOf("Input") }
                    var showInputChip by remember { mutableStateOf(true) }
                    
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        item {
                            AssistChip(
                                onClick = { },
                                label = { Text("Assist") },
                                leadingIcon = { Icon(Icons.Default.Star, contentDescription = "Star") }
                            )
                        }
                        
                        item {
                            FilterChip(
                                selected = filterChipSelected,
                                onClick = { filterChipSelected = !filterChipSelected },
                                label = { Text("Filter") },
                                leadingIcon = if (filterChipSelected) {
                                    { Icon(Icons.Default.Check, contentDescription = "Selected") }
                                } else null
                            )
                        }
                        
                        if (showInputChip) {
                            item {
                                InputChip(
                                    selected = false,
                                    onClick = { },
                                    label = { Text(inputChipText) },
                                    trailingIcon = {
                                        Icon(
                                            Icons.Default.Close,
                                            contentDescription = "Remove",
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                )
                            }
                        }
                        
                        item {
                            SuggestionChip(
                                onClick = { },
                                label = { Text("Suggestion") }
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
                        text = "Segmented Buttons",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    var selectedSegment by remember { mutableIntStateOf(0) }
                    val segments = listOf("Option 1", "Option 2", "Option 3")
                    
                    SingleChoiceSegmentedButtonRow {
                        segments.forEachIndexed { index, label ->
                            SegmentedButton(
                                selected = selectedSegment == index,
                                onClick = { selectedSegment = index },
                                shape = SegmentedButtonDefaults.itemShape(index = index, count = segments.size)
                            ) {
                                Text(label)
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
                        text = "Card Variations",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Card(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Filled Card",
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                        
                        ElevatedCard(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Elevated Card",
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                        
                        OutlinedCard(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "Outlined Card",
                                modifier = Modifier.padding(8.dp)
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
                        text = "Badges & Dividers",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BadgedBox(
                            badge = { Badge { Text("99+") } }
                        ) {
                            Icon(Icons.Default.Email, contentDescription = "Email")
                        }
                        
                        BadgedBox(
                            badge = { Badge() }
                        ) {
                            Icon(Icons.Default.Notifications, contentDescription = "Notifications")
                        }
                    }
                    
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                    
                    Text(
                        text = "Divider above",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
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
                        text = "Dialogs & Snackbars",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    var showDialog by remember { mutableStateOf(false) }
                    var showSnackbar by remember { mutableStateOf(false) }
                    val snackbarHostState = remember { SnackbarHostState() }
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(onClick = { showDialog = true }) {
                            Text("Show Dialog")
                        }
                        
                        Button(onClick = { showSnackbar = true }) {
                            Text("Show Snackbar")
                        }
                    }
                    
                    if (showDialog) {
                        AlertDialog(
                            onDismissRequest = { showDialog = false },
                            title = { Text("Dialog Title") },
                            text = { Text("This is a sample dialog with some content.") },
                            confirmButton = {
                                TextButton(onClick = { showDialog = false }) {
                                    Text("OK")
                                }
                            },
                            dismissButton = {
                                TextButton(onClick = { showDialog = false }) {
                                    Text("Cancel")
                                }
                            }
                        )
                    }
                    
                    if (showSnackbar) {
                        LaunchedEffect(snackbarHostState) {
                            snackbarHostState.showSnackbar(
                                message = "This is a snackbar message",
                                actionLabel = "Action"
                            )
                            showSnackbar = false
                        }
                    }
                    
                    SnackbarHost(hostState = snackbarHostState)
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
                        text = "Range Slider",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    var rangeSliderState by remember { mutableStateOf(0.2f..0.8f) }
                    
                    Text("Range: ${(rangeSliderState.start * 100).toInt()}% - ${(rangeSliderState.endInclusive * 100).toInt()}%")
                    
                    RangeSlider(
                        value = rangeSliderState,
                        onValueChange = { rangeSliderState = it },
                        modifier = Modifier.fillMaxWidth()
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
                        text = "Top App Bar",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        TopAppBar(
                            title = { Text("Small Top App Bar") },
                            navigationIcon = {
                                IconButton(onClick = { }) {
                                    Icon(Icons.Default.Menu, contentDescription = "Menu")
                                }
                            },
                            actions = {
                                IconButton(onClick = { }) {
                                    Icon(Icons.Default.Search, contentDescription = "Search")
                                }
                                IconButton(onClick = { }) {
                                    Icon(Icons.Default.MoreVert, contentDescription = "More")
                                }
                            }
                        )
                        
                        CenterAlignedTopAppBar(
                            title = { Text("Center Aligned") },
                            navigationIcon = {
                                IconButton(onClick = { }) {
                                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                                }
                            }
                        )
                    }
                }
            }
        }
        
        item {
            Text(
                text = "🎉 Comprehensive Material 3 Components Showcase Complete!\n\n" +
                        "This screen demonstrates all major Material 3 components available in Jetpack Compose:\n\n" +
                        "• Buttons (All 5 variants)\n" +
                        "• Icon Buttons & FABs (All 4 variants)\n" +
                        "• Chips (All 4 types)\n" +
                        "• Selection Controls (Checkbox, Switch, Radio, Sliders)\n" +
                        "• Progress Indicators\n" +
                        "• Text Inputs (Filled & Outlined)\n" +
                        "• Cards (All 3 variants)\n" +
                        "• Badges & Dividers\n" +
                        "• Dialogs & Snackbars\n" +
                        "• Segmented Buttons\n" +
                        "• Top App Bars\n" +
                        "• Range Sliders",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center
            )
        }
    }
}
