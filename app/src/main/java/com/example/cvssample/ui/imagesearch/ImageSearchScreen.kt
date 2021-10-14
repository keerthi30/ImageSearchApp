package com.example.cvssample.ui.imagesearch

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cvssample.FlickrScreens
import com.example.cvssample.R
import com.example.cvssample.ui.components.DefaultErrorViewComponent
import com.example.cvssample.ui.components.FlickrImageViewComponent
import com.example.cvssample.ui.components.FullScreenLoadingComponent
import com.example.cvssample.ui.components.ImageSearchScreen
import com.example.cvssample.viewmodel.FlickrImageViewModel
import com.example.cvssample.viewmodel.FlickrViewState
import org.koin.androidx.compose.getViewModel

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun ImageSearchScreen(navController: NavController, viewModel: FlickrImageViewModel) {
    Column {
        ImageSearchScreen(placeHolderText = stringResource(id = R.string.hint_search_image),
            onDoneClicked = { viewModel.getImages(it) })
        val imagesViewState = viewModel.flickrImagesListLiveData.observeAsState()
        when (imagesViewState.value) {
            is FlickrViewState.LoadingState -> {
                FullScreenLoadingComponent()
            }
            is FlickrViewState.FlickrImagesList -> {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(2),
                    contentPadding = PaddingValues(10.dp)
                ) {
                    items((imagesViewState.value as FlickrViewState.FlickrImagesList).listOfImages) {
                        FlickrImageViewComponent(image = it) {
                            viewModel.selectedFlickrImage = it
                            navController.navigate(FlickrScreens.ImageDetail.name)
                        }
                    }
                }
            }
            is FlickrViewState.ErrorState -> {
                val message = (imagesViewState.value as FlickrViewState.ErrorState).errorMessage
                DefaultErrorViewComponent(message)
            }
            else -> {
                // TODO Show list of previous searches
            }
        }
    }
}