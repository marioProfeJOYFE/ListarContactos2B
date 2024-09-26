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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mrh.listarcontactos2b.ui.theme.ListarContactos2BTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
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
                        FilledIconButton(
                            onClick = { Log.d("AccionBoton","Añadir") }
                        ) {
                            Icon(imageVector = Icons.Filled.Add, contentDescription = "Añadir")
                        }
                    }
                ) { innerPadding ->
                    HomeView(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
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

