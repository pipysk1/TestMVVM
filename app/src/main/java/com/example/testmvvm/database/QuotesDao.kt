package com.example.testmvvm.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testmvvm.database.entities.Quote

@Dao
interface QuotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllQuotes(data:List<Quote>)

    @Query("SELECT * FROM Quote")
    fun getQuotes():LiveData<List<Quote>>


}