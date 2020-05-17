package com.salesianostriana.keepdam.models.response

import com.salesianostriana.keepdam.models.User

data class LoginResponse(
    val token: String,
    val user: User
)