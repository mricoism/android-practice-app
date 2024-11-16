package com.mricoism.wishlistapp

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mricoism.wishlistapp.data.DummyWish
import com.mricoism.wishlistapp.data.Wish

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeView(
    navController: NavController,
    viewModel: WishViewModel,

    ) {
    val contextHV = LocalContext.current
    Scaffold(topBar = {
        AppBarView(title = "WishlistApp") {
            Log.d("HWS", "a 1")
            Toast.makeText(contextHV, "Button Clicked!", Toast.LENGTH_LONG).show();
            Log.d("HWS", "a 2")
        }
    }, floatingActionButton = {
        FloatingActionButton(
            modifier = Modifier.padding(all = 20.dp),
            contentColor = Color.White,
            backgroundColor = colorResource(id = R.color.primary),
            onClick = {
//                Toast.makeText(contextHV, "Floating button Clicked!", Toast.LENGTH_LONG).show();
                navController.navigate(Screen.DetailScreen.route + "/0L")
            }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }
    ) { paddingValues ->
        val wishlist = viewModel.getAllWishes.collectAsState(initial = listOf())
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(wishlist.value, key = { wish -> wish.id }) { wish ->
                //TODO maybe here i can add logic if data is 0 then show empty state.
                val dismissState = rememberDismissState(confirmStateChange = {
                    if (it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart) {
                        viewModel.deleteWish(wish)
                    }
                    true
                })
                SwipeToDismiss(
                    state = dismissState,
                    background = {
                        val color by animateColorAsState(
                            targetValue = if (dismissState.dismissDirection == DismissDirection.EndToStart) Color.Red else Color.Transparent,
                            label = ""
                        )
                        val alignment = Alignment.CenterEnd
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color)
                                .padding(horizontal = 20.dp), contentAlignment = alignment
                        ) {
                            Icon(
                                Icons.Default.Delete,
                                contentDescription = "Delete Icon",
                                tint = Color.White
                            )
                        }
                    },
                    directions = setOf(DismissDirection.StartToEnd, DismissDirection.EndToStart),
                    dismissThresholds = { FractionalThreshold(0.25F) },
                    dismissContent = {
                        WishItem(wish = wish) {
//                    Toast.makeText(contextHV, wish.title, Toast.LENGTH_LONG).show();
                            val id = wish.id
                            navController.navigate(Screen.DetailScreen.route + "/$id")
                        }
                    },
                )
            }

        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WishItem(wish: Wish, onClick: () -> Unit) {
//    Card(
////        modifier = Modifier.fillMaxWidth().padding(top = 8.dp, start = 8.dp, end = 8.dp).clickable {  },
////        elevation = 10.dp,
//        back
//        modifier = Modifier.fillMaxWidth().padding(top = 8.dp, start = 8.dp, end = 8.dp),
//        onClick = { onClick() }
//    ) {
//
//    }
    androidx.compose.material.Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 8.dp, end = 8.dp),
        elevation = 10.dp,
        backgroundColor = Color.White,
        onClick = { onClick() },
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = wish.title, fontWeight = FontWeight.ExtraBold)
            Text(text = wish.description)
        }
    }
}