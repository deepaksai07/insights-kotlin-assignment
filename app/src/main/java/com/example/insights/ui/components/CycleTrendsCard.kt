package com.example.insights.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.insights.ui.theme.*

@Composable
fun CycleTrendsCard(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth().padding(horizontal = 24.dp, vertical = 24.dp)) {
        Text(
            text = "Cycle Trends",
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Left Arrow
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .size(24.dp)
                        .border(1.dp, Lavender, CircleShape)
                ) {
                    Icon(Icons.Default.ChevronLeft, contentDescription = null, tint = Lavender, modifier = Modifier.size(16.dp))
                }

                // Bars
                val months = listOf("Jan", "Feb", "Mar", "Apr", "May", "Jun")
                val values = listOf(28, 30, 28, 32, 28, 28)
                val heights = listOf(140.dp, 160.dp, 140.dp, 170.dp, 140.dp, 140.dp)
                
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier.weight(1f).padding(horizontal = 8.dp).height(200.dp)
                ) {
                    months.forEachIndexed { index, month ->
                        val isSelected = index == 3 // Apr
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.alpha(if (isSelected) 1f else 0.8f)
                        ) {
                            Text(
                                text = values[index].toString(),
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                color = TextPrimary,
                                modifier = Modifier.padding(bottom = 8.dp)
                            )
                            
                            Box(
                                modifier = Modifier
                                    .width(16.dp)
                                    .height(heights[index])
                                    .background(LavenderDark, RoundedCornerShape(8.dp))
                            ) {
                                // Teal segment
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(if (isSelected) 48.dp else 40.dp)
                                        .align(Alignment.Center)
                                        .offset(y = (-10).dp)
                                        .background(Teal, RoundedCornerShape(8.dp)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(Icons.Default.Settings, contentDescription = null, tint = Color.White, modifier = Modifier.size(10.dp).alpha(0.5f))
                                }
                                
                                // Pink segment
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(if (isSelected) 60.dp else 48.dp)
                                        .align(Alignment.BottomCenter)
                                        .background(Pink, RoundedCornerShape(8.dp)),
                                    contentAlignment = Alignment.BottomCenter
                                ) {
                                    Icon(Icons.Default.WaterDrop, contentDescription = null, tint = Color.White, modifier = Modifier.size(10.dp).padding(bottom = 4.dp))
                                }
                            }
                            
                            Text(
                                text = month,
                                style = MaterialTheme.typography.labelSmall,
                                color = if (isSelected) TextPrimary else TextSecondary,
                                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                                modifier = Modifier.padding(top = 12.dp)
                            )
                        }
                    }
                }

                // Right Arrow
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .size(24.dp)
                        .border(1.dp, Lavender, CircleShape)
                ) {
                    Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Lavender, modifier = Modifier.size(16.dp))
                }
            }
        }
    }
}
