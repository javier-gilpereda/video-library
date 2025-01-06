package com.gilpereda.videomanager.ui.sidebar

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun SideBar(activeTab: MutableState<MainAreaContent>) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier =
            Modifier
                .fillMaxHeight()
                .width(40.dp)
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 5.dp, vertical = 8.dp),
    ) {
        MainAreaContent.areas.forEach { area ->
            SideBarButton(activeTab, area)
        }
    }
}

@Composable
fun SideBarButton(
    activeTab: MutableState<MainAreaContent>,
    tab: MainAreaContent,
) {
    val selected = activeTab.value == tab
    val colors =
        if (selected) {
            IconButtonDefaults.filledTonalIconButtonColors()
        } else {
            IconButtonDefaults.filledTonalIconButtonColors().copy(containerColor = MaterialTheme.colorScheme.surface)
        }
    FilledTonalIconButton(
        onClick = {
            activeTab.value = tab
        },
        modifier = Modifier.height(30.dp),
        shape = MaterialTheme.shapes.small,
        colors = colors,
    ) {
        Icon(tab.icon, "")
    }
}
