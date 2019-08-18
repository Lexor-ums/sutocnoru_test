package com.example.sutocnoru_test.presentation

import android.os.Bundle
import com.example.sutocnoru_test.R
import com.example.sutocnoru_test.presentation.base.BaseActivity
import javax.inject.Inject


class MainActivity : BaseActivity<MainActivityViewModel>() {
    @Inject
    lateinit var viewmodel : MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun getViewModel(): MainActivityViewModel {
        return viewmodel
    }

    override fun onStart() {
        super.onStart()
        viewmodel.loadJson()
    }
}
