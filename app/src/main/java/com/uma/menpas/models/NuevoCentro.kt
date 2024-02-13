package com.uma.menpas.models

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

data class NuevoCentro (
    var idCentro: String,

    var nombreUsuario1: String,

    var nombreCentro: String,

    var localidad: String,

    var provincia: String,

    var pais: String,

    var direccion: String,

    var telefono: String,

    var codigoPostal: String,

    var limitado: String,

    var contador: String,
)