package com.example.cvssample.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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

@Composable
fun DefaultErrorViewComponent(errorMessage: String = stringResource(id = R.string.default_error_message)) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if(errorMessage.isNotBlank()) errorMessage else stringResource(id = R.string.default_error_message),
            color = MaterialTheme.colors.defaultTextColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(10.dp)
        )
    }
}