package com.mrh.listarcontactos2b

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListaContactosViewModel : ViewModel() {
    private val _contactos = MutableLiveData<List<Persona>>()
    var contactos: LiveData<List<Persona>> = _contactos
}