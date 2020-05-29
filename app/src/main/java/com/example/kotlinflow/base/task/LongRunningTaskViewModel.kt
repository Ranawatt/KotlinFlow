package com.example.kotlinflow.base.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinflow.data.local.DatabaseHelper
import com.example.kotlinflow.data.model.ApiUser
import com.example.kotlinflow.utils.Resource
import com.example.kotlinflow.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class LongRunningTaskViewModel(
    private val apiUser: ApiUser,
    private val databaseHelper: DatabaseHelper): ViewModel() {

    private val status = MutableLiveData<Resource<String>>()

    fun startLongRunningTask(){
        viewModelScope.launch {
            status.postValue(Resource.loading(null))
            doLongRunningTask()
                .flowOn(Dispatchers.Default)
                .catch {
                    status.postValue(Resource.error("Something Went Wrong", null))
                }.collect {
                    status.postValue(Resource.success("Task Completed"))
                }
        }
    }

    fun getStatus() : LiveData<Resource<String>> {
        return status
    }
    private fun doLongRunningTask(): Flow<Int> {
        return flow {
            kotlinx.coroutines.delay(5000)
            emit(0)
        }
    }
}