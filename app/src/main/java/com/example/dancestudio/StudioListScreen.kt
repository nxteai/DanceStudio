package com.example.dancestudio

// Import necessary Compose UI elements and utilities
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dancestudio.ui.theme.DanceStudioTheme
import com.example.dancestudio.ui.theme.Manrope

// Main composable that displays the screen with filters, search, and studio list
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudioListScreen(
    modifier: Modifier = Modifier,
    onStudioSelected: (Studio) -> Unit = {},  // Callback when a studio is tapped
    onFilterChanged: (FilterType) -> Unit = {} // Callback when a filter is changed
) {
    var searchQuery by remember { mutableStateOf("") } // Tracks current search input
    val filters = listOf("Best", "Popular", "Nearby", "New", "Affordable") // Filter chip labels
    var selectedFilter by remember { mutableIntStateOf(0) } // Currently selected filter index

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp) // Outer padding
    ) {
        // LOCATION HEADER
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location",
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "You are here · Indonesia",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontFamily = Manrope,
                    color = Color.Gray
                )
            )
        }

        // SEARCH BAR
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            placeholder = { Text("Enter city or studio name") },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "Search")
            },
            shape = RoundedCornerShape(12.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White
            )
        )

        // HORIZONTAL FILTER CHIPS (SCROLLABLE)
        Row(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .horizontalScroll(rememberScrollState()), // Enables horizontal scrolling
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            filters.forEachIndexed { index, filter ->
                FilterChip(
                    selected = selectedFilter == index,
                    onClick = {
                        selectedFilter = index
                        onFilterChanged(FilterType.entries[index]) // Notify parent about filter change
                    },
                    label = { Text(filter, fontFamily = Manrope) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color.Black,
                        selectedLabelColor = Color.White,
                        containerColor = Color(0xFFF5F5F5)
                    )
                )
            }
        }

        // VERTICAL STUDIO LIST (SCROLLABLE DOWN)
        LazyColumn(
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(getSampleStudioList()) { studio ->
                StudioCard(
                    studio = studio,
                    modifier = Modifier.padding(bottom = 16.dp),
                    onClick = { onStudioSelected(studio) } // Handle card click
                )
            }
        }
    }
}

// Sample data for preview and development use
private fun getSampleStudioList(): List<Studio> {
    return listOf(
        Studio(
            id = "1",
            name = "Rhythm Dance Studio",
            location = "Jakarta",
            size = 150,
            hourlyRate = 25,
            rating = 4.93f,
            imageRes = R.drawable.studio_sample // Replace with actual drawable
        ),
        Studio(
            id = "2",
            name = "Urban Dance Space",
            location = "Bandung",
            size = 200,
            hourlyRate = 30,
            rating = 4.8f,
            imageRes = R.drawable.studio_sample // Replace with actual drawable
        )
        // Add more items here to test scrolling
    )
}

// Studio card component displaying image, rating, and studio info
@Composable
fun StudioCard(
    studio: Studio,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick), // Makes entire card tappable
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {
            // STUDIO IMAGE WITH RATING OVERLAY
            Box(modifier = Modifier.aspectRatio(1.5f)) {
                Image(
                    painter = painterResource(id = studio.imageRes),
                    contentDescription = studio.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // RATING BADGE TOP-RIGHT
                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(Color.Black, RoundedCornerShape(16.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .align(Alignment.TopEnd)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Rating",
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = studio.rating.toString(),
                            color = Color.White,
                            fontFamily = Manrope,
                            fontSize = 12.sp
                        )
                    }
                }
            }

            // STUDIO DETAILS TEXT
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = studio.name,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontFamily = Manrope,
                        fontWeight = FontWeight.Bold
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Place,
                        contentDescription = "Location",
                        tint = Color.Gray,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = studio.location,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontFamily = Manrope,
                            color = Color.Gray
                        )
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Size: ${studio.size} m²",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = Manrope,
                        color = Color.Gray
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "$${studio.hourlyRate}/hour",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = Manrope,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )
            }
        }
    }
}

// Data model representing a dance studio
data class Studio(
    val id: String,
    val name: String,
    val location: String,
    val size: Int,
    val hourlyRate: Int,
    val rating: Float,
    val imageRes: Int
)

// Enum representing different filter types
enum class FilterType { BEST, POPULAR, NEARBY, NEW, AFFORDABLE }

// Preview function to display the UI inside Android Studio Preview
@Preview
@Composable
fun StudioListPreview() {
    DanceStudioTheme {
        StudioListScreen()
    }
}
