package com.example.cvssample

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cvssample.ui.imagedetail.ImageDetailScreen
import com.example.cvssample.ui.imagesearch.ImageSearchScreen
import com.example.cvssample.viewmodel.FlickrImageViewModel


enum class FlickrScreens {
    ImageSearch,
    ImageDetail
}

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@Composable
fun FlickrNavigationComponent(navHostController: NavHostController, viewModel: FlickrImageViewModel) {
    NavHost(navController = navHostController, startDestination = FlickrScreens.ImageSearch.name) {
        composable(FlickrScreens.ImageSearch.name) {
            ImageSearchScreen(navController = navHostController, viewModel = viewModel)
        }
        composable(FlickrScreens.ImageDetail.name) {
            ImageDetailScreen(navHostController = navHostController, viewModel = viewModel)
        }
    }
}