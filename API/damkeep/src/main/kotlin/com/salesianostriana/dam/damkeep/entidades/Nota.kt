package com.salesianostriana.dam.damkeep.entidades

import com.fasterxml.jackson.annotation.JsonBackReference
import java.time.LocalDate
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
data class Nota(
        var titulo: String,
        var contenido: String,
        val fechaCreacion : LocalDate,
        var fechaModificacion : LocalDate,
        @JsonBackReference @ManyToOne var autor: User,
        @Id @GeneratedValue val id : UUID? = null
)