package com.example.sutocnoru_test.dagger.components

import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton
import android.app.Application
import com.example.sutocnoru_test.MainApplication
import com.example.sutocnoru_test.dagger.builders.ScreenBuilder
import com.example.sutocnoru_test.dagger.modules.ApplicationMainModule
import com.example.sutocnoru_test.dagger.modules.NetworkModule
import com.example.sutocnoru_test.dagger.modules.PicassoModule
import com.example.sutocnoru_test.dagger.modules.ViewModelModule
import com.example.sutocnoru_test.presentation.ImageViewAdapter
import com.squareup.picasso.Picasso
import dagger.BindsInstance



@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    ApplicationMainModule::class,
    PicassoModule::class,
    NetworkModule::class,
    ViewModelModule::class,
    ScreenBuilder::class])

interface ApplicationMainComponent {
    fun inject(app: MainApplication)
    fun inject(imageViewAdapter: ImageViewAdapter)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

//        @BindsInstance

        fun build(): ApplicationMainComponent
    }
}