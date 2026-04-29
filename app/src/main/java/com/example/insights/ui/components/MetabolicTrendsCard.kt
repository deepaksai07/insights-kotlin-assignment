package com.example.insights.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.insights.ui.theme.*

@Composable
fun MetabolicTrendsCard(modifier: Modifier = Modifier) {
    var selectedOption by remember { mutableStateOf("Monthly") }

    Column(modifier = modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 24.dp)) {
        Text(
            text = "Body & Metabolic Trends",
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column {
                        Text(
                            text = "Your weight",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "in kg",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextSecondary
                        )
                    }
                    
                    // Toggle
                    Surface(
                        color = AppBackground,
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(modifier = Modifier.padding(4.dp)) {
                            listOf("Monthly", "Weekly").forEach { option ->
                                val isSelected = selectedOption == option
                                Button(
                                    onClick = { selectedOption = option },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = if (isSelected) Color.Black else Color.Transparent,
                                        contentColor = if (isSelected) Color.White else TextSecondary
                                    ),
                                    shape = RoundedCornerShape(10.dp),
                                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
                                    modifier = Modifier.height(32.dp),
                                    elevation = null
                                ) {
                                    Text(text = option, fontSize = 12.sp, fontWeight = FontWeight.Medium)
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Line Chart Area
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                ) {
                    MetabolicLineChart(modifier = Modifier.fillMaxSize())
                    
                    // Y Axis Labels
                    Column(
                        modifier = Modifier.fillMaxHeight().padding(bottom = 24.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("75", fontSize = 11.sp, color = TextSecondary)
                        Text("50", fontSize = 11.sp, color = TextSecondary)
                        Text("25", fontSize = 11.sp, color = TextSecondary)
                    }
                    
                    // Grid lines
                    Column(
                        modifier = Modifier.fillMaxHeight().padding(start = 24.dp, bottom = 24.dp, top = 6.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Divider(color = Color(0xFFE5E7EB), thickness = 1.dp, modifier = Modifier.fillMaxWidth().padding(top = 4.dp))
                        Divider(color = Color(0xFFE5E7EB), thickness = 1.dp, modifier = Modifier.fillMaxWidth())
                        Divider(color = Color(0xFFE5E7EB), thickness = 1.dp, modifier = Modifier.fillMaxWidth())
                    }
                    
                    // X Axis Labels
                    Row(
                        modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter).padding(start = 24.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        listOf("Jan", "Feb", "Mar", "Apr", "May").forEach { month ->
                            Text(text = month, fontSize = 11.sp, color = TextSecondary)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MetabolicLineChart(modifier: Modifier = Modifier) {
    Box(modifier = modifier.padding(start = 24.dp, bottom = 24.dp)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height
            val points = listOf(
                androidx.compose.ui.geometry.Offset(width * 0.1f, height * 0.8f),
                androidx.compose.ui.geometry.Offset(width * 0.3f, height * 0.6f),
                androidx.compose.ui.geometry.Offset(width * 0.5f, height * 0.7f),
                androidx.compose.ui.geometry.Offset(width * 0.7f, height * 0.1f),
                androidx.compose.ui.geometry.Offset(width * 0.9f, height * 0.4f)
            )

            val path = Path().apply {
                moveTo(points.first().x, points.first().y)
                for (i in 0 until points.size - 1) {
                    val p1 = points[i]
                    val p2 = points[i + 1]
                    val cx = (p1.x + p2.x) / 2
                    cubicTo(cx, p1.y, cx, p2.y, p2.x, p2.y)
                }
            }
            
            // Draw gradient fill
            val fillPath = Path().addPath(path).apply {
                lineTo(points.last().x, height)
                lineTo(points.first().x, height)
                close()
            }
            
            drawPath(
                path = fillPath,
                brush = Brush.verticalGradient(
                    colors = listOf(Pink.copy(alpha = 0.4f), Pink.copy(alpha = 0.0f))
                )
            )

            // Draw line
            drawPath(
                path = path,
                color = Pink,
                style = Stroke(width = 2.dp.toPx())
            )
            
            // Draw points
            points.forEach { point ->
                drawCircle(
                    color = Pink,
                    radius = 4.dp.toPx(),
                    center = point
                )
                drawCircle(
                    color = Color.White,
                    radius = 4.dp.toPx(),
                    center = point,
                    style = Stroke(width = 2.dp.toPx())
                )
            }
        }
    }
}
