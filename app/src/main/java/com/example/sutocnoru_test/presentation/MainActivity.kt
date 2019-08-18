package com.example.sutocnoru_test.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.sutocnoru_test.MainApplication
import com.example.sutocnoru_test.R
import com.example.sutocnoru_test.data.net.retrofit.RatingResponse
import com.example.sutocnoru_test.databinding.ActivityMainBinding
import com.example.sutocnoru_test.presentation.base.BaseActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModel : MainActivityViewModel

    private lateinit var dataBinding: ActivityMainBinding

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        dataBinding.viewmodel = viewModel
        viewModel.ratingsLisr.observe(this, Observer<List<RatingResponse>> {
            var dynamicRatings = MainActivityDynamicRatings(MainApplication.instance.applicationContext)
            for (rating in it) {
                var ratingBar = dynamicRatings.getRatingBar(MainApplication.instance.applicationContext, rating.rating)
                var textView = dynamicRatings.getLabel(MainApplication.instance.applicationContext, rating.attributeName)
                findViewById<LinearLayout>(R.id.ratingsLayout).addView(
                    dynamicRatings.getRatingGroup(
                        MainApplication.instance.applicationContext, ratingBar, textView)
                )
                viewModel.addRatingItem(ratingBar.hashCode(), rating.rating.toFloat())
                ratingBar.onRatingBarChangeListener =
                    RatingBar.OnRatingBarChangeListener { ratingBar, rating, _ -> viewModel.addRatingItem(ratingBar.hashCode(), rating) }
            }
        })
        viewModel.totalRating.observe(this, Observer<String> {
            findViewById<TextView>(R.id.totalMark).text = it
        })
    }



    override fun onStart() {
        super.onStart()
        viewModel.onStart()
        viewModel.loadJson()
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }
}
