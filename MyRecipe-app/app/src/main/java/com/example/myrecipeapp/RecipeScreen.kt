package com.example.myrecipeapp

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.Image
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import coil3.imageLoader
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.toUri
import coil3.util.DebugLogger


@Composable
fun RecipeScreen(modifier: Modifier = Modifier, viewState: MainViewModel.RecipeState, navigateToDetail: (Category) -> Unit) {
    val recipeViewModel: MainViewModel = viewModel()
//    val viewState by recipeViewModel.categoriesState

    Box(modifier = modifier.fillMaxSize()) {
        when {
            viewState.loading -> {
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }

            viewState.error != null -> {
                Text(text = "ERROR OCCURED")
            }

            else -> {
                // Display categories
                CategoryScreen(categories = viewState.list, navigateToDetail)
            }
        }
    }
}

@Composable
fun CategoryScreen(categories: List<Category>, navigateToDetail: (Category) -> Unit) {
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize().padding(50.dp)) {
        items(categories) {
            category ->
            CategoryItem(category = category, navigateToDetail)
        }
    }
}

@Composable
fun CategoryItem(category: Category, navigationToDetail: (Category) -> Unit) {

    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize().clickable {
                navigationToDetail(category)
            }, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //https://coil-kt.github.io/coil/compose/
        Log.d("hws a", "${category.strCategoryThumb}")
        Log.d("hws b", "${category.strCategory}")
        val imageLoader = LocalContext.current.imageLoader.newBuilder()
            .logger(DebugLogger())
            .build()

//        AsyncImage(
//            //...
//            imageLoader = imageLoader,
//        )

//        Image(
//            painter = rememberAsyncImagePainter("https://www.themealdb.com/images/category/beef.png"),
//            contentDescription = null,
//            modifier = Modifier
//                .fillMaxSize()
//                .aspectRatio(1f)
//        )

//        val sizeResolver = remember()
//        val painter = rememberAsyncImagePainter(
//            model = ImageRequest.Builder(LocalPlatformContext.current)
//                .data(category.strCategoryThumb)
//                .build(),
//        )
//
//        Image(
//            painter = painter,
//            contentDescription = null,
//            modifier = Modifier.then(sizeResolver),
//        )

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(category.strCategoryThumb)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_launcher_background),
            contentDescription = stringResource(R.string.app_name),
            contentScale = ContentScale.Crop,
            modifier = Modifier.clip(CircleShape),
            imageLoader = imageLoader
        )
        Log.d("hws c", "just check")
        Text(
            text = category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )

    }
}