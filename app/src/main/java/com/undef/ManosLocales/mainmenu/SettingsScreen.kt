package com.undef.ManosLocales.mainmenu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.undef.ManosLocales.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(onNavigateToMainMenu: () -> Unit,
                   onNavigateToFavorites: () -> Unit){
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
                        IconButton(onClick = { }) {
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
        Column (modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(Color(0xFFe3d6c3))) {
            Column(modifier = Modifier.fillMaxWidth()
                .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(15.dp)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                        .height(40.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.usuario),
                            contentDescription = "Mi cuenta",
                            modifier = Modifier.size(20.dp)

                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "Mi cuenta",
                            fontSize = 20.sp
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .height(40.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.favorite),
                            contentDescription = "Favoritos",
                            modifier = Modifier.size(20.dp)

                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "Recibir actualizaciones de favoritos",
                            fontSize = 20.sp
                        )
                    }

                    Checkbox(checked = false, onCheckedChange = {})
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .height(40.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.campana),
                            contentDescription = "Campana",
                            modifier = Modifier.size(20.dp)

                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "Recibir notificaciones de nuevas",
                            fontSize = 20.sp
                        )
                    }
                    Checkbox(checked = false, onCheckedChange = {})
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .height(40.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.correoelectronico),
                            contentDescription = "mail",
                            modifier = Modifier.size(20.dp)

                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "Contactar con el desarrollador",
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }

    }
}