package ru.megboyzz.studentq.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import ru.megboyzz.studentq.AsImageVector
import ru.megboyzz.studentq.AsPainter
import ru.megboyzz.studentq.R
import ru.megboyzz.studentq.navigation.AppNavRoute

@Composable
fun SplashScreen(navHostController: NavHostController){
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 500
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(500)
        navHostController.popBackStack()
        navHostController.navigate(AppNavRoute.Auth.route)
    }
    Splash(alpha = alphaAnim.value)
}

@Composable
fun Splash(alpha: Float) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .size(120.dp)
                .alpha(alpha = alpha),
            imageVector = R.drawable.icon.AsImageVector(),
            contentDescription = "Logo Icon",
            tint = Color.White
        )
    }
}