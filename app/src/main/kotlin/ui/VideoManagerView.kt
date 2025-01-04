package com.gilpereda.videomanager.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun VideoManagerView() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
    ) {
        Row(
            modifier =
                Modifier
                    .weight(1f),
        ) {
            Surface(
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
            ) { SideBar() }
            Surface(
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                modifier = Modifier.fillMaxHeight().weight(1f),
            ) { ContentArea() }
        }
        StatusBar()
    }
}
