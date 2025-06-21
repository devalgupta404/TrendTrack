package com.example.trendtrack.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit
) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 2000
        ),
        label = "Splash Alpha Animation"
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(2500)
        onSplashFinished()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = if (MaterialTheme.colorScheme.background == Color.White) {
            Color(0xFFF5F5F5) // Light mode background
        } else {
            Color(0xFF1A1A1A) // Dark mode background
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .alpha(alphaAnim.value),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "TrendTrack",
                    color = if (MaterialTheme.colorScheme.background == Color.White) {
                        Color(0xFF2196F3) // Light mode primary color
                    } else {
                        Color(0xFF90CAF9) // Dark mode primary color
                    },
                    fontSize = 42.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Your Daily News Companion",
                    color = if (MaterialTheme.colorScheme.background == Color.White) {
                        Color(0xFF757575) // Light mode secondary color
                    } else {
                        Color(0xFFB0BEC5) // Dark mode secondary color
                    },
                    fontSize = 18.sp
                )
            }
        }
    }
} 