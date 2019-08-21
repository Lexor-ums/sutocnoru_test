package com.example.sutocnoru_test.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.sutocnoru_test.MainApplication
import com.example.sutocnoru_test.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import javax.inject.Inject

class ImageViewAdapter : RecyclerView.Adapter<ImageViewAdapter.ViewHolder>() {

    @Inject
    lateinit var picasso: Picasso

    private var clickListener: (Int) -> Unit = { }

    fun setOnClickListener(listener: (Int) -> Unit) {
        clickListener = listener
    }

    var imagesList = mutableListOf<String>()

    init {
        MainApplication.instance.getAppComponent()?.inject(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.photo_item, parent, false)
        return ViewHolder(view, picasso, clickListener)
    }

    override fun getItemCount(): Int {
        return imagesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.loadImage(imagesList[position])
    }

    fun addItem(path: String) {
        imagesList.add(path)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        imagesList.removeAt(position)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, private val picasso: Picasso, private val clickListener: (Int) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)
        val buttonRemove = itemView.findViewById<ImageButton>(R.id.buttonRemove)

        init {
            buttonRemove.setOnClickListener {
                clickListener.invoke(adapterPosition)
            }
        }

        fun loadImage(path: String) {
            println("picasso in action")
            picasso
                .load(path).fit()
                .into(imageView, object : Callback {
                    override fun onSuccess() {
                        println("success")
                    }

                    override fun onError(e: Exception?) {
                        println("error ${e.toString()}")
                    }

                })
            println("picasso in action finished")
        }

    }

}