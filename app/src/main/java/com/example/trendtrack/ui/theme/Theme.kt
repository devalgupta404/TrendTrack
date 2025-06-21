package com.example.trendtrack.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF90CAF9),          // Light Blue
    onPrimary = Color(0xFF000000),
    primaryContainer = Color(0xFF1976D2),  // Darker Blue
    onPrimaryContainer = Color(0xFFFFFFFF),
    
    secondary = Color(0xFF81D4FA),        // Light Blue
    onSecondary = Color(0xFF000000),
    secondaryContainer = Color(0xFF0288D1), // Darker Blue
    onSecondaryContainer = Color(0xFFFFFFFF),
    
    tertiary = Color(0xFF80DEEA),         // Cyan
    onTertiary = Color(0xFF000000),
    tertiaryContainer = Color(0xFF0097A7), // Darker Cyan
    onTertiaryContainer = Color(0xFFFFFFFF),
    
    background = Color(0xFF121212),       // Dark Background
    onBackground = Color(0xFFFFFFFF),
    surface = Color(0xFF1E1E1E),          // Slightly Lighter Dark
    onSurface = Color(0xFFFFFFFF),
    
    surfaceVariant = Color(0xFF2D2D2D),   // Dark Surface Variant
    onSurfaceVariant = Color(0xFFB0B0B0),
    
    error = Color(0xFFCF6679),            // Error Red
    onError = Color(0xFF000000),
    errorContainer = Color(0xFFB00020),   // Darker Error
    onErrorContainer = Color(0xFFFFFFFF)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF1976D2),          // Blue
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFBBDEFB),  // Light Blue
    onPrimaryContainer = Color(0xFF000000),
    
    secondary = Color(0xFF03A9F4),        // Light Blue
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFB3E5FC), // Very Light Blue
    onSecondaryContainer = Color(0xFF000000),
    
    tertiary = Color(0xFF00BCD4),         // Cyan
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFB2EBF2), // Very Light Cyan
    onTertiaryContainer = Color(0xFF000000),
    
    background = Color(0xFFF5F5F5),       // Light Gray Background
    onBackground = Color(0xFF000000),
    surface = Color(0xFFFFFFFF),          // White
    onSurface = Color(0xFF000000),
    
    surfaceVariant = Color(0xFFE0E0E0),   // Light Gray
    onSurfaceVariant = Color(0xFF424242),
    
    error = Color(0xFFB00020),            // Error Red
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFCDAD7),   // Light Error
    onErrorContainer = Color(0xFF000000)
)

@Composable
fun TrendTrackTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}