package com.mammates.mammates_buyer_v1.presentation.pages.main.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mammates.mammates_buyer_v1.domain.model.FoodItem
import com.mammates.mammates_buyer_v1.presentation.component.loading.LoadingScreen
import com.mammates.mammates_buyer_v1.presentation.component.text_field.SearchTextField
import com.mammates.mammates_buyer_v1.presentation.pages.main.search.component.CardSearchFood
import com.mammates.mammates_buyer_v1.presentation.pages.main.search.component.SearchNotFound
import com.mammates.mammates_buyer_v1.presentation.util.navigation.NavigationRoutes
import com.mammates.mammates_buyer_v1.util.Rating

@Composable
fun SearchScreen(
    navController: NavController,
    state: SearchState,
    onEvent: (SearchEvent) -> Unit,
    foods: List<FoodItem>,
    keywords: String,
) {

    val focusRequester = remember {
        FocusRequester()
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

//

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 35.dp)
    ) {
        SearchTextField(
            modifier = Modifier
                .focusRequester(focusRequester),
            value = keywords,
            placeholder = "Find the food you want",
            onValueChange = {
                onEvent(SearchEvent.OnSearchItem(it))
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        if (state.isLoading) {
            LoadingScreen(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )
        } else {
            if (foods.isEmpty()) {
                SearchNotFound(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                )
            } else {
                LazyColumn {
                    items(foods) {
                        CardSearchFood(
                            rating = Rating.TWO,
                            foodName = "Donut Keju Suka Terbang",
                            price = 5000,
                            image = "",
                            isValid = true,
                            onClickCard = {
                                navController.navigate(NavigationRoutes.Main.Store.route)
                            },
                            storeName = "Toko Pak Tude"
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen(
        navController = rememberNavController(),
        state = SearchState(),
        onEvent = {},
        foods = listOf(),
        keywords = ""
    )
}