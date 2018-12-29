package com.aawaz.showcase

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PopupMenu
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*
import me.kaelaela.verticalviewpager.transforms.DefaultTransformer


class MainActivity : AppCompatActivity() {
    var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main)
        val types = arrayOf("Ortho", "Gyne", "Derma", "GP")

        val ortho = arrayOf(R.drawable.ortho1, R.drawable.ortho1a, R.drawable.ortho2, R.drawable.ortho2b, R.drawable.ortho3, R.drawable.ortho3a,
                R.drawable.ortho4, R.drawable.ortho4a, R.drawable.ortho5, R.drawable.ortho5a, R.drawable.ortho5b, R.drawable.ortho5c)

        val derma = arrayOf(R.drawable.derma1, R.drawable.derma1a, R.drawable.derma3, R.drawable.derma3a)

        val gp = arrayOf(R.drawable.gp1, R.drawable.gp1a)

        val gyno = arrayOf(R.drawable.gyne1, R.drawable.gyne1a, R.drawable.gyne2, R.drawable.gyne2a, R.drawable.gyne2b, R.drawable.gyne2c, R.drawable.gyne3, R.drawable.gyne3a)

        setAdatper(ortho, "Ortho")

        imagesTile.layoutManager = LinearLayoutManager(this)


        val menu = PopupMenu(this, titleView)
        menu.inflate(R.menu.menu_items)
        when (intent.getStringExtra("type")) {
            "ortho" -> setAdatper(ortho, "Ortho")
            "gyno" -> setAdatper(gyno, "Gyno")
            "derma" -> setAdatper(derma, "Derma")
            "gp" -> setAdatper(gp, "Gp")
        }

        titleView.setOnTouchListener(object : OnSwipeTouchListener(this) {

            override fun onClick() {
                super.onClick()
                if(imagesTile.visibility ==View.GONE)
                    imagesTile.visibility =View.VISIBLE
                else
                    imagesTile.visibility =View.GONE

            }
        })
    }

    fun setAdatper(images: Array<Int>, title: String) {

        val adapter = ImageAdatper(this, images)

        titleView.text = "$title\n1 of ${images.size}"

        adapter.imageClickListener = object : ImageAdatper.ImageClickListener {
            override fun onImageClicked(position: Int, image_id: Int) {
                imageViewPager.currentItem = position
            }

            override fun onImageLongClicked(image_id: Int) {

            }

        }
        imagesTile.adapter = adapter


        imageViewPager.adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {
            override fun getItem(p0: Int): Fragment {
                return ImageFragment.getInstance(images[p0])
            }

            override fun getCount(): Int {
                return images.size
            }

        }


        imageViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {

            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(p0: Int) {
                titleView.text = "$title\n${p0 + 1} of ${images.size}"
                adapter.setSelectedImage(p0)
                imagesTile.scrollToPosition(p0)
            }

        })
    }
}
