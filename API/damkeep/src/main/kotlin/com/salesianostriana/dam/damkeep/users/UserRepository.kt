package com.salesianostriana.dam.damkeep.users

import com.salesianostriana.dam.damkeep.entidades.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, UUID> {

    fun findByUsername(userId : String) : Optional<User>

}