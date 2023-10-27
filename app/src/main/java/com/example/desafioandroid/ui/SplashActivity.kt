package com.example.desafioandroid.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.desafioandroid.R
import com.example.desafioandroid.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
//        actionBar?.hide()

        supportActionBar!!.hide()

        setupSplash()
    }

    private fun setupSplash() = with(binding){

        ivLogoStefanini.alpha = 0f
        ivLogoStefanini.animate().setDuration(1200).alpha(1f)

        tvTextSplashActivity.alpha = 0f
        tvTextSplashActivity.animate().setDuration(1200).alpha(1f).withEndAction {
            val intent = Intent(this@SplashActivity, HomeActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}