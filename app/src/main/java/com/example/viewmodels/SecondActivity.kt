package com.example.viewmodels

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp



class SecondActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShowText()
        }

    }
}

@Composable
fun ShowText() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Welcome to second page of this activity",
            modifier = Modifier.align(Alignment.Center),
            fontSize = 20.sp
        )
    }
}