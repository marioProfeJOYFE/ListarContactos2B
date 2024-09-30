package com.mrh.listarcontactos2b

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mrh.listarcontactos2b.ui.theme.ListarContactos2BTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            ListarContactos2BTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = Color.White,
                            ),
                            title = {
                                Text("Listar Contactos")
                            },
                            navigationIcon = {
                                if(navBackStackEntry?.destination?.route != "home_view"){
                                    IconButton(
                                        onClick = {
                                            navController.popBackStack()
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                            contentDescription = "Boton volver"
                                        )
                                    }
                                }
                            }
                            /*
                            navigationIcon = {
                                if (navBackStackEntry?.destination?.route != "home_view") {
                                    IconButton(
                                        onClick = {
                                            navController.popBackStack()
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                            contentDescription = "Volver"
                                        )
                                    }
                                }
                            }

                             */
                        )
                    },
                    floatingActionButton = {
                        FilledIconButton(
                            onClick = {
                                navController.navigate("formulario_view")
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Boton añadir contacto"
                            )
                        }
                    }
                    /*
                    floatingActionButton = {
                        if (navBackStackEntry?.destination?.route != "formulario_view") {
                            FilledIconButton(
                                onClick = {
                                    navController.navigate("formulario_view")
                                }
                            ) {
                                Icon(imageVector = Icons.Filled.Add, contentDescription = "Añadir")
                            }
                        }
                    }

                     */
                ) { innerPadding ->
                    NavigationHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController
                    )
                }
            }
        }
    }
}



/**
 *
 */
@Composable
private fun NavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = "home_view"
    ) {
        composable("home_view"){
            HomeView(modifier)
        }
        composable("formulario_view"){
            FormularioView(modifier)
        }
    }
}


@Composable
fun HomeView(modifier: Modifier = Modifier) {
    val listMostrar = cargarDatos()

    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        listMostrar.forEach { persona ->
            ContactoCard(persona)
            Spacer(Modifier.padding(12.dp))
            /*
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Card(
                        modifier = Modifier.size(30.dp),
                        shape = RoundedCornerShape(50.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ){
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(persona.nombre.substring(0,1))
                        }
                    }
                    Column {
                        Text(persona.nombre)
                        Text(persona.apellido)
                    }
                }
            }
            Spacer(Modifier.padding(14.dp))

             */
        }
    }

}

/**
 *  Funcion que muestra una tarjeta con datos del contacto
 *
 *  @params: contacto: Persona
 */
@Composable
fun ContactoCard(contacto: Persona) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier.size(30.dp),
                shape = RoundedCornerShape(50.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer
                )
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(contacto.nombre.substring(0, 1))
                }
            }
            Spacer(Modifier.padding(8.dp))
            Column {
                Text(contacto.nombre, fontWeight = FontWeight.ExtraBold)
                Text(contacto.apellido)
            }
        }

    }
}

@Composable
fun FormularioView(modifier: Modifier = Modifier) {
    //Variable mutable
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var hombre by remember { mutableStateOf(true) }
    var mujer by remember { mutableStateOf(false) }
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(Modifier.padding(12.dp))
        TextField(
            value = nombre,
            onValueChange = { texto ->
                nombre = texto
            },
            label = { Text("Nombre") }
        )
        Spacer(Modifier.padding(12.dp))
        TextField(
            value = apellido,
            onValueChange = { texto ->
                apellido = texto
            },
            label = { Text("Apellido") }
        )
        Spacer(Modifier.padding(12.dp))
        Row(verticalAlignment = Alignment.CenterVertically){
            Text("Hombre")
            Checkbox(
                checked = hombre,
                onCheckedChange = {
                    hombre = it
                    mujer = !it
                },
                enabled = !hombre
            )
            Text("Mujer")
            Checkbox(
                checked = mujer,
                onCheckedChange = {
                    mujer = it
                    hombre = !it
                },
                enabled = !mujer
            )
        }
        Spacer(Modifier.padding(12.dp))
        TextField(
            value = telefono,
            onValueChange = { texto ->
                telefono = texto
            },
            label = { Text("Telefono") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )
        Spacer(Modifier.padding(12.dp))
        Button(
            onClick = {

            }
        ) {
            Text("Guardar Contacto")
        }
    }
}

/**
 *
 */
fun cargarDatos(): ArrayList<Persona> {
    //Crear lista de personas
    val ListContactos = ArrayList<Persona>()
    //Añadir elementos a la lista
    ListContactos.add(
        //Creamos una instancia del objeto persona
        Persona(
            0,
            "Pepe",
            "Pepon",
            "H",
            123456789
        )
    )
    ListContactos.add(Persona(1, "Alba", "Pepon", "M", 123456789))
    ListContactos.add(Persona(2, "Alberto", "Pepon", "H", 123456789))
    ListContactos.add(Persona(2, "Alberto", "Pepon", "H", 123456789))
    ListContactos.add(Persona(2, "Alberto", "Pepon", "H", 123456789))
    ListContactos.add(Persona(2, "Alberto", "Pepon", "H", 123456789))
    ListContactos.add(Persona(2, "Alberto", "Pepon", "H", 123456789))
    ListContactos.add(Persona(2, "Alberto", "Pepon", "H", 123456789))
    return ListContactos
}

