package com.example.desafioandroid.ui.composable

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.desafioandroid.ui.SplashActivity
import com.example.desafioandroid.ui.composable.theme.Black
import com.example.desafioandroid.ui.composable.theme.DesafioAndroidTheme
import java.util.Timer
import kotlin.concurrent.timerTask

class LoadingComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DesafioAndroidTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Black)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        LoadingAnimation()
                    }
                }
            }
        }

        startSplashActivity()
    }

    private fun startSplashActivity() {
        val intent = Intent(this, SplashActivity::class.java)

        val task = timerTask {
            startActivity(intent)
            finish()
        }

        Timer().schedule(task, 1800)
    }
}

