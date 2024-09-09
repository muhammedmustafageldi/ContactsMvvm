package com.swanky.contactsmvvm.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "Contacts")
class Contact (
    var personName: String,
    var phoneNumber: String,
    var personIcon: Int
): Serializable {
    @PrimaryKey(autoGenerate = true)
    var personId: Int = 0
}