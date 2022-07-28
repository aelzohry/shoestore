package com.udacity.aelzohry.shoestore.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Shoe(
    var name: String,
    var size: String,
    var company: String,
    var description: String
) : Parcelable
