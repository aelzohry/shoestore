package com.udacity.aelzohry.shoestore.extensions

import android.view.View
import androidx.databinding.BindingAdapter

// make android:visibility attribute accept boolean values
@BindingAdapter("android:visibility")
fun setVisibility(view: View, value: Boolean) {
    view.visibility = if (value) View.VISIBLE else View.GONE
}