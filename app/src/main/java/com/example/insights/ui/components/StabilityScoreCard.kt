package com.example.insights.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.insights.ui.theme.*

@Composable
fun StabilityScoreCard(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth().padding(horizontal = 24.dp)) {
        Text(
            text = "Stability Summary",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp) // Using shadow via modifier later if needed, but 0dp is cleaner for now
        ) {
            Column(modifier = Modifier.padding(top = 24.dp)) {
                Text(
                    text = "Based on your recent logs and symptom\npatterns.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary,
                    lineHeight = 20.sp,
                    modifier = Modifier.padding(horizontal = 24.dp, bottom = 24.dp)
                )

                Text(
                    text = "Stability Score",
                    style = MaterialTheme.typography.bodyLarge,
                    color = TextPrimary,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )

                Text(
                    text = "78%",
                    style = MaterialTheme.typography.headlineLarge.copy(fontSize = 32.sp, fontWeight = FontWeight.Bold),
                    color = TextPrimary,
                    modifier = Modifier.padding(horizontal = 24.dp, bottom = 24.dp)
                )

                // Chart Area
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                ) {
                    StabilityAreaChart(modifier = Modifier.fillMaxSize())
                    
                    // Tooltip (approximate placement for "Mar")
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .offset(x = 30.dp, y = (-10).dp)
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Surface(
                                color = Color.Black,
                                shape = RoundedCornerShape(8.dp),
                            ) {
                                Text(
                                    text = "Stability\nImproving",
                                    color = Color.White,
                                    fontSize = 11.sp,
                                    lineHeight = 14.sp,
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                                )
                            }
                            // Triangle
                            Canvas(modifier = Modifier.size(10.dp).offset(y = (-4).dp)) {
                                val path = Path().apply {
                                    moveTo(0f, 0f)
                                    lineTo(size.width, 0f)
                                    lineTo(size.width / 2f, size.height)
                                    close()
                                }
                                drawPath(path, Color.Black)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StabilityAreaChart(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height
            val pl = 40.dp.toPx() // padding left
            val pr = 24.dp.toPx() // padding right
            val pb = 32.dp.toPx() // padding bottom
            val pt = 20.dp.toPx() // padding top
            
            val chartWidth = width - pl - pr
            val chartHeight = height - pb - pt

            // Draw Y axis labels
            val yLabels = listOf("24d", "28d", "32d")
            yLabels.forEachIndexed { index, label ->
                drawContext.canvas.nativeCanvas.drawText(
                    label,
                    pl - 16.dp.toPx(),
                    height - pb - (index * (chartHeight / 2)),
                    android.graphics.Paint().apply {
                        color = android.graphics.Color.parseColor("#8E8E93")
                        textSize = 30f
                        textAlign = android.graphics.Paint.Align.RIGHT
                    }
                )
            }

            // Draw Gradients
            val path1 = Path().apply {
                moveTo(pl, height - pb)
                quadraticBezierTo(pl + chartWidth * 0.3f, height - pb - chartHeight * 0.1f, pl + chartWidth, height - pb - chartHeight * 0.6f)
                lineTo(pl + chartWidth, height - pb)
                close()
            }
            drawPath(path1, Brush.verticalGradient(listOf(Color(0xFFEAE5FF), Color(0xFFF5F3FF))))

            val path2 = Path().apply {
                moveTo(pl, height - pb)
                quadraticBezierTo(pl + chartWidth * 0.3f, height - pb - chartHeight * 0.05f, pl + chartWidth, height - pb - chartHeight * 0.4f)
                lineTo(pl + chartWidth, height - pb)
                close()
            }
            drawPath(path2, Color(0xFFCABEF5).copy(alpha = 0.6f))

            // Draw X axis labels
            val xLabels = listOf("Jan", "Feb", "Mar", "Apr")
            val stepX = chartWidth / (xLabels.size - 1)
            xLabels.forEachIndexed { index, label ->
                val isMar = label == "Mar"
                val paint = android.graphics.Paint().apply {
                    color = if (isMar) android.graphics.Color.BLACK else android.graphics.Color.parseColor("#8E8E93")
                    textSize = 32f
                    isFakeBoldText = isMar
                    textAlign = android.graphics.Paint.Align.CENTER
                }
                drawContext.canvas.nativeCanvas.drawText(
                    label,
                    pl + index * stepX,
                    height - 8.dp.toPx(),
                    paint
                )
            }

            // Draw Dashed line for Mar
            val marX = pl + 2 * stepX
            val marY = height - pb - chartHeight * 0.35f
            drawLine(
                color = TextSecondary,
                start = androidx.compose.ui.geometry.Offset(marX, pt),
                end = androidx.compose.ui.geometry.Offset(marX, height - pb),
                strokeWidth = 2f,
                pathEffect = androidx.compose.ui.graphics.PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
            )

            // Draw Dot
            drawCircle(
                color = Teal,
                radius = 6.dp.toPx(),
                center = androidx.compose.ui.geometry.Offset(marX, marY)
            )
            drawCircle(
                color = Color.White,
                radius = 6.dp.toPx(),
                center = androidx.compose.ui.geometry.Offset(marX, marY),
                style = Stroke(width = 4f)
            )
        }
    }
}
