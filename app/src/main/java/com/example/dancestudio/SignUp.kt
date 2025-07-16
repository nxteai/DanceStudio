package com.example.dancestudio

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dancestudio.ui.theme.DanceStudioTheme
import com.example.dancestudio.ui.theme.Manrope
import androidx.compose.material.icons.filled.ArrowBack


@Composable
fun SignUpScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // 🔙 Back Arrow
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    navController.navigate("welcome") {
                        popUpTo("signup") { inclusive = true } // Optional: remove 'signup' from backstack
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
            }

            Text(
                text = "Sign Up",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontFamily = Manrope,
                    color = Color.Black
                ),
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    InputField(label = "Full Name", icon = Icons.Default.Person)
                    Spacer(modifier = Modifier.height(12.dp))
                    InputField(label = "Email", icon = Icons.Default.Email, keyboardType = KeyboardType.Email)
                    Spacer(modifier = Modifier.height(12.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "+225",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontFamily = Manrope,
                                color = Color.Black
                            ),
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        InputField(label = "Mobile Number", icon = Icons.Default.Phone, keyboardType = KeyboardType.Phone)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    InputField(
                        label = "Password",
                        icon = Icons.Default.Lock,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardType = KeyboardType.Password
                    )
                }
            }

            Button(
                onClick = { navController.navigate("signup") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
            ) {
                Text(
                    text = "Create Account",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontFamily = Manrope,
                        color = Color.White
                    )
                )
            }

            Text(
                text = "or sign in using",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontFamily = Manrope,
                    color = Color.Gray
                ),
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
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontFamily = Manrope,
                    fontSize = 12.sp,
                    color = Color.Gray
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            Text(
                text = "Already have an account? Sign In",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontFamily = Manrope,
                    color = Color.Black
                ),
                modifier = Modifier.clickable {
                    // TODO: Navigate to sign-in screen
                }
            )
        }
    }
}


@Composable

fun SocialButton(
    text: String,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        modifier = modifier.padding(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color)
    ) {
        Text(text = text, color = Color.White)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    label: String,
    icon: ImageVector,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    val textState = remember { mutableStateOf(TextFieldValue()) }

    OutlinedTextField(
        value = textState.value,
        onValueChange = { textState.value = it },
        label = { Text(text = label, fontFamily = Manrope, color = Color.Gray) },
        leadingIcon = { Icon(icon, contentDescription = null, tint = Color.Black) },
        visualTransformation = visualTransformation,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Next,
            keyboardType = keyboardType
        ),
        textStyle = MaterialTheme.typography.bodyLarge.copy(
            fontFamily = Manrope,
            color = Color.Black
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Black,
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Gray,
            )
    )
}


@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    DanceStudioTheme {
        // We provide a dummy NavController since SignUpScreen requires one
        val dummyNavController = rememberNavController()
        SignUpScreen(navController = dummyNavController)
    }
}
