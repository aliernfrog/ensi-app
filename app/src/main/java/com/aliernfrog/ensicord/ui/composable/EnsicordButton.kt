package com.aliernfrog.ensicord.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EnsicordButton(title: String, description: String? = null, painter: Painter? = null, backgroundColor: Color = MaterialTheme.colors.secondary, contentColor: Color = MaterialTheme.colors.onBackground, enabled: Boolean = true, onClick: () -> Unit) {
    Button(modifier = Modifier
        .padding(all = 8.dp)
        .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor, contentColor = contentColor),
        onClick = onClick,
        enabled = enabled,
        contentPadding = PaddingValues(all = 8.dp)
    ) {
        if (painter != null) Image(painter, title, Modifier.padding(end = 8.dp).width(40.dp))
        Column(Modifier.fillMaxWidth()) {
            Text(title, fontWeight = FontWeight.Bold)
            if (description != null) Text(description, Modifier.alpha(0.8f), fontSize = 12.sp)
        }
    }
}