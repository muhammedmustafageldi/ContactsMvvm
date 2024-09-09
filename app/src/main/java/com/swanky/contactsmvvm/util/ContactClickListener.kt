package com.swanky.contactsmvvm.util

import android.view.View
import com.swanky.contactsmvvm.models.Contact

interface ContactClickListener {
    fun onContactClicked(view: View, contact: Contact)
}