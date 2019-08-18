package com.example.sutocnoru_test.dagger.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sutocnoru_test.dagger.ViewModelKey
import com.example.sutocnoru_test.presentation.MainActivityViewModel
import com.example.sutocnoru_test.presentation.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule{
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainViewModel(view : MainActivityViewModel) : ViewModel


    @Binds
    abstract fun bindViewModuleFactory(viewModuleFactory: ViewModelFactory) : ViewModelProvider.Factory
}