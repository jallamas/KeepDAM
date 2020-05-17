package com.salesianostriana.keepdam.models.requests

data class RegisterReq(
    val username: String,
    val fullname: String,
    val password: String,
    val password2: String

)