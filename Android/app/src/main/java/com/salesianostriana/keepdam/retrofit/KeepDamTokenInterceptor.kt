package com.salesianostriana.keepdam.retrofit

import com.salesianostriana.keepdam.common.Constantes
import com.salesianostriana.keepdam.common.MySharedPreferencesManager
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KeepDamTokenInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val request: Request
        val token = MySharedPreferencesManager().getSharedPreferences()
            .getString(Constantes.SHARED_PREFERENCES_TOKEN_KEY, "")

        val requestBuilder: Request.Builder =
            original.newBuilder().header("Authorization", "Bearer " + token)
        request = requestBuilder.build()

        return chain.proceed(request)
    }
}