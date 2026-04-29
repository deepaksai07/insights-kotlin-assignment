package com.example.insights.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            contentPadding = PaddingValues(bottom = 100.dp) // Space for floating bottom bar
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
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            Row(modifier = Modifier.padding(start = 24.dp).size(24.dp)) {
                // 4 dots icon
                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                        Box(modifier = Modifier.size(10.dp).background(Lavender, CircleShape))
                        Box(modifier = Modifier.size(10.dp).background(Color(0xFFE5E0FF), CircleShape))
                    }
                    Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                        Box(modifier = Modifier.size(10.dp).background(Color(0xFFE5E0FF), CircleShape))
                        Box(modifier = Modifier.size(10.dp).background(Lavender, CircleShape))
                    }
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.Transparent
        )
    )
}

@Composable
fun InsightsBottomBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 24.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Pill Navigation
            Surface(
                modifier = Modifier
                    .weight(1f)
                    .height(70.dp),
                shape = RoundedCornerShape(35.dp),
                color = Color.White.copy(alpha = 0.9f),
                shadowElevation = 16.dp
            ) {
                Row(
                    modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    NavItem("Home", Icons.Default.Home, false)
                    NavItem("Track", Icons.Default.AccessTime, false)
                    NavItem("Insights", Icons.Default.BarChart, true)
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // FAB
            Surface(
                modifier = Modifier.size(70.dp),
                shape = CircleShape,
                color = Color.White,
                shadowElevation = 16.dp
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(Icons.Default.Add, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(32.dp))
                }
            }
        }
    }
}

@Composable
fun NavItem(label: String, icon: androidx.compose.ui.graphics.vector.ImageVector, isSelected: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (isSelected) TextPrimary else Color(0xFFC7C7CC),
            modifier = Modifier.size(28.dp)
        )
        Text(
            text = label,
            fontSize = 10.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) TextPrimary else Color(0xFFC7C7CC)
        )
    }
}
