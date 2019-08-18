package com.example.sutocnoru_test.dagger.components

import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton
import android.app.Application
import com.example.sutocnoru_test.MainApplication
import com.example.sutocnoru_test.dagger.builders.ScreenBuilder
import com.example.sutocnoru_test.dagger.modules.ApplicationMainModule
import com.example.sutocnoru_test.dagger.modules.NetworkModule
import com.example.sutocnoru_test.dagger.modules.ViewModelModule
import dagger.BindsInstance



@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    ApplicationMainModule::class,
    NetworkModule::class,
    ViewModelModule::class,
    ScreenBuilder::class])

interface ApplicationMainComponent {
    fun inject(app: MainApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationMainComponent
    }
}