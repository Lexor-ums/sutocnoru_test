package com.example.sutocnoru_test.presentation

import android.view.View
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


    val totalRating: LiveData<String> = totalRatingMut
    private var ratingItemsHolder = mutableMapOf<Int, Float>()

    val addButtonVisible  =  MutableLiveData(View.VISIBLE)
    val addButtonMoreVisible  =  MutableLiveData(View.GONE)
    private var photosCount = 0
    private var imageViewAdapter : ImageViewAdapter = ImageViewAdapter()

    private val urls = listOf(
        "https://domcns.ru/files/news/138/image/2250619.jpg",
        "https://homius.ru/wp-content/uploads/2018/06/1-13.jpg",
        "https://icdn.lenta.ru/images/2018/09/17/10/20180917105049245/pic_0433d1541200aad3a62964e11e43379f.jpg"
    )

    init {
        imageViewAdapter.setOnClickListener(::onRemoveButtonClicked)
    }

    fun addRatingItem(ratingBar: Int, rating: Float) {
        ratingItemsHolder[ratingBar] = rating
        recalcTotal()
    }

    fun loadJson() {
        interactor.requestRating()
    }

    fun loadImage(){
        if(photosCount < 3)
            imageViewAdapter.addItem(urls[photosCount])
        ++photosCount
        if(photosCount > 0) {
            addButtonMoreVisible.value = View.VISIBLE
            addButtonVisible.value = View.GONE
        }

    }

    fun addMoreButtonClicked(){
        if(photosCount < 3) {
            imageViewAdapter.addItem(urls[photosCount])
            ++photosCount
        }
    }

    private fun onRemoveButtonClicked(position : Int){
        imageViewAdapter.removeItem(position)
        if(photosCount > 0)
            --photosCount
        println("Photos count $photosCount")
        if(photosCount == 0) {
            println("Photos count $photosCount")
            addButtonMoreVisible.value = View.GONE
            addButtonVisible.value = View.VISIBLE
        }
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

    fun getAdapter () = imageViewAdapter
}