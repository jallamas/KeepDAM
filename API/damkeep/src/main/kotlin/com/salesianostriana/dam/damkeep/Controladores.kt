package com.salesianostriana.dam.damkeep

import com.salesianostriana.dam.damkeep.entidades.CreateNotaDTO
import com.salesianostriana.dam.damkeep.entidades.Nota
import com.salesianostriana.dam.damkeep.entidades.User
import com.salesianostriana.dam.damkeep.users.toUserDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDate
import java.util.*

@RestController
@RequestMapping("/notas")
class NotaController(
        val notaRepository : NotaRepository,
        val notaService: NotaService
) {

    private fun todasLasNotas(): List<Nota> {
        var result: List<Nota>
        with(notaRepository) {
            result = findAll()
        }
        if (result.isEmpty())
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "No hay notas almacenadas")
        return result
    }

    private fun unaNota(id: UUID): Nota {
        var result: Optional<Nota>
        with(notaRepository) {
            result = findById(id)
        }
        return result.orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado la nota con el identificador $id") }
    }

    @GetMapping("/")
    fun todasPorUsuario(@AuthenticationPrincipal user : User)  = todasLasNotas().filter { it.autor.equals(user) }.map { it }

    @GetMapping("/{notaId}")
    fun detalleNota(@PathVariable notaId : UUID) = unaNota(notaId)

    @PostMapping("/")
    fun createNota(@AuthenticationPrincipal user : User, @RequestBody newNota : CreateNotaDTO): ResponseEntity<CreateNotaDTO> =
        notaService.create(newNota, user).map { ResponseEntity.status(HttpStatus.CREATED).body(newNota) }.orElseThrow {
            ResponseStatusException(HttpStatus.BAD_REQUEST, "Se produjo un error")
        }

    @PutMapping("/{notaId}")
    fun editNota(@RequestBody editNota: CreateNotaDTO, @PathVariable notaId : UUID): Nota? {
        return notaRepository.findById(notaId)
                .map {nota ->
                    val notaEditada : Nota =
                            nota.copy(titulo = editNota.titulo,
                            contenido = editNota.contenido,
                            fechaModificacion = LocalDate.now())
                    notaRepository.save(notaEditada)
                }.orElseThrow{
                    ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha encontrado la nota con el identificador $notaId")
                }
    }

    @DeleteMapping("/{notaId}")
    fun deleteNota(@PathVariable notaId : UUID) : ResponseEntity<Void>{
        notaRepository.deleteById(notaId)
        return ResponseEntity.noContent().build()
    }

}