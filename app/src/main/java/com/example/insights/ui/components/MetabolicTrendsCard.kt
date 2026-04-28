package com.example.insights.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.insights.ui.theme.*

@Composable
fun MetabolicTrendsCard(modifier: Modifier = Modifier) {
    var selectedOption by remember { mutableStateOf("Monthly") }

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
                    text = "Body & Metabolic Trends",
                    style = MaterialTheme.typography.titleLarge
                )
                
                // Toggle
                Surface(
                    color = AppBackground,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(modifier = Modifier.padding(2.dp)) {
                        listOf("Monthly", "Weekly").forEach { option ->
                            Button(
                                onClick = { selectedOption = option },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (selectedOption == option) Color.White else Color.Transparent,
                                    contentColor = if (selectedOption == option) TextPrimary else TextSecondary
                                ),
                                shape = RoundedCornerShape(10.dp),
                                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                                modifier = Modifier.height(28.dp),
                                elevation = if (selectedOption == option) ButtonDefaults.buttonElevation(defaultElevation = 1.dp) else null
                            ) {
                                Text(text = option, style = MaterialTheme.typography.labelSmall)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Line Chart
            MetabolicLineChart(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Labels
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun").forEach { month ->
                    Text(text = month, style = MaterialTheme.typography.labelSmall)
                }
            }
        }
    }
}

@Composable
fun MetabolicLineChart(modifier: Modifier = Modifier) {
    val dataPoints = listOf(0.3f, 0.5f, 0.45f, 0.7f, 0.6f, 0.8f)
    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height
        val stepX = width / (dataPoints.size - 1)

        val path = Path().apply {
            dataPoints.forEachIndexed { index, point ->
                val x = index * stepX
                val y = height - (point * height)
                if (index == 0) moveTo(x, y) else lineTo(x, y)
            }
        }

        drawPath(
            path = path,
            color = Lavender,
            style = Stroke(width = 3.dp.toPx())
        )
        
        // Draw points
        dataPoints.forEachIndexed { index, point ->
            val x = index * stepX
            val y = height - (point * height)
            drawCircle(
                color = Lavender,
                radius = 4.dp.toPx(),
                center = androidx.compose.ui.geometry.Offset(x, y)
            )
            drawCircle(
                color = Color.White,
                radius = 2.dp.toPx(),
                center = androidx.compose.ui.geometry.Offset(x, y)
            )
        }
    }
}
