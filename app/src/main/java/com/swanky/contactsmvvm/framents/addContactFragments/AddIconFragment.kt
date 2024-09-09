package com.swanky.contactsmvvm.framents.addContactFragments

import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.swanky.contactsmvvm.R
import com.swanky.contactsmvvm.activities.AddContactActivity
import com.swanky.contactsmvvm.databinding.FragmentAddIconBinding
import com.swanky.contactsmvvm.viewmodels.AddContactViewModel
import com.swanky.contactsmvvm.viewmodels.AddIconViewModel


class AddIconFragment : Fragment() {

    private lateinit var dataBinding: FragmentAddIconBinding
    private val activityViewModel: AddContactViewModel by activityViewModels()
    private val fragmentViewModel: AddIconViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_icon, container, false)
        dataBinding.master = this
        dataBinding.lifecycleOwner = this
        dataBinding.viewModel = fragmentViewModel

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bigIconObserver()
        // Set the default icon
        fragmentViewModel.selectedIcon.value = fragmentViewModel.getIconDrawable(dataBinding.radioButton1.id)

        checkRadioButtons(defineRadioButtons())
    }

    fun sendIconToActivity(icon: Int){
        activityViewModel.putData("contactIcon", icon)
        // Complete save transaction
        (activity as AddContactActivity).completeSave()
    }

    private fun defineRadioButtons(): ArrayList<RadioButton>{
        return arrayListOf(
            dataBinding.radioButton1,
            dataBinding.radioButton2,
            dataBinding.radioButton3,
            dataBinding.radioButton4,
            dataBinding.radioButton5,
            dataBinding.radioButton6,
            dataBinding.radioButton7,
            dataBinding.radioButton8,
            dataBinding.radioButton9,
        )
    }

    private fun checkRadioButtons(radioButtons: ArrayList<RadioButton>){
        for(radioButton in radioButtons){
            radioButton.setOnClickListener {
                // Tick the selected Button
                for (button in radioButtons){
                    button.isChecked = false
                    button.background = ContextCompat.getDrawable(requireContext(), fragmentViewModel.getIconDrawable(button.id))
                }
                radioButton.isChecked = true

                // Add a frame to the selected button and update the background
                val iconId = fragmentViewModel.getIconDrawable(radioButton.id)
                val selectedIcon = ContextCompat.getDrawable(requireContext(), iconId)
                radioButton.background  = LayerDrawable(arrayOf(
                    ContextCompat.getDrawable(requireContext(), R.drawable.custom_radio_selected),
                    selectedIcon))
                dataBinding.buttonAddIcon.isEnabled = true

                // Update the selected icon
                fragmentViewModel.selectedIcon.value = iconId
            }
        }
    }

    private fun bigIconObserver(){
        fragmentViewModel.selectedIcon.observe(viewLifecycleOwner){
            it?.let {
                dataBinding.selectedIcon = it
            }
        }
    }

}