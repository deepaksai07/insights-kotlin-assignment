package com.example.insights.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.insights.ui.components.*
import com.example.insights.ui.theme.*

@Composable
fun InsightsScreen() {
    Scaffold(
        topBar = { InsightsTopBar() },
        bottomBar = { InsightsBottomBar() },
        containerColor = AppBackground
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            item { StabilityScoreCard() }
            item { CycleTrendsCard() }
            item { MetabolicTrendsCard() }
            item { BodySignalsCard() }
            item { LifestyleImpactCard() }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsightsTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Insights",
                style = MaterialTheme.typography.titleLarge
            )
        },
        actions = {
            IconButton(onClick = { }) {
                Icon(Icons.Default.Notifications, contentDescription = null, tint = TextPrimary)
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = AppBackground
        )
    )
}

@Composable
fun InsightsBottomBar() {
    BottomAppBar(
        containerColor = Color.White,
        tonalElevation = 8.dp,
        actions = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { }) { Icon(Icons.Default.Home, contentDescription = null, tint = TextSecondary) }
                IconButton(onClick = { }) { Icon(Icons.Default.BarChart, contentDescription = null, tint = Lavender) }
                
                // Central Add Button
                FloatingActionButton(
                    onClick = { },
                    containerColor = Lavender,
                    contentColor = Color.White,
                    shape = CircleShape,
                    modifier = Modifier.offset(y = (-10).dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = null)
                }
                
                IconButton(onClick = { }) { Icon(Icons.Default.CalendarToday, contentDescription = null, tint = TextSecondary) }
                IconButton(onClick = { }) { Icon(Icons.Default.Person, contentDescription = null, tint = TextSecondary) }
            }
        }
    )
}
