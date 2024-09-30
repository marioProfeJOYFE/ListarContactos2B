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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            val viewModel = ListaContactosViewModel()
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            ListarContactos2BTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text("Listar Contactos")
                            },
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
                        )
                    },
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
                ) { innerPadding ->
                    NavigationHost(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

@Composable
private fun NavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: ListaContactosViewModel
){
    NavHost(
        navController = navController,
        startDestination = "home_view",
    ) {
        composable("home_view") {
            HomeView(modifier = modifier, viewModel)
        }
        composable("formulario_view") {
            FormularioView(modifier = modifier, viewModel)
        }
    }
}


@Composable
fun HomeView(modifier: Modifier = Modifier, viewModel: ListaContactosViewModel) {
    val listMostrar = cargarDatos()

    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        listMostrar.forEach { persona ->
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
        }
    }

}


@Composable
fun FormularioView(modifier: Modifier = Modifier, viewModel: ListaContactosViewModel){
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var hombre by remember { mutableStateOf(true) }
    var mujer by remember { mutableStateOf(false) }
    var telefono by remember { mutableStateOf("") }

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
        TextField(
            value = telefono,
            onValueChange = { texto ->
                telefono = texto
            },
            label = { Text("Telefono") }
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
                //enabled = !mujer
            )
            Text("Mujer")
            Checkbox(
                checked = mujer,
                onCheckedChange = {
                    mujer = it
                    hombre = !it
                },
                //enabled = !hombre
            )
        }
        Button(
            onClick = {
                //TODO: Guardar contacto
            }
        ) {
            Text("Guardar Contacto")
        }
    }
}

/**
 *
 */
fun cargarDatos(): ArrayList<Persona>{
    //TODO: Cargar datos del viewModel
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
    ListContactos.add(Persona(1,"Alba", "Pepon", "M",123456789))
    ListContactos.add(Persona(2,"Alberto", "Pepon", "H",123456789))
    return ListContactos
}

