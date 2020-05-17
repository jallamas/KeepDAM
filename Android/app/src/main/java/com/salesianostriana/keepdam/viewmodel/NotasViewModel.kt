package com.salesianostriana.keepdam.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.salesianostriana.keepdam.models.Nota
import com.salesianostriana.keepdam.models.requests.CreateNotaReq
import com.salesianostriana.keepdam.repository.NotasRepository
import javax.inject.Inject

class NotasViewModel @Inject constructor(notasRepository: NotasRepository) : ViewModel(){
    val repository = notasRepository

    fun createNota(req : CreateNotaReq) : LiveData<Nota> = repository.crearNota(req)

    fun editNota(idNota:String,req:CreateNotaReq) : LiveData<Nota> = repository.editarNota(idNota,req)

    fun deleteNota(idNota:String) = repository.delNota(idNota)

    fun getNota(idNota: String):LiveData<Nota> = repository.getNota(idNota)

    fun getAllNotas():LiveData<List<Nota>> = repository.getAllNotas()

}