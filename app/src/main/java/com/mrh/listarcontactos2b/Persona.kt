package com.mrh.listarcontactos2b

/**
 *  Clase donde almaceno datos de contacto
 */
data class Persona(
    val id: Int,
    val nombre: String,
    val apellido: String,
    val sexo: String,
    val telefono: Int
)
