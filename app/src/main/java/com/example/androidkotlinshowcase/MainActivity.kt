package com.example.androidkotlinshowcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.androidkotlinshowcase.navigation.ShowcaseApp
import com.example.androidkotlinshowcase.theme.AndroidKotlinShowcaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidKotlinShowcaseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ShowcaseApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
