package com.example.mywallet

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class InOutLogViewModel(applicaton: Application) :
    AndroidViewModel(application){
        //ViewModel to maintain a reference to the respository
        private val repository: InOutLogRespository

    val allLogs : LiveData<List<InOutLog>>

    init{
        //Get reference to DAO
        val inOutLogDao = InOutLogDatabase
            .getDatabase(applicaton)
            .inOutLogDao()

        repository = InOutLogRespository(inOutLogDao)
        allLogs = repository.allLogs
    }

    //ViewModel to use coroutine to perform long process
    fun insertLog(inOutLog: InOutLog) = viewModelScope.launch{
        repository.insertLog(inOutLog)
    }
    }



