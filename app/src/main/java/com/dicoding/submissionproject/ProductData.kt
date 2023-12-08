package com.dicoding.submissionproject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductData (val name: String, val description: String, val image: Int) : Parcelable
