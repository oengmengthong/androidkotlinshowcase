package com.example.androidkotlinshowcase.ui.advancedui

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedElementTransitionDemo() {
    SharedTransitionLayout {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "list") {
            composable("list") { 
                SharedElementListScreen(
                    onItemClick = { imageRes ->
                        navController.navigate("detail/$imageRes")
                    },
                    animatedVisibilityScope = this@composable
                )
            }
            composable("detail/{imageRes}") { backStackEntry ->
                val imageRes = backStackEntry.arguments?.getString("imageRes")?.toInt()
                imageRes?.let {
                    SharedElementDetailScreen(
                        imageRes = it,
                        animatedVisibilityScope = this@composable
                    )
                }
            }
        }
    }
}
