package com.example.dancestudio

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dancestudio.ui.theme.DanceStudioTheme
import com.example.dancestudio.ui.theme.Manrope

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import android.widget.Toast
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.ui.platform.LocalContext

@Composable
fun SignUpScreen(navController: NavHostController) {
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()

    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    navController.navigate("welcome") {
                        popUpTo("signup") { inclusive = true }
                    }
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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
                    InputField(label = "Full Name", icon = Icons.Default.Person, textState = name)
                    Spacer(modifier = Modifier.height(12.dp))
                    InputField(label = "Email", icon = Icons.Default.Email, keyboardType = KeyboardType.Email, textState = email)
                    Spacer(modifier = Modifier.height(12.dp))
                    InputField(label = "Mobile Number", icon = Icons.Default.Phone, keyboardType = KeyboardType.Phone, textState = phone)
                    Spacer(modifier = Modifier.height(12.dp))
                    InputField(
                        label = "Password",
                        icon = Icons.Default.Lock,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardType = KeyboardType.Password,
                        textState = password
                    )
                }
            }

            Button(
                onClick = {
                    if (name.value.isBlank() || email.value.isBlank() || password.value.length < 6) {
                        Toast.makeText(context, "Please fill all fields correctly", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    auth.createUserWithEmailAndPassword(email.value, password.value)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val userId = auth.currentUser?.uid ?: return@addOnCompleteListener
                                val userMap = hashMapOf(
                                    "fullName" to name.value,
                                    "email" to email.value,
                                    "phone" to phone.value
                                )

                                db.collection("users").document(userId).set(userMap)
                                    .addOnSuccessListener {
                                        Toast.makeText(context, "Account created!", Toast.LENGTH_SHORT).show()
                                        navController.navigate("home") // Update with your next screen
                                    }
                                    .addOnFailureListener {
                                        Toast.makeText(context, "Failed to save user data", Toast.LENGTH_SHORT).show()
                                    }
                                navController.navigate("home") {
                                    popUpTo("signup") { inclusive = true }
                                }
                            } else {
                                Toast.makeText(context, "Sign up failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                            }
                        }
                },
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
                    navController.navigate("login") // implement this screen later
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
    textState: MutableState<String>,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text
) {
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
        val dummyNavController = rememberNavController()
        SignUpScreen(navController = dummyNavController)
    }
}
