package com.example.testmvvm.data.respositoris

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testmvvm.data.network.CallAPI
import com.example.testmvvm.data.network.responses.SafeApiRequest
import com.example.testmvvm.data.preferences.PreferenceProvider
import com.example.testmvvm.database.AppDatabase
import com.example.testmvvm.database.entities.Quote
import com.example.testmvvm.ulti.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

private val MININUM_INTERAL = 6

class QuotesRespositoris(
    private val api: CallAPI,
    private val db: AppDatabase,
    private val prefs: PreferenceProvider

) : SafeApiRequest() {
    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.observeForever {
            saveQuotes(it)
        }
    }

    suspend fun getQuotes(): LiveData<List<Quote>> {
        return withContext(Dispatchers.IO) {
            fetchQuotes()
            db.getQuotesDao().getQuotes()
        }
    }

    private suspend fun fetchQuotes() {
        val lastSaveAt = prefs.getLastSaveAt()
        if (lastSaveAt == null || isFetchNeeded(LocalDateTime.parse(lastSaveAt))) {
            try {
                val response = apiRequest { api.getQuotes() }
                quotes.postValue(response.data)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

    }

    private fun isFetchNeeded(saveAt: LocalDateTime): Boolean {
        return ChronoUnit.HOURS.between(saveAt, LocalDateTime.now()) > MININUM_INTERAL
    }

    private fun saveQuotes(quotes: List<Quote>) {
        Coroutines.io {
            prefs.savelastSaveAt(LocalDateTime.now().toString())
            db.getQuotesDao().saveAllQuotes(quotes)
        }
    }
}