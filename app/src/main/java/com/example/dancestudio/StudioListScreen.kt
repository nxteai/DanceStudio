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
import androidx.compose.ui.draw.clip
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

/**
 * Main composable function for the Studio List screen.
 * Displays the location header, search input, filter chips, and a scrollable list of studio cards.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudioListScreen(
    modifier: Modifier = Modifier,
    onStudioSelected: (Studio) -> Unit = {},  // Callback when a studio card is clicked
    onFilterChanged: (FilterType) -> Unit = {} // Callback when a filter is selected
) {
    var searchQuery by remember { mutableStateOf("") } // Search input state
    val filters = listOf("Best", "Popular", "Nearby", "New", "Affordable") // List of filter options
    var selectedFilter by remember { mutableIntStateOf(0) } // Index of the selected filter

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp)
    ) {
        // LOCATION INDICATOR HEADER
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location Icon",
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "You are here · Indonesia",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontFamily = Manrope,
                    color = Color.Red
                )
            )
        }

        // SEARCH TEXT FIELD
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it }, // Updates search query
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

        // HORIZONTAL SCROLLABLE FILTER CHIPS
        Row(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            filters.forEachIndexed { index, filter ->
                FilterChip(
                    selected = selectedFilter == index,
                    onClick = {
                        selectedFilter = index
                        onFilterChanged(FilterType.entries[index]) // Notify filter change
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

        // VERTICAL LIST OF STUDIOS
        LazyColumn(
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(getSampleStudioList()) { studio ->
                StudioCard(
                    studio = studio,
                    modifier = Modifier.padding(bottom = 16.dp),
                    onClick = { onStudioSelected(studio) } // Card click handler
                )
            }
        }
    }
}

/**
 * Provides sample static studio data for UI previews and testing.
 */
private fun getSampleStudioList(): List<Studio> {
    return listOf(
        Studio(
            id = "1",
            name = "Rhythm Dance Studio",
            location = "Jakarta",
            size = 150,
            hourlyRate = 25,
            rating = 4.93f,
            imageRes = R.drawable.studio_sample // Placeholder image resource
        ),
        Studio(
            id = "2",
            name = "Urban Dance Space",
            location = "Bandung",
            size = 200,
            hourlyRate = 30,
            rating = 4.8f,
            imageRes = R.drawable.studio_sample
        )
        // Add more studios here to populate the list
    )
}

/**
 * Composable for displaying individual studio card with image, rating, and info.
 */
@Composable
fun StudioCard(
    studio: Studio,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick), // Entire card is tappable
        shape = RoundedCornerShape(16.dp), // Rounded card corners
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column {
            // STUDIO IMAGE SECTION
            Box(
                modifier = Modifier
                    .aspectRatio(1.5f) // Sets aspect ratio for consistent image shape
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)) // Rounded top corners
            ) {
                Image(
                    painter = painterResource(id = studio.imageRes),
                    contentDescription = studio.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // RATING BADGE OVERLAY
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
                        Text(
                            text = studio.rating.toString(),
                            color = Color.White,
                            modifier = Modifier.padding(start = 4.dp),
                            fontSize = 12.sp
                        )
                    }
                }
            }

            // STUDIO TEXT DETAILS SECTION
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Left column: studio name, location, size
                    Column {
                        Text(
                            text = studio.name,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontFamily = Manrope,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = "Location",
                                tint = Color.Gray,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = studio.location,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontFamily = Manrope,
                                    color = Color.Gray
                                ),
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Size: ${studio.size} m²",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontFamily = Manrope,
                                color = Color.Gray
                            )
                        )
                    }

                    // Right column: pricing info
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "$${studio.hourlyRate}",
                            style = MaterialTheme.typography.headlineSmall.copy(
                                fontFamily = Manrope,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        )
                        Text(
                            text = "per hour",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontFamily = Manrope,
                                color = Color.Gray
                            )
                        )
                    }
                }
            }
        }
    }
}

/**
 * Data model class representing a dance studio entity.
 */
data class Studio(
    val id: String,
    val name: String,
    val location: String,
    val size: Int,            // Studio size in square meters
    val hourlyRate: Int,      // Rental rate per hour in USD
    val rating: Float,        // Rating out of 5
    val imageRes: Int         // Resource ID for the studio image
)

/**
 * Enum for studio filter options.
 * Matches UI filter chip labels for filtering the studio list.
 */
enum class FilterType { BEST, POPULAR, NEARBY, NEW, AFFORDABLE }

/**
 * Preview function to render the StudioListScreen inside Android Studio.
 * Useful for UI testing during development.
 */
@Preview
@Composable
fun StudioListPreview() {
    DanceStudioTheme {
        StudioListScreen()
    }
}
