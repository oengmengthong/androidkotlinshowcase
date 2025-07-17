package com.example.androidkotlinshowcase.ui.advancedui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.androidkotlinshowcase.ui.advancedui.AdvancedUIScreen
import com.example.androidkotlinshowcase.ui.advancedui.SharedElementTransitionDemo
import com.example.androidkotlinshowcase.ui.advancedui.CustomModifiersScreen
import com.example.androidkotlinshowcase.ui.advancedui.AdvancedAnimationsScreen
import com.example.androidkotlinshowcase.ui.advancedui.ComplexGesturesScreen
import com.example.androidkotlinshowcase.ui.advancedui.CustomLayoutsScreen
import com.example.androidkotlinshowcase.ui.shared.ComingSoonScreen

@Composable
fun AdvancedUINav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "advanced-ui") {
        composable("advanced-ui") {
            AdvancedUIScreen(navController)
        }
        composable("shared-element-transitions") {
            SharedElementTransitionDemo()
        }
        composable("custom-modifiers") {
            CustomModifiersScreen()
        }
        composable("advanced-animations") {
            AdvancedAnimationsScreen()
        }
        composable("complex-gestures") {
            ComplexGesturesScreen()
        }
        composable("custom-layouts") {
            CustomLayoutsScreen()
        }
    }
}
