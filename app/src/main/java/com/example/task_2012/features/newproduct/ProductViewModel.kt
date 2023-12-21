package com.example.task_2012.features.newproduct

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task_2012.features.Product

class ProductViewModel : ViewModel() {

     val prdId = MutableLiveData<String>()
     val prdName = MutableLiveData<String>()
     val prdDescription = MutableLiveData<String>()

     val errorMessage = MutableLiveData<String>()
     val addProductObserve = MutableLiveData<Boolean>()
     val getProduct = MutableLiveData<Product>()



     fun onAddProduct() {

          if (prdId.value.isNullOrEmpty()
               || prdName.value.isNullOrEmpty()
               || prdDescription.value.isNullOrEmpty()) {

               errorMessage.postValue("Please fill out the form")
               return
          }

          getProduct.postValue(
               Product(id = "${prdId.value}",
                    name = "${prdName.value}",
                    description = "${prdDescription.value}")
          )

          addProductObserve.postValue(true)

     }

}