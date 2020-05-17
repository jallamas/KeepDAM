package com.salesianostriana.keepdam.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.salesianostriana.keepdam.models.User
import com.salesianostriana.keepdam.models.requests.LoginReq
import com.salesianostriana.keepdam.models.requests.RegisterReq
import com.salesianostriana.keepdam.repository.UserRepository
import javax.inject.Inject

class UserViewModel @Inject constructor(userRepository: UserRepository) : ViewModel(){
    val repository = userRepository

    fun doLogin(req : LoginReq) : LiveData<User> = repository.login(req)

    fun signup(req : RegisterReq) : LiveData<User> = repository.register(req)
}