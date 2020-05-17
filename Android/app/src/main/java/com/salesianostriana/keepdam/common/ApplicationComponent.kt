package com.salesianostriana.keepdam.common

import com.salesianostriana.keepdam.*
import com.salesianostriana.keepdam.retrofit.KeepDamClient
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [KeepDamClient::class])
interface ApplicationComponent {
    fun inject(main: LoginActivity)
    fun inject(registerActivity: RegisterActivity)
    fun inject(notasFragment: NotasFragment)
    fun inject(notasActivity: NotasActivity)
    fun inject(nuevaNotaActivity: NuevaNotaActivity)
    fun inject(notaDetalleActivity: NotaDetalleActivity)
}