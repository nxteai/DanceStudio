package com.example.dancestudio

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dancestudio.ui.theme.DanceStudioTheme
import com.example.dancestudio.ui.theme.Manrope

@Composable
fun SignUpScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(Color(0xFF00C6FF), Color(0xFF0072FF))
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Sign Up",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(8.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    InputField(label = "Full Name", icon = Icons.Default.Person)
                    Spacer(modifier = Modifier.height(12.dp))
                    InputField(label = "Email", icon = Icons.Default.Email)
                    Spacer(modifier = Modifier.height(12.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "+225",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        InputField(label = "Mobile Number", icon = Icons.Default.Phone)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    InputField(
                        label = "Password",
                        icon = Icons.Default.Lock,
                        visualTransformation = PasswordVisualTransformation()
                    )
                }
            }

            Button(
                onClick = { /* TODO: Handle create account */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    text = "Create Account",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.White
                )
            }

            Text(
                text = "or sign in using",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                SocialButton("Facebook", Color(0xFF3b5998), modifier = Modifier.weight(1f))
                SocialButton("Google", Color(0xFFdb4a39), modifier = Modifier.weight(1f))
            }

            Text(
                text = "By creating an account, you agree to our Terms",
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 12.sp),
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Text(
                text = "Already have an account? Sign In",
                style = MaterialTheme.typography.labelLarge,
                color = Color.White
            )
        }
    }
}

@Composable
fun InputField(
    label: String,
    icon: ImageVector,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    val textState = remember { mutableStateOf(TextFieldValue()) }

    OutlinedTextField(
        value = textState.value,
        onValueChange = { textState.value = it },
        label = { Text(text = label, fontFamily = Manrope) },
        leadingIcon = { Icon(icon, contentDescription = null) },
        visualTransformation = visualTransformation,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text
        ),
        textStyle = MaterialTheme.typography.bodyLarge.copy(fontFamily = Manrope)
    )
}

@Composable
fun SocialButton(text: String, color: Color, modifier: Modifier = Modifier) {
    Button(
        onClick = { /* TODO: Social login */ },
        colors = ButtonDefaults.buttonColors(containerColor = color),
        shape = RoundedCornerShape(12.dp),
        modifier = modifier.height(48.dp)
    ) {
        Text(
            text = text,
            color = Color.White,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

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

@Composable
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
}

// Preview showing both buttons stacked
@Preview(showBackground = true)
@Composable
fun ButtonPairPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MyBlackButton()
        MyWhiteButton()
    }
}