package com.example.sutocnoru_test

import android.app.Application
import android.content.res.Resources
import com.example.sutocnoru_test.dagger.components.ApplicationMainComponent
import com.example.sutocnoru_test.dagger.components.DaggerApplicationMainComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainApplication : Application(), HasAndroidInjector{

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    private lateinit var appResources: Resources
    private var appComponent: ApplicationMainComponent? = null

    override fun onCreate() {
        super.onCreate()
        appResources = resources
        instance = this
//        DaggerApplicationMainComponent.create()
//            .injectApplication(this)
        appComponent = DaggerApplicationMainComponent.builder()
            .application(this)
            .build()
        appComponent!!.inject(this);
//        AppInjector.init(this);
    }

    fun getRsources() : Resources{
        return appResources
    }

    companion object {
        lateinit var instance: MainApplication
            private set
    }

    override fun androidInjector(): AndroidInjector<Any> =  androidInjector

}