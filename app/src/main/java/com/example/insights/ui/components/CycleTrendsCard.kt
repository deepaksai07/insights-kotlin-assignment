package com.example.insights.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.insights.ui.theme.*

@Composable
fun CycleTrendsCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Cycle Trends",
                    style = MaterialTheme.typography.titleLarge
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { }, modifier = Modifier.size(24.dp)) {
                        Icon(Icons.Default.ChevronLeft, contentDescription = null, tint = TextSecondary)
                    }
                    Text(
                        text = "Jan - Jun",
                        style = MaterialTheme.typography.labelSmall,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                    IconButton(onClick = { }, modifier = Modifier.size(24.dp)) {
                        Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextSecondary)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                val months = listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun")
                val heights = listOf(0.7f, 0.8f, 0.75f, 0.9f, 0.7f, 0.85f)
                
                months.forEachIndexed { index, month ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.weight(1f)
                    ) {
                        SegmentedBar(
                            modifier = Modifier
                                .width(32.dp)
                                .fillMaxHeight(heights[index]),
                            showIcons = index == 3 // Show icons on 'Apr' for demo
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = month, style = MaterialTheme.typography.labelSmall)
                    }
                }
            }
        }
    }
}

@Composable
fun SegmentedBar(modifier: Modifier = Modifier, showIcons: Boolean = false) {
    Column(
        modifier = modifier
            .background(Lavender.copy(alpha = 0.2f), RoundedCornerShape(16.dp))
            .padding(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        // Top segment (Lavender/Placeholder)
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Lavender.copy(alpha = 0.4f), RoundedCornerShape(12.dp))
        )
        // Mid segment (Teal)
        Box(
            modifier = Modifier
                .weight(0.6f)
                .fillMaxWidth()
                .background(Teal, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            if (showIcons) {
                // Placeholder for icon (e.g. Gear)
                Box(modifier = Modifier.size(12.dp).background(Color.White.copy(alpha = 0.3f), CircleShape))
            }
        }
        // Bottom segment (Pink)
        Box(
            modifier = Modifier
                .weight(1.2f)
                .fillMaxWidth()
                .background(Pink, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            if (showIcons) {
                // Placeholder for icon (e.g. Drop)
                Box(modifier = Modifier.size(12.dp).background(Color.White.copy(alpha = 0.3f), CircleShape))
            }
        }
    }
}
