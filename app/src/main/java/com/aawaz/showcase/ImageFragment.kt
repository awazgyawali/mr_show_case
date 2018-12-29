package com.aawaz.showcase

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView


class ImageFragment : Fragment() {

    companion object {
        fun getInstance(image_id: Int): ImageFragment {
            val imageFragment = ImageFragment()
            val args = Bundle()
            args.putInt("image_id", image_id)
            imageFragment.arguments = args
            return imageFragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageView = view.findViewById(R.id.imageView) as ImageView

        imageView.setImageResource(arguments!!.getInt("image_id"))
//        imageView.setOnClickListener {
//            startActivity(Intent(context,FullImageActivity::class.java).putExtra("image_id",arguments!!.getInt("image_id")))
//        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image, container, false)
    }

}
