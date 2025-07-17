package com.example.androidkotlinshowcase.ui.advancedui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.ui.graphics.vector.ImageVector

data class AdvancedUiItem(
    val name: String,
    val icon: ImageVector,
    val route: String
)

val advancedUiItems = listOf(
    AdvancedUiItem(
        name = "Shared Element Transitions",
        icon = Icons.Default.ArrowForward,
        route = "shared-element-transitions"
    ),
    AdvancedUiItem(
        name = "Custom Modifiers",
        icon = Icons.Default.ArrowForward,
        route = "custom-modifiers"
    ),
    AdvancedUiItem(
        name = "Advanced Animations",
        icon = Icons.Default.ArrowForward,
        route = "advanced-animations"
    ),
    AdvancedUiItem(
        name = "Complex Gestures",
        icon = Icons.Default.ArrowForward,
        route = "complex-gestures"
    ),
    AdvancedUiItem(
        name = "Custom Layouts",
        icon = Icons.Default.ArrowForward,
        route = "custom-layouts"
    )
)
