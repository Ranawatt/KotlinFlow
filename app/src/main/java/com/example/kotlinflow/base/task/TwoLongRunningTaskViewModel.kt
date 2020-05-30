package com.example.kotlinflow.base.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinflow.data.api.ApiHelper
import com.example.kotlinflow.data.local.DatabaseHelper
import com.example.kotlinflow.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TwoLongRunningTaskViewModel(
    private val apiHelper: ApiHelper,
    private val dbHelper: DatabaseHelper
): ViewModel() {

    private val status = MutableLiveData<Resource<String>>()

    fun startLongRunningTask() {
        viewModelScope.launch {
            status.postValue(Resource.loading(null))
            doLongRunningTaskOne().zip(doLongRunningTaskTwo()) {
                resultOne, resultTwo -> return@zip resultOne + resultTwo
            }.flowOn(Dispatchers.Default)
                .catch { e ->
                    status.postValue(Resource.error(e.toString(), null))
                }.collect {
                    status.postValue(Resource.success(it))
                }
        }
    }

    private fun doLongRunningTaskTwo(): Flow<String> {
        return flow {
            kotlinx.coroutines.delay(5000)
            emit("Two")
        }
    }

    private fun doLongRunningTaskOne(): Flow<String> {
        return flow {
            kotlinx.coroutines.delay(3000)
            emit("One")
        }
    }

    fun getStatus() : LiveData<Resource<String>> {
        return  status
    }
}