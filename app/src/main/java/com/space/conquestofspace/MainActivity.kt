package com.space.conquestofspace

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.space.conquestofspace.presentation.ConquestOfSpaceApp
import com.space.conquestofspace.presentation.ui.theme.ConquestOfSpaceAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConquestOfSpaceAppTheme {
                ConquestOfSpaceApp()
            }
        }
    }
}
