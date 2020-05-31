package com.example.kotlinflow.base.filter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinflow.data.api.ApiHelper
import com.example.kotlinflow.data.local.DatabaseHelper
import com.example.kotlinflow.data.model.ApiUser
import com.example.kotlinflow.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class FilterViewModel(apiHelper: ApiHelper, dbHelper: DatabaseHelper) : ViewModel(){

    val status = MutableLiveData<Resource<String>>()

    fun startFilterTask(){
        viewModelScope.launch {
            status.postValue(Resource.loading(null))
            val result = mutableListOf<Int>()
            (1..5).asFlow().filter {
                it % 2 == 0
            }.toList(result)

            status.postValue(Resource.success(result.toString()))
        }

    }

    fun getStatus(): LiveData<Resource<String>> {
        return status
    }
}