package com.example.kotlinflow.base.completion

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CompletionViewModel(
    private val apiHelper: ApiHelper,
    private val dbHelper: DatabaseHelper
) : ViewModel() {

    private val status = MutableLiveData<Resource<String>>()

    init{
        fetchUsers()
    }
    private fun fetchUsers() {
        viewModelScope.launch {
            status.postValue(Resource.loading(null))
            apiHelper.getUsers()
                .catch { e ->
                    status.postValue(Resource.error(e.toString(), null))
                }
                .onCompletion {
                    status.postValue(Resource.success("Task Completed"))
                }
                .collect {
                }
        }
    }

    fun getStatus(): LiveData<Resource<String>> {
        return status
    }
}