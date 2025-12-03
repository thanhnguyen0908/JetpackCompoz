package com.example.jetpackcompoz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val fontFamily = FontFamily(
            Font(R.font.roboto_thin, FontWeight.Thin),
            Font(R.font.roboto_medium, FontWeight.Medium),
            Font(R.font.roboto_extra_bold, FontWeight.ExtraBold),
            Font(R.font.roboto_extra_light, FontWeight.ExtraLight),
            Font(R.font.roboto_semi_bold, FontWeight.SemiBold),
            Font(R.font.roboto_bold, FontWeight.Bold),
            Font(R.font.roboto_regular, FontWeight.Normal),
        )

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { App() }
    }
}
