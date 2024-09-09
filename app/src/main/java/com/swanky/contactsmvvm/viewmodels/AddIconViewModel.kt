package com.swanky.contactsmvvm.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.swanky.contactsmvvm.R

class AddIconViewModel : ViewModel() {

    var selectedIcon = MutableLiveData<Int>()

    fun getIconDrawable(buttonId: Int): Int{
        return when(buttonId){
            R.id.radioButton1 -> R.drawable.man1
            R.id.radioButton2 -> R.drawable.woman1
            R.id.radioButton3 -> R.drawable.man2
            R.id.radioButton4 -> R.drawable.woman2
            R.id.radioButton5 -> R.drawable.man3
            R.id.radioButton6 -> R.drawable.woman3
            R.id.radioButton7 -> R.drawable.man4
            R.id.radioButton8 -> R.drawable.woman4
            R.id.radioButton9 -> R.drawable.woman5
            else -> R.drawable.error
        }
    }

}