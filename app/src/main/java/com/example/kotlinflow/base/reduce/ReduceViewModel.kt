package com.example.kotlinflow.base.reduce

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinflow.data.api.ApiHelper
import com.example.kotlinflow.data.local.DatabaseHelper
import com.example.kotlinflow.utils.Resource
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.reduce
import kotlinx.coroutines.launch

class ReduceViewModel(apiHelper: ApiHelper, dbHelper: DatabaseHelper): ViewModel() {

    private val status = MutableLiveData<Resource<String>>()

    fun startReduceTask(){
        viewModelScope.launch {
            status.postValue(Resource.loading(null))
            val result = (1..5).asFlow().reduce { a, b -> a + b }
            status.postValue(Resource.success(result.toString()))
        }
    }
    fun getStatus(): LiveData<Resource<String>> {
        return status
    }
}