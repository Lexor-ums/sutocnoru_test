package com.example.sutocnoru_test.presentation

import android.content.Context
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import com.example.sutocnoru_test.R

class MainActivityDynamicRatings (var context : Context){

    fun getRatingGroup(context : Context, ratingBar : RatingBar, textView: TextView ) : LinearLayout{
        val groupLayout = LinearLayout(context)
        val groupLayoutParams =  LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        groupLayout.layoutParams = groupLayoutParams
        groupLayout.orientation = LinearLayout.HORIZONTAL
        val ratingLayout = LinearLayout(context)
        val ratingLayoutParams =  LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        ratingLayout.layoutParams = ratingLayoutParams
        ratingLayout.addView(ratingBar)
        groupLayout.addView(textView)
        groupLayout.addView(ratingLayout)
        return groupLayout
    }
    fun getLabel(context : Context, text : String) : TextView{
        val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val textView = TextView(context)
        layoutParams.weight = 1F
        layoutParams.gravity = Gravity.CENTER
        textView.layoutParams = layoutParams
        textView.textSize = 16F
        textView.text = text
        return textView
    }
    fun getRatingBar(context: Context, rating: String) : RatingBar{
        val theme = ContextThemeWrapper(context, R.style.RatingBar)
        val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        val ratingBar = RatingBar(theme)
        ratingBar.layoutParams = layoutParams
        ratingBar.rating = rating.toFloat()
        ratingBar.stepSize = 1F
        return ratingBar
    }
}