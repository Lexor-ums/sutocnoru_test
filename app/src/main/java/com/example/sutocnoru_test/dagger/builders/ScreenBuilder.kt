package com.example.sutocnoru_test.dagger.builders

import com.example.sutocnoru_test.presentation.MainActivity
import com.example.sutocnoru_test.presentation.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ScreenBuilder {


    @ContributesAndroidInjector(
        modules = [
            MainActivityModule::class
        ]
    )
    abstract fun contributeMainActivity(): MainActivity
}