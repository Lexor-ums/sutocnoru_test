package com.example.sutocnoru_test.domain.interactors

import com.example.calculator.utils.events.Events
import com.example.sutocnoru_test.data.net.retrofit.JsonApiService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus

import javax.inject.Inject

class LoadRating @Inject constructor(): BaseInteractor(){
    @Inject
    lateinit var api : JsonApiService

    fun requestRating() {
        GlobalScope.launch {
            val json = safeApiCall(
                call = { api.getActualAsync().await() },
                errorMessage = "Can`t do request fon network"
            )
            println("Received json ${json.toString()}")
            EventBus.getDefault().post(Events.RatingsLoaded(json?.toList()))
        }
    }
}