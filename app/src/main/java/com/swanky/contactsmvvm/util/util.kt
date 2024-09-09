package com.swanky.contactsmvvm.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText

@BindingAdapter("app:srcCompat")
fun setSrcCompat(imageView: ImageView, resourceId: Int) {
    imageView.setImageResource(resourceId)
}

@BindingAdapter("app:setErrorMessage")
fun setErrorMessage(editText: TextInputEditText, errorMessage: String?) {
    editText.error = errorMessage
}