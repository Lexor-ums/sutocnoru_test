package com.example.sutocnoru_test.presentation.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<T : ViewModel?> : DaggerAppCompatActivity(){
    var viewModel : T? = null
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    abstract fun getViewModel() : Class<T>

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
//        this.viewModel = if(viewModel == null)
            viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModel())
//        else
//            viewModel
    }
}