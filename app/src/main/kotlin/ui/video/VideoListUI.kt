package com.gilpereda.videomanager.ui.video

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.sp
import com.gilpereda.videomanager.domain.Video
import com.gilpereda.videomanager.library.resources.Res
import com.gilpereda.videomanager.library.resources.default_video_cover
import org.jetbrains.compose.resources.painterResource

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
                columns = StaggeredGridCells.Adaptive(minSize = uiState.itemSize.dp * 1.2f),
                contentPadding = PaddingValues(4.dp),
                verticalItemSpacing = 4.dp,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                items(count = uiState.videos.size) {
                    VideoListItemUI(uiState.videos[it], uiState.itemSize, onClick = onVideoSelectedToggle)
                }
            }
        }
    }
}

@Composable
private fun VideoListItemUI(
    stateUI: VideoListItemUIState,
    size: Int,
    onClick: (Video) -> Unit,
) {
    Box(
        Modifier
            .size(size.dp * 1.2f)
            .background(Color.Transparent),
    ) {
        Card(
            onClick = { onClick(stateUI.video) },
            modifier =
                Modifier
                    .size(size.dp)
                    .align(Alignment.Center),
        ) {
            Image(
                painter = painterResource(Res.drawable.default_video_cover),
                modifier = Modifier.fillMaxWidth().height(size.dp * 0.5625f),
                contentDescription = null,
            )
            Text(
                text = stateUI.video.name,
                modifier =
                    Modifier
                        .padding(16.dp)
                        .weight(1f)
                        .fillMaxWidth()
                        .background(Color.Transparent),
                textAlign = TextAlign.Center,
                fontSize = size.sp * 0.08f,
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
