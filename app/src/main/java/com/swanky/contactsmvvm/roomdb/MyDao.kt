package com.swanky.contactsmvvm.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.swanky.contactsmvvm.models.Contact
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface MyDao {

    // Queries
    @Query("SELECT * FROM Contacts")
    fun getAllContact(): Single<List<Contact>>

    // INSERT TRANSACTIONS
    @Insert
    fun insertContact(contact: Contact): Completable

    // DELETE TRANSACTIONS
    @Delete
    fun deleteContact(contact: Contact): Completable

}