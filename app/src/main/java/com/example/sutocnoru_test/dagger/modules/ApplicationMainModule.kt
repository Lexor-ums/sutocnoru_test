package com.example.sutocnoru_test.dagger.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationMainModule{

    @Provides
    @Singleton
    fun provideContext(app : Application) : Context{
        return app.applicationContext
    }
}