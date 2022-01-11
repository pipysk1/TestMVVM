package com.example.testmvvm.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class Quote(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String


)