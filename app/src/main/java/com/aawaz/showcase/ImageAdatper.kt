package com.aawaz.showcase

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class ImageAdatper(val context: Context, val images: Array<Int>) : RecyclerView.Adapter<ImageAdatper.VH>() {
    var imageClickListener: ImageClickListener? = null
    var selected = 0

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): VH {
        return VH(LayoutInflater.from(context).inflate(R.layout.image_tile, p0, false))
    }

    override fun getItemCount(): Int {
        return images.size
    }

    fun setSelectedImage(index:Int){
        val old = selected
        selected = index
        notifyItemChanged(selected)
        notifyItemChanged(old)

    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.image_view.setImageResource(images[position])
        holder.count.text = (position+1).toString()
        if(selected == position)
            holder.core.setBackgroundColor(ContextCompat.getColor(context, R.color.primaryTransp))
        else
            holder.core.setBackgroundColor(ContextCompat.getColor(context, R.color.white))


        holder.image_view.setOnClickListener {
            if (imageClickListener != null)
                imageClickListener!!.onImageClicked(position, images[position])
        }
        holder.image_view.setOnLongClickListener {
            if (imageClickListener != null)
                imageClickListener!!.onImageLongClicked(images[position])
            true
        }
    }


    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image_view: ImageView = itemView.findViewById(R.id.image_view)
        val count: TextView = itemView.findViewById(R.id.count)
        val core = itemView
    }

    interface ImageClickListener {
        fun onImageClicked(position: Int, image_id: Int)
        fun onImageLongClicked(image_id: Int)
    }
}