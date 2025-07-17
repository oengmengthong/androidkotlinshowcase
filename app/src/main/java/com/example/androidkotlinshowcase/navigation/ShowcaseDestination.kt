package com.example.androidkotlinshowcase.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

enum class ShowcaseScreen(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val description: String
) {
    HOME(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home,
        description = "Welcome to Android Kotlin Showcase"
    ),
    FOUNDATION(
        route = "foundation",
        title = "Foundation",
        icon = Icons.Default.Build,
        description = "Core Building Blocks"
    ),
    MATERIAL3(
        route = "material3",
        title = "Material 3",
        icon = Icons.Default.Star,
        description = "UI Components"
    ),
    RUNTIME(
        route = "runtime",
        title = "Runtime",
        icon = Icons.Default.PlayArrow,
        description = "State & Side Effects"
    ),
    ANIMATIONS(
        route = "animations",
        title = "Animations",
        icon = Icons.Default.PlayArrow,
        description = "Motion & Transitions"
    ),
    GESTURES(
        route = "gestures",
        title = "Gestures",
        icon = Icons.Default.Settings,
        description = "Touch & Input Handling"
    ),
    GRAPHICS(
        route = "graphics",
        title = "Graphics",
        icon = Icons.Default.Edit,
        description = "Custom Drawing"
    ),
    SYSTEM_UI(
        route = "system_ui",
        title = "System UI",
        icon = Icons.Default.Settings,
        description = "Window & Platform Integration"
    ),
    INTEROP(
        route = "interop",
        title = "Interop",
        icon = Icons.Default.Share,
        description = "Legacy View Integration"
    ),
    LAYOUTS(
        route = "layouts",
        title = "Layouts",
        icon = Icons.Default.Build,
        description = "Advanced Layout Patterns"
    ),
    ADVANCED_LISTS(
        route = "advanced_lists",
        title = "Lists",
        icon = Icons.Default.List,
        description = "Grids & Advanced Lists"
    ),
    INPUT_FORMS(
        route = "input_forms",
        title = "Input",
        icon = Icons.Default.Edit,
        description = "Forms & Input Controls"
    ),
    PERFORMANCE(
        route = "performance",
        title = "Performance",
        icon = Icons.Default.Settings,
        description = "Optimization & Performance"
    )
}

val showcaseScreens = listOf(
    ShowcaseScreen.HOME,
    ShowcaseScreen.FOUNDATION,
    ShowcaseScreen.MATERIAL3,
    ShowcaseScreen.RUNTIME,
    ShowcaseScreen.ANIMATIONS,
    ShowcaseScreen.GESTURES,
    ShowcaseScreen.GRAPHICS,
    ShowcaseScreen.SYSTEM_UI,
    ShowcaseScreen.INTEROP,
    ShowcaseScreen.LAYOUTS,
    ShowcaseScreen.ADVANCED_LISTS,
    ShowcaseScreen.INPUT_FORMS,
    ShowcaseScreen.PERFORMANCE
)
