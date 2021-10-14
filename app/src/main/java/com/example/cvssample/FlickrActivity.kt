package com.example.cvssample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.cvssample.ui.theme.CvsSampleTheme
import com.example.cvssample.viewmodel.FlickrImageViewModel
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {
    @ExperimentalComposeUiApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlickrApp()
        }
    }
}

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun FlickrApp() {
    CvsSampleTheme {
        Scaffold {
            val navController = rememberNavController()
            val vm = getViewModel<FlickrImageViewModel>()
            FlickrNavigationComponent(navHostController = navController, vm)
        }
    }
}