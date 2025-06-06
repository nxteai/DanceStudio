package com.example.dancestudio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.dancestudio.ui.theme.DanceStudioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DanceStudioTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    BookingCard()
                }
            }
        }
    }
}
