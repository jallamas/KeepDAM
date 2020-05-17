package com.salesianostriana.keepdam.retrofit

import com.salesianostriana.keepdam.models.Nota
import com.salesianostriana.keepdam.models.User
import com.salesianostriana.keepdam.models.requests.CreateNotaReq
import com.salesianostriana.keepdam.models.requests.LoginReq
import com.salesianostriana.keepdam.models.requests.RegisterReq
import com.salesianostriana.keepdam.models.response.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface KeepDamService {

    @POST("/auth/login")
    fun login(@Body request: LoginReq): Call<LoginResponse>

    @POST("/user/")
    fun signup(@Body request: RegisterReq): Call<User>

    @GET("/notas/")
    fun getNotasUser(): Call<List<Nota>>

    @GET("/notas/{notaId}")
    fun getNota(@Path("notaId") id: String): Call<Nota>

    @DELETE("/notas/{notaId}")
    fun deleteNota(@Path("notaId") id: String): Call<Void>

    @PUT("/notas/{notaId}")
    fun editNota(@Path("notaId") id: String, @Body request: CreateNotaReq): Call<Nota>

    @POST("/notas/")
    fun createNota(@Body request: CreateNotaReq): Call<Nota>
}