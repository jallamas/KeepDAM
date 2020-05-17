package com.salesianostriana.keepdam.models

import java.util.*

data class User(
    val id: UUID,
    val username: String,
    val fullName: String
)