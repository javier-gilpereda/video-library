package com.gilpereda.videomanager.ui.video

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gilpereda.videomanager.domain.Video

@Composable
fun VideoListUI(
    uiState: VideoListUIState,
    onVideoSelectedToggle: (Video) -> Unit,
) {
    Surface(
        Modifier
            .fillMaxSize(),
    ) {
        Column {
            VideoListTopBarUI()
            LazyVerticalStaggeredGrid(
                modifier =
                    Modifier
                        .background(MaterialTheme.colorScheme.surfaceContainer)
                        .fillMaxWidth()
                        .weight(1f),
                columns = StaggeredGridCells.Adaptive(minSize = 240.dp),
                contentPadding = PaddingValues(4.dp),
                verticalItemSpacing = 4.dp,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                items(count = uiState.videos.size) {
                    VideoListItemUI(uiState.videos[it], onClick = onVideoSelectedToggle)
                }
            }
        }
    }
}

@Composable
private fun VideoListItemUI(
    stateUI: VideoListItemUIState,
    onClick: (Video) -> Unit,
) {
    Box(
        Modifier
            .size(240.dp)
            .clickable { onClick(stateUI.video) }
            .background(Color.Transparent),
    ) {
        Card(
            modifier =
                Modifier
                    .size(200.dp)
                    .align(Alignment.Center),
        ) {
            Text(
                text = stateUI.video.name,
                modifier =
                    Modifier
                        .padding(16.dp)
                        .background(Color.Transparent),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
private fun VideoListTopBarUI() {
    Box(
        Modifier
            .fillMaxWidth()
            .height(40.dp),
    ) {
        Text("Top bar")
    }
}
