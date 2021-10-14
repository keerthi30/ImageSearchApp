package com.example.cvssample.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.cvssample.R
import com.example.cvssample.ui.theme.defaultTextColor
import com.example.cvssample.ui.theme.loadingBarColor

@Composable
fun FullScreenLoadingComponent(
    loadingText:String = stringResource(id = R.string.loading)
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            CircularProgressIndicator(
                color = MaterialTheme.colors.loadingBarColor,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Text(
                text = loadingText,
                color = MaterialTheme.colors.defaultTextColor,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}

@Composable
fun LoadingContentComponent(
    empty:Boolean,
    emptyContent: @Composable () -> Unit,
    loading:Boolean,
    onRefresh: () -> Unit,
    content: @Composable () -> Unit
) {
    if(empty) {
        emptyContent()
    } else {
        // TODO add Swipe to refresh component
    }
}