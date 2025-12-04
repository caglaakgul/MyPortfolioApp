package com.caglaakgul.myportfolioapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caglaakgul.myportfolioapp.presentation.ui.theme.MyPortfolioAppTheme

@Composable
fun FilterChip(
    label: String,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) {
        MaterialTheme.colorScheme.primary.copy(alpha = 0.08f)
    } else {
        Color.White
    }

    val borderColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.55f)
    val textColor = if (isSelected) {
        MaterialTheme.colorScheme.primary
    } else {
        Color(0xFF4B5563)
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(12.dp)
            )
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = textColor
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FilterChipUnselectedPreview() {
    MyPortfolioAppTheme {
        FilterChip(
            label = "Personal",
            isSelected = false,
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FilterChipSelectedPreview() {
    MyPortfolioAppTheme {
        FilterChip(
            label = "Personal",
            isSelected = true,
            onClick = {}
        )
    }
}