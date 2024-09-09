package com.swanky.contactsmvvm.framents

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.swanky.contactsmvvm.databinding.FragmentDetailsBinding
import com.swanky.contactsmvvm.viewmodels.DetailsFragmentViewModel

class DetailsFragment : Fragment() {

    private lateinit var dataBinding: FragmentDetailsBinding
    private val viewModel: DetailsFragmentViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        dataBinding = FragmentDetailsBinding.inflate(inflater, container, false)
        dataBinding.master = this

        getContactData()

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeContactData()
    }

    fun callContact(phoneNumber: String){
        viewModel.callContact(phoneNumber, requireContext())
    }


    private fun getContactData(){
        viewModel.getContactData(arguments)
    }

    private fun observeContactData(){
        viewModel.contactLiveData.observe(viewLifecycleOwner){
            dataBinding.contact = it
        }
    }

}