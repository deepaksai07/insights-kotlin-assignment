package com.example.insights.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.insights.ui.theme.*

@Composable
fun BodySignalsCard(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 24.dp)) {
        Text(
            text = "Body Signals",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text(
                    text = "Symptom Trends",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Compared to last cycle",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary,
                    modifier = Modifier.padding(bottom = 32.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp),
                    contentAlignment = Alignment.Center
                ) {
                    val strokeWidth = 32.dp.toPx()
                    
                    // Donut Chart
                    Canvas(modifier = Modifier.size(200.dp)) {
                        // Background gap
                        drawArc(
                            color = Color(0xFFF3F4F6),
                            startAngle = 0f,
                            sweepAngle = 360f,
                            useCenter = false,
                            style = Stroke(width = strokeWidth)
                        )
                        
                        // Bloating 31%
                        drawArc(
                            color = Lavender,
                            startAngle = -90f,
                            sweepAngle = 360f * 0.31f,
                            useCenter = false,
                            style = Stroke(width = strokeWidth)
                        )
                        
                        // Fatigue 21%
                        drawArc(
                            color = DarkPink,
                            startAngle = -90f + (360f * 0.31f),
                            sweepAngle = 360f * 0.21f,
                            useCenter = false,
                            style = Stroke(width = strokeWidth)
                        )
                        
                        // Acne 17%
                        drawArc(
                            color = Teal,
                            startAngle = -90f + (360f * 0.52f),
                            sweepAngle = 360f * 0.17f,
                            useCenter = false,
                            style = Stroke(width = strokeWidth)
                        )
                        
                        // Mood 30%
                        drawArc(
                            color = Peach,
                            startAngle = -90f + (360f * 0.69f),
                            sweepAngle = 360f * 0.30f,
                            useCenter = false,
                            style = Stroke(width = strokeWidth)
                        )
                    }

                    // Labels
                    DonutLabel("31%", "Bloating", Modifier.align(Alignment.TopEnd).offset(x = (-10).dp, y = 30.dp))
                    DonutLabel("30%", "Mood", Modifier.align(Alignment.TopStart).offset(x = 10.dp, y = 30.dp))
                    DonutLabel("21%", "Fatigue", Modifier.align(Alignment.BottomEnd).offset(x = (-40).dp, y = (-10).dp))
                    DonutLabel("17%", "Acne", Modifier.align(Alignment.BottomStart).offset(x = 10.dp, y = (-30).dp))
                }
            }
        }
    }
}

@Composable
fun DonutLabel(percent: String, label: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.size(70.dp),
        shape = CircleShape,
        color = Color.White,
        shadowElevation = 8.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = percent, fontWeight = FontWeight.Bold, fontSize = 14.sp)
            Text(text = label, fontSize = 12.sp, color = TextPrimary)
        }
    }
}

@Composable
fun LifestyleImpactCard(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 24.dp)) {
        Text(
            text = "Lifestyle Impact",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        Card(
            modifier = Modifier.fillMaxWidth().border(1.dp, Color(0xFF3CA7FF), RoundedCornerShape(24.dp)),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Correlation Strength",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Surface(
                        color = Color(0xFFF9FAFB),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "4 months", fontSize = 12.sp, color = TextSecondary)
                            Icon(Icons.Default.KeyboardArrowDown, contentDescription = null, tint = TextSecondary, modifier = Modifier.size(16.dp))
                        }
                    }
                }

                // Heatmap Rows
                HeatmapRow("Sleep", LavenderDark, listOf(1f, 0.9f, 0.8f, 0.7f, 0.6f, 0.5f, 0.4f, 0f, 0f))
                Spacer(modifier = Modifier.height(12.dp))
                HeatmapRow("Hydrate", Pink, listOf(1f, 0.9f, 0.8f, 0f, 0f, 0f, 0f, 0f, 0f))
                Spacer(modifier = Modifier.height(12.dp))
                HeatmapRow("Caffeine", Teal, listOf(1f, 0.9f, 0.8f, 0.7f, 0.6f, 0f, 0f, 0f, 0f))
                Spacer(modifier = Modifier.height(12.dp))
                HeatmapRow("Exercise", Peach, listOf(1f, 0.9f, 0.8f, 0.7f, 0f, 0f, 0f, 0f, 0f))
            }
        }
    }
}

@Composable
fun HeatmapRow(label: String, color: Color, opacities: List<Float>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            modifier = Modifier.width(60.dp)
        )
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            opacities.forEach { opacity ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(24.dp)
                        .background(
                            if (opacity > 0) color.copy(alpha = opacity) else Color(0xFFE5E7EB),
                            RoundedCornerShape(4.dp)
                        )
                )
            }
        }
    }
}
