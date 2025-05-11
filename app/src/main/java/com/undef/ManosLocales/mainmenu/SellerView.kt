package com.undef.ManosLocales.mainmenu


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.undef.ManosLocales.entities.Seller

@Composable
fun SellerItem(seller: Seller) {
    Column(
        modifier = Modifier
            .padding(4.dp)
            .shadow(4.dp, RoundedCornerShape(12.dp))
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .background(Color(0xFFE0E0E0), RoundedCornerShape(8.dp))
        )
//        // Imagen del vendedor
//        Image(
//            painter = rememberAsyncImagePainter(seller.image),
//            contentDescription = "Imagen de ${seller.name} ${seller.surname}",
//            modifier = Modifier
//                .height(100.dp)
//                .fillMaxWidth()
//                .background(Color.LightGray, shape = RoundedCornerShape(8.dp)), // Color de fondo predeterminado
//            alignment = Alignment.Center,
//            contentScale = ContentScale.Crop
//        )

        Spacer(modifier = Modifier.height(8.dp))

        // Nombre y apellido del vendedor
        Text(
            text = "${seller.user.name} ${seller.user.surname}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )

        // Ciudad del vendedor
        Text(
            text = seller.user.city,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
    }
}
