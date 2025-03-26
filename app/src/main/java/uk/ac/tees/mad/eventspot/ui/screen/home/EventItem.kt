package uk.ac.tees.mad.eventspot.ui.screen.home

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uk.ac.tees.mad.eventspot.model.Event
import uk.ac.tees.mad.eventspot.ui.theme.EventSpotTheme

@Composable
fun EventItem(event: Event, borderColor: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .drawBehind {
                    val strokeWidth = 6.dp.toPx()
                    val cornerRadius = 12.dp.toPx()

                    val gradient = Brush.radialGradient(
                        colors = listOf(borderColor.copy(alpha = 0.4f), borderColor, borderColor.copy(alpha = 0.4f)),
                        center = Offset(size.width / 2, size.height / 2),
                        radius = size.width * 0.6f
                    )

                    drawRoundRect(
                        brush = gradient,
                        size = size,
                        cornerRadius = CornerRadius(cornerRadius, cornerRadius),
                        style = Stroke(width = strokeWidth)
                    )

                    drawRoundRect(
                        color = Color.White,
                        size = size,
                        cornerRadius = CornerRadius(cornerRadius, cornerRadius),
                        style = Stroke(width = 2.dp.toPx())
                    )
                }
                .border(1.dp, Color.Transparent, RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    event.title,
                    fontSize = 22.sp,
                    color = borderColor,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Row(modifier = Modifier.padding(8.dp)) {
                    Icon(
                        Icons.Default.LocationOn,
                        contentDescription = "location",
                        tint = Color.White
                    )
                    Text(event.location, color = Color.White)
                }
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text("Date: ${event.date}", color = Color.Gray)
                    Spacer(Modifier.weight(1f))
                    Text("Time: ${event.time}", color = Color.Gray)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun EventItemPrev() {
    val event = Event("Tech Innovation Summit", "2025-04-15", "10:00 AM", "London, UK", 51.5074, -0.1278, "A gathering of industry leaders discussing the future of AI and technology.")
    EventSpotTheme {
        EventItem(event, Color.Magenta)
    }
}