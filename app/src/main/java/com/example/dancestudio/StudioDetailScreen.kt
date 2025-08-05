package com.example.dancestudio

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.dancestudio.ui.theme.Manrope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudioDetailScreen(studioId: String, navController: NavHostController) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Studio Details", fontFamily = Manrope) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "Selected Studio ID: $studioId",
                style = MaterialTheme.typography.titleLarge.copy(fontFamily = Manrope)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Here you can expand with full studio info, images, booking, etc.")
        }
    }
}
