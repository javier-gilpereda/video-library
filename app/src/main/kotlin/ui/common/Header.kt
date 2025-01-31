package com.gilpereda.videomanager.ui.common

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun H1(
    modifier: Modifier = Modifier,
    text: String,
) {
    Box {
        Text(
            text = text,
            modifier = modifier,
            fontSize = 24.sp,
        )
    }
}
