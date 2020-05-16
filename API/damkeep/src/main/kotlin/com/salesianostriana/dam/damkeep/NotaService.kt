package com.salesianostriana.dam.damkeep

import com.salesianostriana.dam.damkeep.entidades.CreateNotaDTO
import com.salesianostriana.dam.damkeep.entidades.Nota
import com.salesianostriana.dam.damkeep.entidades.User
import com.salesianostriana.dam.damkeep.users.CreateUserDTO
import com.salesianostriana.dam.damkeep.users.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class NotaService(
        private val notaRepository: NotaRepository
 ) {

    fun create(newNota : CreateNotaDTO, user:User): Optional<Nota> {
        return Optional.of(
                with(newNota) {
                    notaRepository.save(Nota(titulo,contenido, LocalDate.now(), LocalDate.now(),user))
                }

        )
    }
}