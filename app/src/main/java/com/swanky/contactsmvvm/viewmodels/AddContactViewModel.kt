package com.swanky.contactsmvvm.viewmodels

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.swanky.contactsmvvm.framents.addContactFragments.AddIconFragment
import com.swanky.contactsmvvm.framents.addContactFragments.AddNameFragment
import com.swanky.contactsmvvm.framents.addContactFragments.AddPhoneFragment
import com.swanky.contactsmvvm.models.Contact
import com.swanky.contactsmvvm.repo.ContactDaoRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class AddContactViewModel(application: Application): BaseViewModel(application) {

    private val repository = ContactDaoRepository(application)
    private val addContactDataSet = MutableLiveData<MutableMap<String, Any>>()
    private val compositeDisposable = CompositeDisposable()

    fun getViewPagerFragments(): List<Fragment> {
        return listOf(
            AddPhoneFragment(),
            AddNameFragment(),
            AddIconFragment()
        )
    }

    fun putData(key: String, value: Any) {
        val currentMap = addContactDataSet.value ?: mutableMapOf()
        currentMap[key] = value
        addContactDataSet.value = currentMap
    }

    fun saveContactToDatabase() {
        // Save data to database
        val contactData = addContactDataSet.value
        contactData?.let {
            val contact = Contact(
                personName = it["contactName"] as String,
                phoneNumber = it["phoneNumber"] as String,
                personIcon = it["contactIcon"] as Int
            )
            // Insert data with repo
            compositeDisposable.add(repository.insertContact(contact)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

}