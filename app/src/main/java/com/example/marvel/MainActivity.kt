package com.example.marvel


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material.MaterialTheme
//import androidx.compose.material3.MaterialTheme


import androidx.compose.runtime.internal.ComposableLambda
import androidx.compose.runtime.internal.composableLambda
import androidx.compose.runtime.internal.composableLambdaInstance
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.marvel.model.api.MarvelApi
import com.example.marvel.model.api.MarvelApiRepo
import com.example.marvel.ui.theme.MarvelTheme

import com.example.marvel.view.CharacterDetailScreen
import com.example.marvel.view.CharactersBottomNav
import com.example.marvel.view.CollectionScreen
import com.example.marvel.view.LibraryScreen
import com.example.marvel.viewmodel.LibraryApiViewModel
import dagger.Provides
import dagger.hilt.android.AndroidEntryPoint




sealed class Destination(val route: String) {
    object Library : Destination("library")
    object Collection : Destination("collection")
    object CharacterDetail : Destination("character/{characterId}") {

        fun createRoute(characterId: Int?) = "character/$characterId"
    }
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private  val lvm by viewModels<LibraryApiViewModel> ()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelTheme {
                // A surface container using the 'background' color from the them{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    //color = MaterialTheme.colorScheme.background
                color = MaterialTheme.colors.background
                )
                {
                    val navController = rememberNavController()
                    CharactersScaffold(navController = navController, lvm)
                }


            }
        }
    }


}



@Composable
fun CharactersScaffold(navController: NavHostController,lvm:LibraryApiViewModel) {

    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = { CharactersBottomNav(navController = navController) }) { PaddingValues ->
        NavHost(
            navController = navController,
            startDestination = Destination.Library.route,
            modifier = Modifier.padding(PaddingValues)
        )
        {

            /*composable(route = LibraryScreen.route) {
                LibraryScreen()
            }*/
            composable(route = Destination.Library.route) {
             LibraryScreen(navController,lvm, PaddingValues)
            }
            composable(route = Destination.Collection.route) {
                CollectionScreen()
            }
            /* composable(route = CharacterDetailScreen.route) {
                 CharacterDetailScreen()
             }*/

            composable(route = Destination.CharacterDetail.route) { navBackStackEntry ->
            }
        }

    }

}

