package com.undef.ManosLocales
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Login() {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val rememberMe = remember { mutableStateOf(false) }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFe3d6c3)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(0.85f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logotipo),
                contentDescription = "Logo de la app",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(200.dp)
            )
            Row(){
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Bienvenido", color = Color(0xFF404934), fontSize = 40.sp)
                    Text("Inicie sesion para continuar",
                        color = Color(0xFF404934),
                        fontSize = 15.sp,
                        modifier = Modifier.padding(top = 10.dp)) }

            }


            TextField(
                value = email.value,
                onValueChange = { email.value = it },
                label = { Text("Email", color = Color(0xFF7C5C44)) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedTextColor = Color(0xFF7C5C44),
                    unfocusedTextColor = Color(0xFF7C5C44),
                    focusedLabelColor = Color(0xFF7C5C44),
                    unfocusedLabelColor = Color(0xFF7C5C44),
                    cursorColor = Color(0xFF7C5C44),
                    focusedIndicatorColor = Color(0xFF7C5C44),
                    unfocusedIndicatorColor = Color(0xFF7C5C44)
                )
            )

            TextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Contraseña", color = Color(0xFF7C5C44)) },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedTextColor = Color(0xFF7C5C44),
                    unfocusedTextColor = Color(0xFF7C5C44),
                    focusedLabelColor = Color(0xFF7C5C44),
                    unfocusedLabelColor = Color(0xFF7C5C44),
                    cursorColor = Color(0xFF7C5C44),
                    focusedIndicatorColor = Color(0xFF7C5C44),
                    unfocusedIndicatorColor = Color(0xFF7C5C44)
                )

            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ){
                Text(
                    text = "Olvide mi contraseña",
                    color = Color(0xFF404934),
                    modifier = Modifier
                        .clickable {
                            val intent = Intent(context, SinginActivity::class.java)
                            context.startActivity(intent)
                        }

                )
            }


            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Checkbox(
                    checked = rememberMe.value,
                    onCheckedChange = { rememberMe.value = it },
                    colors = CheckboxDefaults.colors(
                        checkmarkColor = Color.White,
                        uncheckedColor = Color(0xFF7C5C44),
                        checkedColor = Color(0xFF7C5C44)
                    )
                )
                Text("Recordame", color = Color(0xFF7C5C44))

            }

            Button(
                onClick = {
                    val intent = Intent(context, MainMenuActivity::class.java)
                    context.startActivity(intent) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF997052)
                ),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text("Iniciar sesion",
                    color = Color.White
                )
            }
            Text(
                text = "¿No tenés cuenta? Registrate.",
                color = Color(0xFF404934),
                modifier = Modifier
                    .clickable {
                        val intent = Intent(context, SinginActivity::class.java)
                        context.startActivity(intent)
                    }
            )

        }
    }
}

