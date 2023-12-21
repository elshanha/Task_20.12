package com.example.task_2012.features

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product (
    val id : String,
    val name : String,
    val description : String
) : Parcelable {

}
