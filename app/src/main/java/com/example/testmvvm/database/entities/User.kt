package com.example.testmvvm.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


const val CURRENT_USER_ID = 0

@Entity
data class User(
    var id: Int? = null,
    var email: String? = null,
    var first_name: String? = null,
    var last_name: String? = null,
    var avatar: String? = null
) {
    @PrimaryKey(autoGenerate = false)
    var uid: Int = CURRENT_USER_ID
}