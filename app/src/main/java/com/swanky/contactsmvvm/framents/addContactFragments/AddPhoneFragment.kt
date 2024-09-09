package com.swanky.contactsmvvm.framents.addContactFragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.swanky.contactsmvvm.R
import com.swanky.contactsmvvm.activities.AddContactActivity
import com.swanky.contactsmvvm.databinding.FragmentAddPhoneNumberBinding
import com.swanky.contactsmvvm.util.NumPadListener
import com.swanky.contactsmvvm.viewmodels.AddContactViewModel

class AddPhoneFragment : Fragment() {

    private lateinit var dataBinding: FragmentAddPhoneNumberBinding
    private val activityViewModel: AddContactViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_phone_number, container, false)
        dataBinding.master = this
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        defineNumPad()
    }

    fun setContactPhone(phoneNumber: String){
        activityViewModel.putData("phoneNumber", phoneNumber)
        // Next page
        (activity as AddContactActivity).nextPage()
    }

    private fun defineNumPad() {
        NumPadListener(
            dataBinding.numberKeyboardPhone,
            dataBinding.addNumberEditText,
            dataBinding.addPhoneButton
        ).defineNumPad()
    }

}