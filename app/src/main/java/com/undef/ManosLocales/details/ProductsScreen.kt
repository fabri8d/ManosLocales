package com.undef.ManosLocales.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.undef.ManosLocales.R
import com.undef.ManosLocales.components.ProductItem
import com.undef.ManosLocales.utils.ObjectsProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(productId: String?,
                        onNavigateToSeller: (String) -> Unit,
                        onNavigateToProduct: (String) -> Unit,
                        onNavigateToMainMenu: () -> Unit,
                        onNavigateToFavorites: () -> Unit,
                        onNavigateToSettings: () -> Unit) {
    val product = remember(productId) {
        ObjectsProvider.productsList.find { it.id.toString() == productId }
    }
    val sameCategoryProducts = remember(product) {
        ObjectsProvider.productsList.filter { it.category == product?.category }
    }
    val sellerProducts = remember(product?.owner) {
        ObjectsProvider.productsList.filter { it.owner == product?.owner }
    }
    val scrollState = rememberScrollState()

    var imagenActual by remember { mutableStateOf(true) }

    val imagen = if (imagenActual) {
        painterResource(id = R.drawable.favorite)
    } else {
        painterResource(id = R.drawable.favorite_selected)
    }

    Image(
        painter = imagen,
        contentDescription = null,
        modifier = Modifier
            .clickable {
                imagenActual = !imagenActual
            }
    )

    Image(
        painter = imagen,
        contentDescription = null,
        modifier = Modifier
            .size(200.dp)

    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 10.dp),
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
                        IconButton(onClick = { onNavigateToFavorites() }) {
                            Icon(
                                painter = painterResource(id = R.drawable.favorite),
                                contentDescription = "Favoritos",
                                modifier = Modifier.size(29.dp)
                            )
                        }
                        IconButton(onClick = { onNavigateToMainMenu() }) {
                            Icon(
                                painter = painterResource(id = R.drawable.home),
                                contentDescription = "Inicio",
                                modifier = Modifier.size(25.dp)
                            )
                        }
                        IconButton(onClick = { onNavigateToSettings() }) {
                            Icon(
                                painter = painterResource(id = R.drawable.submenu),
                                contentDescription = "Submenu",
                                modifier = Modifier.size(29.dp)
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
        ) {
            Box (modifier = Modifier
                .background(Color.White)
                .fillMaxSize())
            {
                Column (modifier = Modifier
                    .background(Color(0xFFe3d6c3))
                    .fillMaxSize()
                    .padding(8.dp)
                    .verticalScroll(scrollState)) {
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 30.dp, top = 20.dp, end = 30.dp, bottom = 30.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.Top,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            Image(
                                painter = painterResource(id = product?.image ?: R.drawable.loki2),
                                contentDescription = "Imagen de ${product?.name}",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.LightGray, shape = RoundedCornerShape(8.dp)),
                                alignment = Alignment.Center,
                                contentScale = ContentScale.Crop
                            )
                        }


                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Row(horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()) {
                                Text(
                                    product?.name ?: "Producto no disponible",
                                    fontSize = 30.sp
                                )
                                Image(
                                painter = imagen,
                                contentDescription = "Añadir a favoritos",
                                modifier = Modifier
                                    .size(30.dp)
                                    .clickable {
                                        imagenActual = !imagenActual
                                    }
                            ) }

                            Text(
                                "Categoria: " + product?.category,
                                fontSize = 15.sp
                            )

                            Text(
                                "Precio: $" + product?.price,
                                fontSize = 15.sp
                            )

                            Text(
                                "Vendedor: " + product?.owner?.businessName,
                                modifier = Modifier.clickable {
                                    onNavigateToSeller(product?.owner?.user?.id.toString())
                                },
                                fontSize = 15.sp
                            )

                            Text(
                                "Descripcion: \n" + product?.description,
                                fontSize = 15.sp
                            )

                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Box {
                        Text(
                            "Otros productos de " + product?.owner?.businessName,
                            fontSize = 20.sp,
                        )

                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 30.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(sellerProducts) { product ->
                                Box(
                                    modifier = Modifier
                                        .width(180.dp)
                                        .clickable {
                                            onNavigateToProduct(product.id.toString())
                                        }
                                ) {
                                    ProductItem(product = product)
                                }
                            }
                        }


                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Box {
                        Text(
                            "Quizas te interese ",
                            fontSize = 20.sp,
                        )

                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 30.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(sameCategoryProducts) { product ->
                                Box(
                                    modifier = Modifier
                                        .width(180.dp)
                                        .clickable {
                                            onNavigateToProduct(product.id.toString())
                                        }
                                ) {
                                    ProductItem(product = product)
                                }
                            }
                        }


                    }
                }

            }
    }
}
}


