package com.example.cvssample

import android.os.Build
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavHostController
import androidx.test.filters.SdkSuppress
import com.example.cvssample.ui.imagesearch.ImageSearchScreen
import com.example.cvssample.viewmodel.FlickrImageViewModel
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test

@ExperimentalTestApi
@SdkSuppress(minSdkVersion = Build.VERSION_CODES.O)
class ImageSearchScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val mockNavController: NavHostController = mockk(relaxed = true)
    private val mockViewModel: FlickrImageViewModel = mockk(relaxed = true)

    @ExperimentalFoundationApi
    @ExperimentalComposeUiApi
    @Test
    fun MyTest() {
        composeTestRule.setContent {
            ImageSearchScreen(navController = mockNavController, mockViewModel)
        }

        composeTestRule.onNodeWithText("Enter image name to search").assertExists()
    }
}