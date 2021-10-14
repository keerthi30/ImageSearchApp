package com.example.cvssample.ui.imagedetail

import android.text.Html
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.cvssample.ui.components.BackNavigationIcon
import com.example.cvssample.viewmodel.FlickrImageViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ImageDetailScreen(navHostController: NavHostController, viewModel: FlickrImageViewModel) {
    val offset = remember { mutableStateOf(0f) }
    
    viewModel.selectedFlickrImage?.let { image ->
        Scaffold(topBar = {
            TopAppBar(title = {
                Text(text = image.title)
            }, navigationIcon = {
                BackNavigationIcon(navHostController = navHostController)
            })
        }) {

            Column(modifier = Modifier
                .fillMaxSize()
                .scrollable(
                    orientation = Orientation.Vertical,
                    state = rememberScrollableState { delta ->
                        offset.value = offset.value + delta
                        delta
                    }
                )) {

                Image(
                    painter = rememberImagePainter(
                        data = image.media.url,
                        builder = {
                            crossfade(true)
                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(10.dp)
                )

                Column(modifier = Modifier.weight(1f, true)) {
                    Text(text = Html.fromHtml(image.description.trim()).toString(),
                        fontSize = 16.sp, modifier = Modifier.padding(16.dp))
                    Text(text = "Author Name: ${image.author}", fontSize = 16.sp,
                        modifier = Modifier.padding(16.dp),
                        fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}