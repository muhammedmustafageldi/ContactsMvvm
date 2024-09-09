package com.swanky.contactsmvvm.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.swanky.contactsmvvm.R
import com.swanky.contactsmvvm.databinding.RecyclerRowBinding
import com.swanky.contactsmvvm.framents.HomePageFragmentDirections
import com.swanky.contactsmvvm.models.Contact

class ContactsAdapter(private val contactsList: ArrayList<Contact>): RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    class ViewHolder(val binding: RecyclerRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: RecyclerRowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.recycler_row, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentContact = contactsList[position]
        holder.binding.contact = currentContact

        holder.binding.root.setOnClickListener {
            // Handle click
            val action = HomePageFragmentDirections.actionHomePageFragmentToDetailsFragment(currentContact)
            Navigation.findNavController(it).navigate(action)
        }

        holder.binding.rowCallButton.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:${currentContact.phoneNumber}")
            holder.itemView.context.startActivity(callIntent)
        }
    }

    fun updateContactsList(newContactsList: List<Contact>) {
        contactsList.clear()
        contactsList.addAll(newContactsList)
        notifyDataSetChanged()
    }

}