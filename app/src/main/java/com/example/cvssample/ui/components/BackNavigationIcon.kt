package com.example.cvssample.ui.components

import android.widget.ImageButton
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.*
import androidx.navigation.NavHostController

@Composable
fun BackNavigationIcon(navHostController: NavHostController, icon: ImageVector = Icons.Default.ArrowBack) {
    IconButton(onClick = {
        navHostController.popBackStack()
    }, modifier = Modifier.semantics { contentDescription = "Click to navigate back"}) {
        Icon(imageVector = icon, contentDescription = "Back Arrow")
    }
}