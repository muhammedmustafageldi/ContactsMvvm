package com.swanky.contactsmvvm.framents

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.swanky.contactsmvvm.R
import com.swanky.contactsmvvm.adapters.ContactsAdapter
import com.swanky.contactsmvvm.databinding.FragmentHomePageBinding
import com.swanky.contactsmvvm.viewmodels.HomeFragmentViewModel


class HomePageFragment : Fragment() {

    private lateinit var dataBinding: FragmentHomePageBinding
    private val viewModel: HomeFragmentViewModel by viewModels()
    private val contactsAdapter = ContactsAdapter(arrayListOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, container, false)
        dataBinding.master = this
        dataBinding.lifecycleOwner = this
        dataBinding.viewModel = viewModel

        // Set tool bar title
        dataBinding.toolBarTitle = getString(R.string.contacts)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadData()
        dataBinding.adapter = contactsAdapter
        observeLiveData()
    }

    fun addContact(view: View) {
        // Go to add contact activity
        Navigation.findNavController(view).navigate(R.id.action_homePageFragment_to_addContactActivity)
    }

    fun tryAgain() {
        viewModel.loadData()
    }

    private fun observeLiveData() {
        viewModel.contactsList.observe(viewLifecycleOwner) { contacts ->
            contacts?.let {
                contactsAdapter.updateContactsList(newContactsList = contacts)
            }

            viewModel.contactsError.observe(viewLifecycleOwner) { error ->
                error?.let {
                    if (error) {
                        dataBinding.errorLayout.visibility = View.VISIBLE
                        dataBinding.homePageRecycler.visibility = View.GONE
                    } else {
                        dataBinding.errorLayout.visibility = View.GONE
                        dataBinding.homePageRecycler.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadData()
    }

}