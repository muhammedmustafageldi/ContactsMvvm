package com.swanky.contactsmvvm.repo

import android.content.Context
import com.swanky.contactsmvvm.models.Contact
import com.swanky.contactsmvvm.roomdb.AppDatabase
import com.swanky.contactsmvvm.roomdb.MyDao
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class ContactDaoRepository(context: Context) {

    private val dao: MyDao

    init {
        val database = AppDatabase.getDatabase(context)
        dao = database.myDao()
    }

    fun getAllContracts(): Single<List<Contact>> {
        return dao.getAllContact()
    }

    fun insertContact(contact: Contact): Completable{
        return dao.insertContact(contact)
    }

}