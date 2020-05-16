package com.salesianostriana.dam.damkeep.users

import com.salesianostriana.dam.damkeep.entidades.User
import java.util.*

data class UserDTO(
        var username : String,
        var fullName: String,
        val id: UUID? = null
)

fun User.toUserDTO() = UserDTO(username, fullName, id)

data class CreateUserDTO(
        var username: String,
        var fullName: String,
        val password: String,
        val password2: String
)