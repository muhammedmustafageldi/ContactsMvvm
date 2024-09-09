package com.swanky.contactsmvvm.viewmodels

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.swanky.contactsmvvm.models.Contact
import com.swanky.contactsmvvm.repo.ContactDaoRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeFragmentViewModel(application: Application) : BaseViewModel(application) {

    private var compositeDisposable = CompositeDisposable()
    private var repository = ContactDaoRepository(application)

    val contactsList = MutableLiveData<ArrayList<Contact>>()
    val contactsError = MutableLiveData<Boolean>()

    fun loadData(){
        compositeDisposable.add(repository.getAllContracts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    // Success
                    contactsList.value = it as ArrayList<Contact>
                    contactsError.value = false
                },
                {
                    // Error
                    contactsError.value = true
                }
            ))
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}