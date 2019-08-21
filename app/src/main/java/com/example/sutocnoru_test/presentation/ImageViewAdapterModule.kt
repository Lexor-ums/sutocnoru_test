package com.example.sutocnoru_test.presentation

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
abstract class ImageViewAdapterModule{

    @ContributesAndroidInjector
    abstract fun provideImageViewAdapter() : ImageViewAdapter
}