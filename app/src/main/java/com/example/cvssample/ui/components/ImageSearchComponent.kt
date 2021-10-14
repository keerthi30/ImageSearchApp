package com.example.cvssample.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.cvssample.R
import com.example.cvssample.ui.theme.defaultTextColor

@ExperimentalComposeUiApi
@Composable
fun ImageSearchScreen(placeHolderText:String,
                      leadingIcon: @Composable () -> Unit = { SearchImageComponent() },
                      onDoneClicked: (String) -> Unit
) {
    val textState = remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
        value = textState.value ,
        onValueChange = { textState.value = it },
        shape = RoundedCornerShape(10.dp),
        singleLine = true,
        placeholder = {
            Text(text = placeHolderText, color = MaterialTheme.colors.defaultTextColor)
        },
        keyboardActions = KeyboardActions(onDone = {
            onDoneClicked(textState.value)
            keyboardController?.hide()
        }),
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        leadingIcon = leadingIcon,
        trailingIcon = {
            IconButton(onClick = {
                textState.value = ""
                onDoneClicked("")
            }) {
                Icon(
                    Icons.Default.Close, stringResource(id = R.string.close), tint = MaterialTheme.colors.primary
                )
            }
        },
    )
}

@Composable
fun SearchImageComponent() {
    Icon(
        Icons.Default.Search, null, tint = MaterialTheme.colors.primary
    )
}