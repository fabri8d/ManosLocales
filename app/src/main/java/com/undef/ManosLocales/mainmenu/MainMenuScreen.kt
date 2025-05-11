package com.undef.ManosLocales.mainmenu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.res.painterResource
import com.undef.ManosLocales.R
import com.undef.ManosLocales.utils.ObjectsProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Posts(onNavigateToSeller: (String) -> Unit,
          onNavigateToProduct: (String) -> Unit) {
    val productsList = remember { ObjectsProvider.productsList }
    val sellersList = remember { ObjectsProvider.sellerLists }
    var selectedView by remember { mutableStateOf("Productos") }
    var selectedCategory by remember { mutableStateOf("Todos") }
    var selectedCity by remember { mutableStateOf("Todos") }
    var expandedView by remember { mutableStateOf(false) }
    var expandedCategory by remember { mutableStateOf(false) }
    var expandedCity by remember { mutableStateOf(false) }
    val categories = remember { ObjectsProvider.categories }

    val filteredProducts = if (selectedCategory == "Todos") {
        productsList
    } else {
        productsList.filter { it.category == selectedCategory }
    }
    val filteredSellers = if (selectedCity == "Todos") {
        sellersList
    } else {
        sellersList.filter { it.user.city == selectedCity }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Manos Locales",
                            color = Color.White
                        )
                        Image(
                            painter = painterResource(id = R.drawable.logotipo_transparente),
                            contentDescription = "Logotipo",
                            modifier = Modifier.size(48.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF404934)
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = Color(0xFFe3d6c3),
                content = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        IconButton(onClick = { /* Acción 1 */ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.favorite),
                                contentDescription = "Favoritos"
                            )
                        }
                        IconButton(onClick = { /* Acción 2 */ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.home),
                                contentDescription = "Inicio",
                                modifier = Modifier.size(25.dp)
                            )
                        }
                        IconButton(onClick = { /* Acción 3 */ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.submenu),
                                contentDescription = "Submenu"
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF7C5C44)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedView,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
                    color = Color(0xFFe3d6c3),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Box {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            "Ver: $selectedView",
                            color = Color(0xFFe3d6c3),
                            modifier = Modifier
                                .clickable { expandedView = !expandedView }
                                .padding(16.dp)
                        )

                        DropdownMenu(
                            expanded = expandedView,
                            onDismissRequest = { expandedView = false },
                            modifier = Modifier.padding(10.dp)
                        ) {
                            listOf("Productos", "Vendedores").forEach { view ->
                                DropdownMenuItem(
                                    text = { Text(view) },
                                    onClick = {
                                        selectedView = view
                                        expandedView = false
                                    }
                                )
                            }
                        }
                        if (selectedView == "Productos") {
                            Text(
                                "Categoría: $selectedCategory",
                                color = Color(0xFFe3d6c3),
                                modifier = Modifier
                                    .padding(16.dp)
                                    .clickable { expandedCategory = !expandedCategory }
                            )
                            Row(horizontalArrangement = Arrangement.End,
                                modifier = Modifier.padding(top = 56.dp)){
                                DropdownMenu(
                                    expanded = expandedCategory,
                                    onDismissRequest = { expandedCategory = false },
                                    modifier = Modifier.padding(10.dp)
                                ) {
                                    categories.forEach { category ->
                                        DropdownMenuItem(
                                            text = { Text(category) },
                                            onClick = {
                                                selectedCategory = category
                                                expandedCategory = false
                                            }
                                        )
                                    }
                                }
                            }

                        }else if (selectedView == "Vendedores"){
                            Text(
                                "Ciudad: $selectedCity",
                                color = Color(0xFFe3d6c3),
                                modifier = Modifier
                                    .padding(16.dp)
                                    .clickable { expandedCity = !expandedCity }
                            )
                            Row(horizontalArrangement = Arrangement.End,
                                modifier = Modifier.padding(top = 56.dp)){
                                DropdownMenu(
                                    expanded = expandedCity,
                                    onDismissRequest = { expandedCity = false },
                                    modifier = Modifier.padding(10.dp)
                                ) {
                                    ObjectsProvider.cities.forEach { city ->
                                        DropdownMenuItem(
                                            text = { Text(city) },
                                            onClick = {
                                                selectedCity = city
                                                expandedCity = false
                                            }
                                        )
                                    }
                                }
                            }


                        }

                    }
                }
            }

            Row(
                modifier = Modifier
                    .background(Color(0xFFe3d6c3))
                    .fillMaxHeight()
            ) {
                LazyColumn {
                    if (selectedView == "Productos") {
                        items(filteredProducts.chunked(2)) { rowItems ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                rowItems.forEach { product ->
                                    Box(modifier = Modifier.weight(1f)
                                        .clickable{
                                            onNavigateToProduct(product.id.toString())
                                        }) {
                                        ProductItem(product = product)
                                    }
                                }
                                if (rowItems.size == 1) {
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                        }
                    } else if (selectedView == "Vendedores") {
                        items(filteredSellers.chunked(2)) { rowUsers ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                rowUsers.forEach { seller ->
                                    Box(modifier = Modifier.weight(1f)
                                        .clickable{
                                            onNavigateToSeller(seller.user.id.toString())
                                        }) {
                                        SellerItem(seller = seller)
                                    }
                                }
                                if (rowUsers.size == 1) {
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}



