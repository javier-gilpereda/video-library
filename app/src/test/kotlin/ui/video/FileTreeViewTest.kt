package ui.video

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import com.gilpereda.videomanager.VideoLibraryApplicationState
import com.gilpereda.videomanager.VideoLibraryApplicationView
import com.gilpereda.videomanager.ui.sidebar.ACTOR_MANAGEMENT
import com.gilpereda.videomanager.ui.sidebar.SIDE_BAR_TAG
import com.gilpereda.videomanager.ui.video.FILE_TREE
import org.junit.jupiter.api.Test

@OptIn(ExperimentalTestApi::class)
class FileTreeViewTest {
    @Test
    fun `should show the folder tree`() =
        runComposeUiTest {
            setContent {
                val applicationState = VideoLibraryApplicationState()
                VideoLibraryApplicationView(applicationState)
            }

            onNodeWithTag(FILE_TREE).assertIsDisplayed()

            onNodeWithTag("$SIDE_BAR_TAG-$ACTOR_MANAGEMENT").performClick()

            onNodeWithTag(FILE_TREE).assertDoesNotExist()
        }
}
