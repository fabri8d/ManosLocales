package com.undef.ManosLocales.ui.screens.mainmenu

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.undef.ManosLocales.R
import com.undef.ManosLocales.ui.viewmodels.UserViewModel
import com.undef.ManosLocales.ui.screens.authentication.LoginActivity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onNavigateToMainMenu: () -> Unit,
    onNavigateToFavorites: () -> Unit,
    onNavigateToModifyAccount: () -> Unit,
    onNavigateToBecomeSeller: () -> Unit  // Nuevo parámetro para navegación
) {
    val context = LocalContext.current
    var notificationTimer = remember { mutableStateOf("") }
    var isFavoriteChecked by remember { mutableStateOf(false) }
    var isNotificationChecked by remember { mutableStateOf(false) }
    val userViewModel: UserViewModel = hiltViewModel()

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
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(Color(0xFFe3d6c3))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Mi cuenta
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .height(56.dp)
                        .clickable { onNavigateToModifyAccount() }
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.usuario),
                            contentDescription = "Mi cuenta",
                            modifier = Modifier.size(25.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Mi cuenta", fontSize = 20.sp, color = Color(0xFF7C5C44))
                    }
                }

                // Recibir actualizaciones de favoritos
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .height(56.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.favorite),
                            contentDescription = "Favoritos",
                            modifier = Modifier.size(25.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "Recibir actualizaciones de favoritos",
                            fontSize = 20.sp,
                            color = Color(0xFF7C5C44)
                        )
                    }

                    Checkbox(
                        checked = isFavoriteChecked,
                        onCheckedChange = { isFavoriteChecked = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(0xFF7C5C44),
                            uncheckedColor = Color(0xFF7C5C44),
                            checkmarkColor = Color.White
                        )
                    )
                }

                // Recibir notificaciones de ofertas
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .height(56.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.campana),
                            contentDescription = "Campana",
                            modifier = Modifier.size(25.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "Recibir notificaciones de ofertas",
                            fontSize = 20.sp,
                            color = Color(0xFF7C5C44)
                        )
                    }

                    Checkbox(
                        checked = isNotificationChecked,
                        onCheckedChange = { isNotificationChecked = it },
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(0xFF7C5C44),
                            uncheckedColor = Color(0xFF7C5C44),
                            checkmarkColor = Color.White
                        )
                    )
                }

                // Recibir notificaciones cada X horas
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .height(56.dp)
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.alarma),
                        contentDescription = "tiempo",
                        modifier = Modifier.size(25.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Recibir notificaciones cada", fontSize = 20.sp, color = Color(0xFF7C5C44))
                    TextField(
                        value = notificationTimer.value,
                        onValueChange = { notificationTimer.value = it },
                        modifier = Modifier
                            .width(60.dp)
                            .height(56.dp),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        placeholder = { Text("0", fontSize = 20.sp) },
                        textStyle = TextStyle(fontSize = 20.sp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedTextColor = Color(0xFF7C5C44),
                            unfocusedTextColor = Color(0xFF7C5C44),
                            focusedLabelColor = Color(0xFF7C5C44),
                            unfocusedLabelColor = Color(0xFF7C5C44),
                            cursorColor = Color(0xFF7C5C44),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )

                    Text("hs", fontSize = 20.sp, color = Color(0xFF7C5C44))

                }

                // Contactar con el desarrollador
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .height(56.dp)
                        .clickable {
                            val intent = Intent(Intent.ACTION_SEND).apply {
                                type = "message/rfc822"
                                putExtra(Intent.EXTRA_EMAIL, arrayOf("lbiondi733@alumnos.iua.edu.ar"))
                                putExtra(Intent.EXTRA_SUBJECT, "Consulta sobre la app")
                                putExtra(Intent.EXTRA_TEXT, "Hola, tengo una consulta sobre...")
                            }

                            if (intent.resolveActivity(context.packageManager) != null) {
                                context.startActivity(Intent.createChooser(intent, "Enviar correo con:"))
                            } else {
                                Toast.makeText(
                                    context,
                                    "No se encontró una app de correo",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.mail),
                            contentDescription = "mail",
                            modifier = Modifier.size(25.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Contactar con el desarrollador", fontSize = 20.sp, color = Color(0xFF7C5C44))
                    }
                }

                // NUEVO: Hacerse vendedor
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .height(56.dp)
                        .clickable {
                            onNavigateToBecomeSeller()
                        }
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.usuario), // Cambia por el ícono que tengas para tienda
                            contentDescription = "Hacerse vendedor",
                            modifier = Modifier.size(25.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Hacerse vendedor", fontSize = 20.sp, color = Color(0xFF7C5C44))
                    }
                }

                // Cerrar sesión
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .height(56.dp)
                        .clickable {
                            userViewModel.logout()
                            context.startActivity(Intent(context, LoginActivity::class.java))
                        }
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.usuario),
                            contentDescription = "cerrar sesión",
                            modifier = Modifier.size(25.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Cerrar sesión", fontSize = 20.sp, color = Color(0xFF7C5C44))
                    }
                }
            }
        }
    }
}
