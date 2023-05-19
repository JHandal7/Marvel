package com.example.marvel

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.ComposableLambda
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.runtime.internal.composableLambdaInstance
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.marvel.ui.theme.MarvelTheme

import com.example.marvel.view.CharacterDetailScreen
import com.example.marvel.view.CollectionScreen
import com.example.marvel.view.LibraryScreen


sealed class Destination(val route: String) {
    object Library : Destination("Library")
    object Collection : Destination("destination")
    object CharacterDetail : Destination("character/characterId")

    fun createRoute(characterId: Int?) = "character/$characterId"

}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    CharactersScaffold(navController = navController)
                }
            }
        }
    }
}


@Composable
fun CharactersScaffold(navController: NavHostController) {
    val scaffoldState = rememberScaffoldState()
    androidx.compose.material.Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {}) { PaddingValues ->
        // modifier = Modifier.padding(innerPadding)
        NavHost(
            navController = navController,
            startDestination = Destination.Library.route,
            modifier = Modifier.padding(PaddingValues)
        )
        {

            composable(route=LibraryScreen.route){
                LibraryScreen()
            }

            composable(route = CollectionScreen.route) {
                CollectionScreen()
            }
            composable(route = CharacterDetailScreen.route) {
                CharacterDetailScreen()
            }
           composable(route=Destination.Library.route) {
               LibraryScreen()
           }
composable(route=Destination.CharacterDetail.route){
    navBackStackEntry ->  
}
        }

    }

}

