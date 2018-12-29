package com.aawaz.showcase

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash)

        ortho.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java).putExtra("type", "ortho"))
        }

        gyno.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java).putExtra("type", "gyno"))
        }

        derma.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java).putExtra("type", "derma"))
        }

        gp.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java).putExtra("type", "gp"))
        }

    }
}
