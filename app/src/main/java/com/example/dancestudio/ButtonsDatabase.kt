/*package com.example.dancestudio

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dancestudio.ui.theme.Manrope

@Composable
fun MyBlackButton() {
    Button(
        onClick = { /* Handle click */ },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF000000) // Pure black
        ),
        shape = RoundedCornerShape(10.dp), // Matches Figma's 10 corner radius
        modifier = Modifier
            .width(353.dp) // Exact width from Figma
            .height(56.dp) // Exact height from Figma
    ) {
        Text(
            text = "Button",
            color = Color.White,
            fontSize = 16.sp,
            fontFamily = Manrope, // Using your existing font
            fontWeight = FontWeight.Bold,
            lineHeight = 20.sp // 125% of 16sp = 20sp
        )
    }
}

/*@Composable
fun MyWhiteButton() {
    Button(
        onClick = { /* Handle click */ },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White, // White background
            contentColor = Color.Black  // Black text
        ),
        shape = RoundedCornerShape(10.dp), // Same corner radius as black button
        border = BorderStroke(1.dp, Color.Black), // Black border
        modifier = Modifier
            .width(353.dp) // Same width as black button
            .height(56.dp) // Same height as black button
    ) {
        Text(
            text = "Button",
            fontSize = 16.sp,
            fontFamily = Manrope,
            fontWeight = FontWeight.SemiBold,
            lineHeight = 20.sp // 125% line height
        )
    }
} */