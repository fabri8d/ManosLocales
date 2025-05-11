package com.undef.ManosLocales.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.undef.ManosLocales.R
import com.undef.ManosLocales.mainmenu.ProductItem
import com.undef.ManosLocales.utils.ObjectsProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(productId: String?, onNavigateToSeller: (String) -> Unit, onNavigateToProduct: (String) -> Unit) {
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
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
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
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
        ) {
            Box (modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .padding(start = 8.dp, end = 8.dp, top = 2.dp, bottom = 2.dp)){
                Column (modifier = Modifier
                    .background(Color(0xFFe3d6c3), shape = RoundedCornerShape(12.dp))
                    .fillMaxSize()
                    .padding(8.dp)
                    .verticalScroll(scrollState)) {


                    Box(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .aspectRatio(1f)
                            .background(Color(0xFFE0E0E0), RoundedCornerShape(8.dp))
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            product?.name ?: "Producto no disponible" ,
                            fontSize = 30.sp
                        )
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
                            modifier = Modifier.clickable{
                                onNavigateToSeller(product?.owner?.user?.id.toString())
                            },
                            fontSize = 15.sp
                        )

                        Text(
                            "Descripcion: \n" + product?.description,
                            fontSize = 15.sp
                        )

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


