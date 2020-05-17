package com.salesianostriana.dam.damkeep

import com.salesianostriana.dam.damkeep.entidades.Nota
import com.salesianostriana.dam.damkeep.entidades.User
import com.salesianostriana.dam.damkeep.users.UserRepository
import com.salesianostriana.dam.damkeep.users.UserService
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.*
import javax.annotation.PostConstruct

interface NotaRepository : JpaRepository<Nota, UUID> {

    fun findByAutor(user: User):List<Nota>

    fun findById(notaId:String):Nota
}

@Component
class InitDataComponent(
        val notaRepository: NotaRepository,
        val userRepository: UserRepository,
        private val encoder: PasswordEncoder
) {

    @PostConstruct
    fun initData()  {
        val user1 = User("jallamas", encoder.encode("12345678"),"José Antonio Llamas")
        userRepository.save(user1)

        val user1notas = listOf(
                Nota("Compras", "Harina, levadura, chocolate, huevos.",
                LocalDate.of(2020, 3, 12), LocalDate.of(2020, 3, 13),user1),
                Nota("Tareas pendientes", "Hacer la compra otra vez. Peluquería, Limpieza",
                LocalDate.of(2020, 3, 14), LocalDate.of(2020, 3, 14),user1)
        )
        notaRepository.saveAll(user1notas)

        val user2 = User("usuario", encoder.encode("123456"),"José Antonio Llamas")
        userRepository.save(user2)

        val user2notas = listOf(
                Nota("Primera Nota", "Harina, levadura, chocolate, huevos.",
                        LocalDate.of(2020, 3, 12), LocalDate.of(2020, 3, 13),user2),
                Nota("Segunda Nota", "Hacer la compra. Peluquería, Limpieza",
                        LocalDate.of(2020, 3, 14), LocalDate.of(2020, 3, 14),user2)
        )

        notaRepository.saveAll(user2notas)
    }

}