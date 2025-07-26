//package com.undef.ManosLocales.ui.screens.mainmenu
//
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Button
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import com.undef.ManosLocales.data.local.entities.Product
//import com.undef.ManosLocales.data.local.entities.Seller
//import com.undef.ManosLocales.data.local.entities.User
//import com.undef.ManosLocales.ui.viewmodels.ProductsViewModel
//
//@Composable
//fun CreateProductScreen(
//    ownerId: Int,
//    onProductCreated: () -> Unit,
//    viewModel: ProductsViewModel = hiltViewModel()
//) {
//    var name by remember { mutableStateOf("") }
//    var price by remember { mutableStateOf("") }
//    var category by remember { mutableStateOf("") }
//    var description by remember { mutableStateOf("") }
//
//    Column(modifier = Modifier.padding(16.dp)) {
//        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Nombre") })
//        OutlinedTextField(value = price, onValueChange = { price = it }, label = { Text("Precio") })
//        OutlinedTextField(value = category, onValueChange = { category = it }, label = { Text("Categoría") })
//        OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Descripción") })
//
//        Spacer(modifier = Modifier.height(16.dp))
//        Button(onClick = {
//            val product = Product(
//                id = (0..1000000).random(), // o usar UUID o autogenerado
//                name = name,
//                price = price.toDoubleOrNull() ?: 0.0,
//                category = category,
//                description = description,
//                owner = Seller(
//                    user = User(id = ownerId, name = "", email = "", password = ""), // datos mínimos
//                    phone = "",
//                    city = ""
//                ),
//                image = R.drawable.placeholder // imagen por defecto o selector
//            )
//            viewModel.insertProduct(product)
//            onProductCreated()
//        }) {
//            Text("Guardar producto")
//        }
//    }
//}
