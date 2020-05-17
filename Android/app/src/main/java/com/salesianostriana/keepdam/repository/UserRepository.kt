package com.salesianostriana.keepdam.repository

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.salesianostriana.keepdam.common.Constantes
import com.salesianostriana.keepdam.common.MyApp
import com.salesianostriana.keepdam.common.MySharedPreferencesManager
import com.salesianostriana.keepdam.models.User
import com.salesianostriana.keepdam.models.requests.LoginReq
import com.salesianostriana.keepdam.models.requests.RegisterReq
import com.salesianostriana.keepdam.models.response.LoginResponse
import com.salesianostriana.keepdam.retrofit.KeepDamService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(var keepDamService: KeepDamService){
    var user: MutableLiveData<User> = MutableLiveData()
    var newUser: MutableLiveData<User> = MutableLiveData()

    fun login(req: LoginReq) : MutableLiveData<User>{
        val call : Call<LoginResponse>? = keepDamService.login(req)
        call?.enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if(response.isSuccessful){
                    user.value = response.body()?.user
                    MySharedPreferencesManager().setStringValue(
                        Constantes.SHARED_PREFERENCES_TOKEN_KEY,
                        response.body()!!.token
                    )
                }else{
                    user.postValue(null)
                    MySharedPreferencesManager().removeStringValue(Constantes.SHARED_PREFERENCES_TOKEN_KEY)
                    Toast.makeText(MyApp.instance, "El usuario o contraseña no son correctos", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Se produjo un error", Toast.LENGTH_SHORT).show()
            }
        })
        return user
    }

    fun register(req : RegisterReq) : MutableLiveData<User>{
        val call : Call<User>? = keepDamService.signup(req)
        call?.enqueue(object : Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.isSuccessful){
                    newUser.value = response.body()
                }else{
                    Toast.makeText(MyApp.instance, "Error en los datos registro", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Se produjo un error", Toast.LENGTH_SHORT).show()
            }
        })
        return newUser
    }
}