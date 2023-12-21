package com.example.task_2012.features.productlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    var showProductObserve = MutableLiveData<Boolean>()

    fun onProductScreen() {
        showProductObserve.postValue(true)
    }

}