    package com.undef.ManosLocales

    import androidx.compose.foundation.background
    import androidx.compose.foundation.clickable
    import androidx.compose.foundation.layout.*
    import androidx.compose.foundation.lazy.LazyColumn
    import androidx.compose.foundation.lazy.items
    import androidx.compose.material3.*
    import androidx.compose.runtime.*
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.material3.CheckboxDefaults.colors
    import androidx.compose.ui.text.font.FontWeight
    import androidx.compose.ui.draw.shadow
    import androidx.compose.ui.res.painterResource

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Posts() {
        val productsList = remember { ObjectsProvider.productsList }

        var selectedCategory by remember { mutableStateOf("Todos") }
        var expanded by remember { mutableStateOf(false) }

        val categories = listOf(
            "Todos",
            "Higiene",
            "Decoración",
            "Accesorios",
            "Pan",
            "Textil",
            "Bebida",
            "Papelería",
            "Cerámica"
        )

        val filteredProducts = if (selectedCategory == "Todos") {
            productsList
        } else {
            productsList.filter { it.category == selectedCategory }
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Manos Locales",
                            color = Color.White
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {  },
                            shape = RoundedCornerShape(0)) {
                            Icon(
                                painter = painterResource(id = R.drawable.submenu),
                                contentDescription = "Submenu",
                                tint = Color.White
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
                    containerColor = Color(0xFF404934),
                    content = {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            IconButton(onClick = { /* Acción 1 */ }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.favorite),
                                    contentDescription = "Submenu"
                                )
                            }
                            IconButton(onClick = { /* Acción 2 */ }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.home),
                                    contentDescription = "Submenu"
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
                        text = "Productos",
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 16.dp),
                        color = Color(0xFFe3d6c3),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Box {
                        Text(
                            "Categoría: $selectedCategory",
                            color = Color(0xFFe3d6c3),
                            modifier = Modifier
                                .padding(16.dp)
                                .clickable { expanded = !expanded }
                        )
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.padding(10.dp)
                        ) {
                            categories.forEach { category ->
                                DropdownMenuItem(
                                    text = { Text(category) },
                                    onClick = {
                                        selectedCategory = category
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                }
                Row(modifier = Modifier
                    .background(Color(0xFFe3d6c3))
                    .fillMaxHeight()) {
                    LazyColumn() {
                        items(filteredProducts.chunked(2)) { rowItems ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                rowItems.forEach { product ->
                                    Box(modifier = Modifier.weight(1f)) {
                                        ProductItem(product = product)
                                    }
                                }
                                if (rowItems.size == 1) {
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                        }
                    }
                }

            }
        }
    }


    @Composable
    fun ProductItem(product: Product) {
        Column(
            modifier = Modifier
                .padding(4.dp)
                .shadow(4.dp, RoundedCornerShape(12.dp))
                .background(Color.White, shape = RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            // Placeholder para imagen
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .background(Color(0xFFE0E0E0), RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = product.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )

            Text(
                text = "$${product.price}",
                fontSize = 14.sp,
                color = Color(0xFF555555),
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
