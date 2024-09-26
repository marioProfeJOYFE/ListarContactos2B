package com.mrh.listarcontactos2b

import android.os.Bundle
import android.util.Log
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text("Listar Contactos")
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
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
private fun NavigationHost(
    modifier: Modifier = Modifier,
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = "home_view",
    ) {
        composable("home_view") {
            HomeView(modifier = modifier)
        }
        composable("formulario_view") {
            FormularioView(modifier = modifier)
        }
    }
}


@Composable
fun HomeView(modifier: Modifier = Modifier) {
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
fun FormularioView(modifier: Modifier = Modifier){
    Column(
        modifier = modifier
    ) {
        Text("ventana 2")
    }
}

/**
 *
 */
fun cargarDatos(): ArrayList<Persona>{
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

