package com.example.androidkotlinshowcase.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.androidkotlinshowcase.ui.home.HomeScreen
import com.example.androidkotlinshowcase.ui.foundation.FoundationScreen
import com.example.androidkotlinshowcase.ui.material3.Material3Screen
import com.example.androidkotlinshowcase.ui.runtime.RuntimeScreen
import com.example.androidkotlinshowcase.ui.animation.AnimationScreen
import com.example.androidkotlinshowcase.ui.gestures.GesturesScreen
import com.example.androidkotlinshowcase.ui.graphics.GraphicsScreen
import com.example.androidkotlinshowcase.ui.system.SystemUIScreen
import com.example.androidkotlinshowcase.ui.interop.InteropScreen
import com.example.androidkotlinshowcase.ui.layouts.LayoutsScreen
import com.example.androidkotlinshowcase.ui.lists.AdvancedListsScreen
import com.example.androidkotlinshowcase.ui.input.InputFormsScreen
import com.example.androidkotlinshowcase.ui.performance.PerformanceScreen
import com.example.androidkotlinshowcase.ui.advancedui.AdvancedUINav

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowcaseApp(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    
    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                
                bottomNavigationScreens.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.title) },
                        label = { Text(screen.title) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ShowcaseScreen.HOME.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(ShowcaseScreen.HOME.route) {
                HomeScreen(navController = navController)
            }
            composable(ShowcaseScreen.FOUNDATION.route) {
                FoundationScreen()
            }
            composable(ShowcaseScreen.MATERIAL3.route) {
                Material3Screen()
            }
            composable(ShowcaseScreen.RUNTIME.route) {
                RuntimeScreen()
            }
            composable(ShowcaseScreen.ANIMATIONS.route) {
                AnimationScreen()
            }
            composable(ShowcaseScreen.GESTURES.route) {
                GesturesScreen()
            }
            composable(ShowcaseScreen.GRAPHICS.route) {
                GraphicsScreen()
            }
            composable(ShowcaseScreen.SYSTEM_UI.route) {
                SystemUIScreen()
            }
            composable(ShowcaseScreen.INTEROP.route) {
                InteropScreen()
            }
            composable(ShowcaseScreen.LAYOUTS.route) {
                LayoutsScreen()
            }
            composable(ShowcaseScreen.ADVANCED_LISTS.route) {
                AdvancedListsScreen()
            }
            composable(ShowcaseScreen.INPUT_FORMS.route) {
                InputFormsScreen()
            }
            composable(ShowcaseScreen.PERFORMANCE.route) {
                PerformanceScreen()
            }
            composable(ShowcaseScreen.ADVANCED_UI.route) {
                AdvancedUINav()
            }
        }
    }
}
