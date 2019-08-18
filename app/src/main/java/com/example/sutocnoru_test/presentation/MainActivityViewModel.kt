package com.example.sutocnoru_test.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calculator.utils.events.Events
import com.example.sutocnoru_test.data.net.retrofit.RatingResponse
import com.example.sutocnoru_test.domain.interactors.LoadRating
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(val interactor: LoadRating) : ViewModel() {

    var ratingsLisr = MutableLiveData<List<RatingResponse>>()
    private var totalRatingMut = MutableLiveData("")
    val totalRating : LiveData<String>  = totalRatingMut
    private var ratingItemsHolder = mutableMapOf<Int, Float>()
    fun addRatingItem(ratingBar: Int, rating: Float) {
        ratingItemsHolder[ratingBar] = rating
        recalcTotal()
    }

    fun loadJson() {
        interactor.requestRating()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onLoadRatingsEvent(event: Events.RatingsLoaded) {
        ratingsLisr.value = event.ratings
    }

    fun onStart() {
        EventBus.getDefault().register(this)
    }

    fun onStop() {
        EventBus.getDefault().unregister(this)
    }

    fun recalcTotal() {
        var total = 0f
        for (rating in ratingItemsHolder) {
            total += rating.value
        }
        totalRatingMut.value = (total / ratingItemsHolder.size).toString()
        println(" ${total} ${totalRating.value}")

    }
}