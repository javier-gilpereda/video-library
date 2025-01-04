package com.gilpereda.videomanager.ui

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Label
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.Label
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.FilledTonalIconToggleButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun SideBar() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier =
            Modifier
                .fillMaxHeight()
                .width(40.dp)
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 5.dp, vertical = 8.dp),
    ) {
        SideBarButton(Icons.Default.Folder, true)
        SideBarButton(Icons.Default.Person, false)
        SideBarButton(Icons.Default.Apartment, false)
        SideBarButton(Icons.AutoMirrored.Default.Label, false)
        SideBarButton(Icons.Default.Settings, false)
    }
}

@Composable
fun SideBarButton(
    icon: ImageVector,
    selected: Boolean,
) {
    FilledTonalIconToggleButton(
        checked = selected,
        onCheckedChange = {},
        modifier = Modifier.height(30.dp),
        shape = MaterialTheme.shapes.small,
        colors = IconButtonDefaults.filledIconToggleButtonColors().copy(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Icon(icon, "")
    }
}
