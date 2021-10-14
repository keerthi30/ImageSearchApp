package com.example.cvssample.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.cvssample.data.models.FlickrImage
import com.example.cvssample.ui.theme.defaultTextColor

@Composable
fun FlickrImageViewComponent(image: FlickrImage,
                             onImageClicked: () -> Unit) {
    Card(
        elevation = 2.dp,
        shape = RectangleShape,
        modifier = Modifier.clickable {
            onImageClicked()
        }
    ) {
        Column (modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = rememberImagePainter(
                    data = image.media.url,
                    builder = {
                        crossfade(true)
                    }
                ),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(150.dp).padding(10.dp)
            )
            Text(text = image.title, fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.defaultTextColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}