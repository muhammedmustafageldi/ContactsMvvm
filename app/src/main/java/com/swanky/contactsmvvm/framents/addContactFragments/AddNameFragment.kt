package com.swanky.contactsmvvm.framents.addContactFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.swanky.contactsmvvm.R
import com.swanky.contactsmvvm.activities.AddContactActivity
import com.swanky.contactsmvvm.databinding.FragmentAddNameBinding
import com.swanky.contactsmvvm.viewmodels.AddContactViewModel

class AddNameFragment : Fragment() {

    private lateinit var dataBinding: FragmentAddNameBinding
    private val activityViewModel: AddContactViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_name, container, false)
        dataBinding.addNameFragment = this
        return dataBinding.root
    }

    fun setContactName(name: String) {
        if (name.isNotBlank()){
            // Set name
            activityViewModel.putData("contactName", name)
            // Go to next page
            (activity as AddContactActivity).nextPage()
        }else{
            dataBinding.errorMessage = "Please enter a valid value."
        }
    }

}