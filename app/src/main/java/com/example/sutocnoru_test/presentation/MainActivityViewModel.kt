package com.example.sutocnoru_test.presentation

import androidx.lifecycle.ViewModel
import com.example.sutocnoru_test.domain.interactors.LoadRating
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(val interactor: LoadRating) : ViewModel() {

    fun loadJson(){
        interactor.requestRating()
    }
}