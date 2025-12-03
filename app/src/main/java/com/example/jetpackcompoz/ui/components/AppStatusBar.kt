package com.example.jetpackcompoz.ui.components

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.Dp
import androidx.core.view.WindowCompat

@Composable
fun AppStatusBar(
    statusBarColor: Color = Color.Transparent,
    isLight: Boolean = true
) {
    val statusBarHeight: Dp = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
    val view = LocalView.current
    val window = (view.context as Activity).window
    SideEffect {
        WindowCompat.getInsetsController(window, view)
            .isAppearanceLightStatusBars = isLight
    }

    Box(
        modifier = Modifier
            .height(statusBarHeight)
            .fillMaxWidth()
            .background(color = statusBarColor)
    )
}