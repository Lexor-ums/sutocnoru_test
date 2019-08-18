package com.example.sutocnoru_test.presentation

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector
    abstract fun provideMainActivityViewModel() : MainActivityViewModel
}