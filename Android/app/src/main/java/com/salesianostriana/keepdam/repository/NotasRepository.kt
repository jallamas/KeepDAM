package com.salesianostriana.keepdam.repository

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.salesianostriana.keepdam.common.MyApp
import com.salesianostriana.keepdam.models.Nota
import com.salesianostriana.keepdam.models.requests.CreateNotaReq
import com.salesianostriana.keepdam.retrofit.KeepDamService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NotasRepository @Inject constructor(var keepDamService: KeepDamService){
    var notas : MutableLiveData<List<Nota>> = MutableLiveData()
    var nota : MutableLiveData<Nota> = MutableLiveData()

    fun crearNota(req : CreateNotaReq) : MutableLiveData<Nota>{
        val call : Call<Nota>? = keepDamService.createNota(req)
        call?.enqueue(object : Callback<Nota>{
            override fun onResponse(call: Call<Nota>, response: Response<Nota>) {
                if(response.isSuccessful){
                    nota.value = response.body()
                }else{
                    Toast.makeText(MyApp.instance, "Error al crear la nota", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<Nota>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Se produjo un error", Toast.LENGTH_SHORT).show()
            }
        })
        return nota
    }

    fun editarNota(id : String, req: CreateNotaReq): MutableLiveData<Nota>{
        val call: Call<Nota>? = keepDamService.editNota(id,req)
        call?.enqueue(object : Callback<Nota>{
            override fun onResponse(call: Call<Nota>, response: Response<Nota>) {
                if(response.isSuccessful){
                    nota.value = response.body()
                }else{
                    Toast.makeText(MyApp.instance, "Error al editar la nota", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Nota>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Se produjo un error", Toast.LENGTH_SHORT).show()
            }
        })
        return nota
    }

    fun delNota(id : String){
        val call : Call<Void>? = keepDamService.deleteNota(id)
        call?.enqueue(object : Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if(response.isSuccessful){
                    Toast.makeText(MyApp.instance, "La nota se eliminó correctamente", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(MyApp.instance, "Error al eliminar la nota", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Se produjo un error", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getAllNotas() : MutableLiveData<List<Nota>>{
        val call : Call<List<Nota>>? = keepDamService.getNotasUser()
        call?.enqueue(object : Callback<List<Nota>>{
            override fun onResponse(call: Call<List<Nota>>, response: Response<List<Nota>>) {
                if(response.isSuccessful){
                    notas.value = response.body()
                }else{
                    Toast.makeText(MyApp.instance, "Error listar las notas", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<List<Nota>>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Se produjo un error", Toast.LENGTH_SHORT).show()
            }
        })
        return notas
    }

    fun getNota(id : String): MutableLiveData<Nota>{
        val call : Call<Nota>? = keepDamService.getNota(id)
        call?.enqueue(object : Callback<Nota>{
            override fun onResponse(call: Call<Nota>, response: Response<Nota>) {
                if(response.isSuccessful){
                    nota.value = response.body()
                } else {
                    Toast.makeText(MyApp.instance, "Error al mostrar la nota", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Nota>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Se produjo un error", Toast.LENGTH_SHORT).show()
            }
        })
        return nota
    }
}