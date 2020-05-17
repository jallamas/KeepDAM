package com.salesianostriana.keepdam.models.response

import java.util.*

data class RegisterResponse(
    val username: String,
    val fullName: String,
    val id: UUID
)