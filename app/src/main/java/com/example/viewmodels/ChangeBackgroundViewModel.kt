package com.example.viewmodels

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class ChangeBackgroundViewModel: ViewModel() {

    var backgroundColor by mutableStateOf(Color.White)
        private set


    fun changeBackgroundColor(){
        backgroundColor = Color.Red
    }

    var uri: Uri? by mutableStateOf(null)
        private set

    fun updateUri(uri: Uri?){
        this.uri = uri
    }

    var counter by mutableStateOf(0)
        private set


    fun incrementCount(counter: Int){
        this.counter = counter+1
    }

}