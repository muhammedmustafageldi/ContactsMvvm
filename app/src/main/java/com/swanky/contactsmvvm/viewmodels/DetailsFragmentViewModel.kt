package com.swanky.contactsmvvm.viewmodels

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.swanky.contactsmvvm.framents.DetailsFragmentArgs
import com.swanky.contactsmvvm.models.Contact

class DetailsFragmentViewModel: ViewModel() {

    val contactLiveData = MutableLiveData<Contact>()

    fun getContactData(arguments: Bundle?){
        arguments?.let {
            val contact = DetailsFragmentArgs.fromBundle(it).contact
            contactLiveData.value = contact
        }
    }

    fun callContact(phoneNumber: String, context: Context){
        val callIntent = Intent(Intent.ACTION_DIAL)
        callIntent.data = Uri.parse("tel:$phoneNumber")
        context.startActivity(callIntent)
    }

}