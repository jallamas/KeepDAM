package com.salesianostriana.keepdam.common

import android.app.Application

class MyApp : Application(){

    companion object {
        lateinit var instance: MyApp
        lateinit var appComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerApplicationComponent.create()
    }
    fun getApplicationComponent():ApplicationComponent{
        return appComponent
    }

}