package com.example.cvssample.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.cvssample.data.models.FlickrImage
import com.example.cvssample.ui.theme.defaultTextColor

@Composable
fun FlickrImageDetailsComponent(flickrImage: FlickrImage) {
    val offset = remember { mutableStateOf(0f) }

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
                data = flickrImage.media.url,
                builder = {
                    crossfade(true)
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(10.dp)
        )

        Text(
            text = flickrImage.description,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            color = MaterialTheme.colors.defaultTextColor
        )

        Text(
            text = "Author Name: ${flickrImage.author}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            color = MaterialTheme.colors.defaultTextColor
        )
    }
}