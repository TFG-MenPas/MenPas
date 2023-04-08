package com.uma.menpas.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class Usuario(
    @PrimaryKey(autoGenerate = false)
    var nombreUsuario: String,
    var contrasenya: String,
    var nombre: String,
    var apellidos: String,
    var edad: Int,
    var telefono: String,
    var dni: String,
    var sexo: String,
    var fechaRegistro: String,
    var perfil: String,
    var correo: String,
    var deportePracticado: String,
    var grupo: String,
    var nacionalidad: String,
    var estadoCivil: String,
    var horasSemanales: Int,
    var estudios: String,
    var profesion: String,
    var aComienzoDeporte: Int
)