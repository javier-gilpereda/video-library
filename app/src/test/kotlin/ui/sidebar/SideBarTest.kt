package ui.sidebar

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.runComposeUiTest
import com.gilpereda.videomanager.VideoLibraryApplicationState
import com.gilpereda.videomanager.VideoLibraryApplicationView
import com.gilpereda.videomanager.ui.sidebar.ACTOR_MANAGEMENT
import com.gilpereda.videomanager.ui.sidebar.PRODUCER_MANAGEMENT
import com.gilpereda.videomanager.ui.sidebar.SETTINGS
import com.gilpereda.videomanager.ui.sidebar.SIDE_BAR_TAG
import com.gilpereda.videomanager.ui.sidebar.TAG_MANAGEMENT
import com.gilpereda.videomanager.ui.sidebar.VIDEO_MANAGEMENT
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@OptIn(ExperimentalTestApi::class)
class SideBarTest {
    @ParameterizedTest
    @ValueSource(
        strings = [VIDEO_MANAGEMENT, ACTOR_MANAGEMENT, PRODUCER_MANAGEMENT, TAG_MANAGEMENT, SETTINGS],
    )
    fun `should change between the tabs`(tab: String) =
        runComposeUiTest {
            setContent {
                val applicationState = VideoLibraryApplicationState()
                VideoLibraryApplicationView(applicationState)
            }

            onNodeWithTag("$SIDE_BAR_TAG-$tab").performClick()

            onNodeWithTag(tab).assertIsDisplayed()
        }
}
