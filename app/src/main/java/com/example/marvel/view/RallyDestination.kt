package com.example.marvel.view

import androidx.compose.runtime.Composable
interface RallyDestination{

    val route: String
    val screen: @Composable () -> Unit
}


    object LibraryScreen : RallyDestination {

        override val route = "libraryscreen"
        override val screen: @Composable () -> Unit = { LibraryScreen() }
    }

    object CollectionScreen : RallyDestination {

        override val route = "collectionscreen"
        override val screen: @Composable () -> Unit = { CollectionScreen() }
    }

    object CharacterDetailScreen : RallyDestination {

        override val route = "characterdetailscreen"
        override val screen: @Composable () -> Unit = { CharacterDetailScreen()}
    }
