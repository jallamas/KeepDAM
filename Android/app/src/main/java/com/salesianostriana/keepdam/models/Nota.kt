package com.salesianostriana.keepdam.models

import java.util.*

data class Nota(
    val titulo: String,
    val contenido: String,
    val fechaCreacion: String,
    val fechaModificacion: String,
    val id: UUID
)