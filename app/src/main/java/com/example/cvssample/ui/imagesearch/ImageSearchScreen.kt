package com.example.cvssample.ui.imagesearch

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cvssample.FlickrScreens
import com.example.cvssample.R
import com.example.cvssample.ui.components.DefaultErrorViewComponent
import com.example.cvssample.ui.components.FlickrImageViewComponent
import com.example.cvssample.ui.components.FullScreenLoadingComponent
import com.example.cvssample.ui.components.ImageSearchComponent
import com.example.cvssample.ui.theme.defaultTextColor
import com.example.cvssample.viewmodel.FlickrImageViewModel
import com.example.cvssample.viewmodel.FlickrViewState

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun ImageSearchScreen(navController: NavController, viewModel: FlickrImageViewModel) {
    Column(modifier = Modifier.padding(16.dp)) {
        ImageSearchComponent(placeHolderText = stringResource(id = R.string.hint_search_image),
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
                val searchTagViewState = viewModel.searchTagLiveData.observeAsState()
                if(searchTagViewState.value.isNullOrEmpty()) {
                    Text(text = stringResource(id = R.string.message_search_image),
                        modifier = Modifier.fillMaxSize(),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        color = MaterialTheme.colors.defaultTextColor)
                } else {
                    Text(text = stringResource(id = R.string.recent_search),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colors.defaultTextColor)
                    LazyColumn {
                        items(searchTagViewState.value ?: emptyList()) {
                            Text(text = it.tag,
                                fontSize = 14.sp,
                                color = MaterialTheme.colors.defaultTextColor)
                        }
                    }
                }
            }
        }
    }
}