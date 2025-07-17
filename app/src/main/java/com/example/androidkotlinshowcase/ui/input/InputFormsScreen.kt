package com.example.androidkotlinshowcase.ui.input

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputFormsScreen(
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
                    text = "Input & Forms",
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center
                )
                
                Text(
                    text = "Text fields, search bars, sliders, and form validation",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
        
        item {
            TextFieldVariantsDemo()
        }
        
        item {
            SearchBarDemo()
        }
        
        item {
            SliderDemo()
        }
        
        item {
            FormValidationDemo()
        }
        
        item {
            SelectionControlsDemo()
        }
        
        item {
            PasswordFieldDemo()
        }
    }
}

@Composable
private fun TextFieldVariantsDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "TextField Variants",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            var standardText by remember { mutableStateOf("") }
            var outlinedText by remember { mutableStateOf("") }
            var errorText by remember { mutableStateOf("") }
            val isError = errorText.length < 3 && errorText.isNotEmpty()
            
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Standard TextField
                TextField(
                    value = standardText,
                    onValueChange = { standardText = it },
                    label = { Text("Standard TextField") },
                    placeholder = { Text("Enter text...") },
                    modifier = Modifier.fillMaxWidth()
                )
                
                // Outlined TextField
                OutlinedTextField(
                    value = outlinedText,
                    onValueChange = { outlinedText = it },
                    label = { Text("Outlined TextField") },
                    placeholder = { Text("Enter text...") },
                    modifier = Modifier.fillMaxWidth()
                )
                
                // Error state
                OutlinedTextField(
                    value = errorText,
                    onValueChange = { errorText = it },
                    label = { Text("Error State") },
                    placeholder = { Text("Min 3 characters") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = isError,
                    supportingText = {
                        if (isError) {
                            Text("Must be at least 3 characters")
                        }
                    }
                )
            }
            
            Text(
                text = "Different TextField styles with error states and validation",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchBarDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Search Bar",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            var searchText by remember { mutableStateOf("") }
            var active by remember { mutableStateOf(false) }
            
            val searchHistory = remember {
                listOf("Previous search 1", "Previous search 2", "Previous search 3")
            }
            
            SearchBar(
                query = searchText,
                onQueryChange = { searchText = it },
                onSearch = { 
                    active = false
                    // Handle search
                },
                active = active,
                onActiveChange = { active = it },
                placeholder = { Text("Search anything...") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                },
                trailingIcon = {
                    if (searchText.isNotEmpty()) {
                        IconButton(onClick = { searchText = "" }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear"
                            )
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                // Search suggestions
                Column {
                    searchHistory.forEach { suggestion ->
                        ListItem(
                            headlineContent = { Text(suggestion) },
                            leadingContent = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = null
                                )
                            },
                            modifier = Modifier.clickable {
                                searchText = suggestion
                                active = false
                            }
                        )
                    }
                }
            }
            
            Text(
                text = "SearchBar with suggestions and search history",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun SliderDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Sliders",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            var sliderValue by remember { mutableFloatStateOf(50f) }
            var rangeSliderValue by remember { mutableStateOf(20f..80f) }
            
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Single Slider
                Column {
                    Text(
                        text = "Volume: ${sliderValue.toInt()}%",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )
                    
                    Slider(
                        value = sliderValue,
                        onValueChange = { sliderValue = it },
                        valueRange = 0f..100f,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                
                // Range Slider
                Column {
                    Text(
                        text = "Price Range: $${rangeSliderValue.start.toInt()} - $${rangeSliderValue.endInclusive.toInt()}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )
                    
                    RangeSlider(
                        value = rangeSliderValue,
                        onValueChange = { rangeSliderValue = it },
                        valueRange = 0f..100f,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                
                // Discrete Slider
                var discreteValue by remember { mutableFloatStateOf(3f) }
                
                Column {
                    Text(
                        text = "Rating: ${discreteValue.toInt()}/5",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )
                    
                    Slider(
                        value = discreteValue,
                        onValueChange = { discreteValue = it },
                        valueRange = 1f..5f,
                        steps = 3,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            
            Text(
                text = "Sliders for numeric input with continuous and discrete values",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun FormValidationDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Form Validation",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            var email by remember { mutableStateOf("") }
            var name by remember { mutableStateOf("") }
            var agreeToTerms by remember { mutableStateOf(false) }
            
            val isEmailValid = email.contains("@") && email.contains(".")
            val isNameValid = name.length >= 2
            val isFormValid = isEmailValid && isNameValid && agreeToTerms
            
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Name field
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Full Name") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = name.isNotEmpty() && !isNameValid,
                    supportingText = {
                        if (name.isNotEmpty() && !isNameValid) {
                            Text("Name must be at least 2 characters")
                        }
                    }
                )
                
                // Email field
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = email.isNotEmpty() && !isEmailValid,
                    supportingText = {
                        if (email.isNotEmpty() && !isEmailValid) {
                            Text("Please enter a valid email")
                        }
                    }
                )
                
                // Checkbox
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = agreeToTerms,
                        onCheckedChange = { agreeToTerms = it }
                    )
                    Text(
                        text = "I agree to the terms and conditions",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                
                // Submit button
                Button(
                    onClick = { /* Handle submit */ },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = isFormValid
                ) {
                    Text("Submit")
                }
                
                // Form status
                if (!isFormValid) {
                    Text(
                        text = "Please complete all fields correctly",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
            
            Text(
                text = "Real-time form validation with error states and submission control",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun SelectionControlsDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Selection Controls",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            var switchChecked by remember { mutableStateOf(false) }
            var checkboxChecked by remember { mutableStateOf(false) }
            var selectedOption by remember { mutableStateOf("Option 1") }
            
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Switch
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Enable notifications",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Switch(
                        checked = switchChecked,
                        onCheckedChange = { switchChecked = it }
                    )
                }
                
                // Checkbox
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = checkboxChecked,
                        onCheckedChange = { checkboxChecked = it }
                    )
                    Text(
                        text = "Subscribe to newsletter",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                
                // Radio buttons
                Text(
                    text = "Choose an option:",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
                
                Column(
                    modifier = Modifier.selectableGroup()
                ) {
                    listOf("Option 1", "Option 2", "Option 3").forEach { option ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .selectable(
                                    selected = selectedOption == option,
                                    onClick = { selectedOption = option },
                                    role = Role.RadioButton
                                )
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = selectedOption == option,
                                onClick = null
                            )
                            Text(
                                text = option,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }
            }
            
            Text(
                text = "Various selection controls for different input types",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun PasswordFieldDemo() {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Password Field",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            var password by remember { mutableStateOf("") }
            var confirmPassword by remember { mutableStateOf("") }
            var passwordVisible by remember { mutableStateOf(false) }
            
            val passwordsMatch = password == confirmPassword && password.isNotEmpty()
            val isPasswordStrong = password.length >= 8
            
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = if (passwordVisible) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    trailingIcon = {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = if (passwordVisible) {
                                    Icons.Default.Clear // Using Clear as placeholder for visibility off
                                } else {
                                    Icons.Default.Search // Using Search as placeholder for visibility
                                },
                                contentDescription = if (passwordVisible) "Hide password" else "Show password"
                            )
                        }
                    },
                    supportingText = {
                        Text(
                            text = if (isPasswordStrong) "Strong password" else "Password must be at least 8 characters",
                            color = if (isPasswordStrong) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                        )
                    }
                )
                
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Confirm Password") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    isError = confirmPassword.isNotEmpty() && !passwordsMatch,
                    supportingText = {
                        if (confirmPassword.isNotEmpty() && !passwordsMatch) {
                            Text("Passwords do not match")
                        }
                    }
                )
                
                // Password strength indicator
                if (password.isNotEmpty()) {
                    Column {
                        Text(
                            text = "Password strength:",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Medium
                        )
                        
                        LinearProgressIndicator(
                            progress = { (password.length / 12f).coerceAtMost(1f) },
                            modifier = Modifier.fillMaxWidth(),
                            color = when {
                                password.length < 6 -> MaterialTheme.colorScheme.error
                                password.length < 10 -> MaterialTheme.colorScheme.tertiary
                                else -> MaterialTheme.colorScheme.primary
                            }
                        )
                    }
                }
            }
            
            Text(
                text = "Password fields with visibility toggle and strength validation",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
