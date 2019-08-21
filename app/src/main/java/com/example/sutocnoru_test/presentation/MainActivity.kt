package com.example.sutocnoru_test.presentation

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.sutocnoru_test.MainApplication
import com.example.sutocnoru_test.R
import com.example.sutocnoru_test.data.net.retrofit.RatingResponse
import com.example.sutocnoru_test.databinding.ActivityMainBinding
import com.example.sutocnoru_test.utils.SELECT_PHOTO
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModel: MainActivityViewModel

    private lateinit var dataBinding: ActivityMainBinding

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        dataBinding?.lifecycleOwner = this
        dataBinding.viewmodel = viewModel
        viewModel.ratingsLisr.observe(this, Observer<List<RatingResponse>> {
            var dynamicRatings = MainActivityDynamicRatings(MainApplication.instance.applicationContext)
            for (rating in it) {
                var ratingBar = dynamicRatings.getRatingBar(MainApplication.instance.applicationContext, rating.rating)
                var textView =
                    dynamicRatings.getLabel(MainApplication.instance.applicationContext, rating.attributeName)
                findViewById<LinearLayout>(R.id.ratingsLayout).addView(
                    dynamicRatings.getRatingGroup(
                        MainApplication.instance.applicationContext, ratingBar, textView
                    )
                )
                viewModel.addRatingItem(ratingBar.hashCode(), rating.rating.toFloat())
                ratingBar.onRatingBarChangeListener =
                    RatingBar.OnRatingBarChangeListener { ratingBar, rating, _ ->
                        viewModel.addRatingItem(
                            ratingBar.hashCode(),
                            rating
                        )
                    }
            }
        })
        viewModel.totalRating.observe(this, Observer<String> {
            findViewById<TextView>(R.id.totalMark).text = it
        })
        findViewById<ImageButton>(R.id.buttonAdd).setOnClickListener {
//            val intent = Intent(Intent.ACTION_PICK)
//            intent.type = "image/*"
//            startActivityForResult(intent, SELECT_PHOTO)
            println("load image")
            viewModel.loadImage()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, imageReturnedIntent: Intent?) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent)

        when (requestCode) {
            SELECT_PHOTO -> if (resultCode == Activity.RESULT_OK) {
                val selectedImage = imageReturnedIntent!!.data
            }
        }
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
