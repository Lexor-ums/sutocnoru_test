package com.example.calculator.utils.events

import com.example.sutocnoru_test.data.net.retrofit.RatingResponse

sealed class Events {
    class RatingsLoaded(val ratings : List<RatingResponse>?) : Events()
}