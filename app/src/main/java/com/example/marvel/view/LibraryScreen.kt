package com.example.marvel.view
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.marvel.AttributionText
import com.example.marvel.CharacterImage
import com.example.marvel.Destination
import com.example.marvel.model.CharactersApiResponse
import com.example.marvel.model.api.NetworkResult
import com.example.marvel.viewmodel.LibraryApiViewModel



@Composable
fun LibraryScreen(
   navController: NavHostController,
   vm: LibraryApiViewModel,
   paddingValues: PaddingValues
) {
   
   val result by vm.result.collectAsState()
   val text = vm.queryText.collectAsState()

   Column(
      modifier = Modifier
         .fillMaxSize()
         .padding(bottom = paddingValues.calculateBottomPadding()),
      horizontalAlignment = Alignment.CenterHorizontally
   ) {

      OutlinedTextField(
         value = text.value,
         onValueChange = vm::onQueryUpdate,
         label = { Text(text = "Character search") },
         placeholder = { Text(text = "Character") },
         keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
      )

      Column(
         modifier = Modifier.fillMaxSize(),
         horizontalAlignment = Alignment.CenterHorizontally,
         verticalArrangement = Arrangement.Center
      ) {
         when (result) {
            is NetworkResult.Initial -> {
               Text(text = "Search for a character")
            }

            is NetworkResult.Success -> {
               ShowCharactersList(result, navController)
            }

            is NetworkResult.Loading -> {
               CircularProgressIndicator()
            }

            is NetworkResult.Error -> {
               Text(text = "Error: ${result.message}")
            }
         }
      }

   }
}

@Composable
fun ShowCharactersList(
   result: NetworkResult<CharactersApiResponse>,
   navController: NavHostController
) {
   result.data?.data?.results?.let { characters ->
      LazyColumn(
         modifier = Modifier.background(Color.LightGray),
         verticalArrangement = Arrangement.Top
      ) {
         result.data.attributionText?.let {
            item {
               AttributionText(text = it)
            }
         }

         items(characters) { character ->
            val imageUrl = character.thumbnail?.path + "." + character.thumbnail?.extension
            val title = character.name
            val description = character.description
            val context = LocalContext.current
            val id = character.id

            Column(
               modifier = Modifier
                  .padding(4.dp)
                  .clip(RoundedCornerShape(5.dp))
                  .background(Color.White)
                  .padding(4.dp)
                  .fillMaxWidth()
                  .wrapContentHeight()
                  .clickable {
                     if (character.id != null)
                        navController.navigate(Destination.CharacterDetail.createRoute(id))
                     else
                        Toast
                           .makeText(context, "Character id is null", Toast.LENGTH_SHORT)
                           .show()
                  }
            ) {
               Row(modifier = Modifier.fillMaxWidth()) {
                  CharacterImage(
                     url = imageUrl,
                     modifier = Modifier
                        .padding(4.dp)
                        .width(100.dp)
                  )

                  Column(modifier = Modifier.padding(4.dp)) {
                     Text(text = title ?: "", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                  }
               }

               Text(text = description ?: "", maxLines = 4, fontSize = 14.sp)
            }
         }
      }
   }
}

/*
@Composable
fun CharacterImage(url: String, modifier: Modifier) {

}
*/


