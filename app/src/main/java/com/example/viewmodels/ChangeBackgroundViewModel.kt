package com.example.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class ChangeBackgroundViewModel: ViewModel() {

    var itemList = mutableStateListOf("Siddhant", "Nikhil", "Adarsh", "Sahil")
        private set


    fun addItemToList(item: String){
        this.itemList.add(item)
    }

    fun removeItemFromList(item: String){
        this.itemList.remove(item)
    }

}