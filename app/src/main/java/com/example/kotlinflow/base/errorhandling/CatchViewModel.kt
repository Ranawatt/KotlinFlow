package com.example.kotlinflow.base.errorhandling

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinflow.data.api.ApiHelper
import com.example.kotlinflow.data.local.DatabaseHelper
import com.example.kotlinflow.data.model.ApiUser

import com.example.kotlinflow.utils.Resource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CatchViewModel(
    private val apiHelper: ApiHelper,
    private val dbHelper: DatabaseHelper)
    :ViewModel() {

    private val users = MutableLiveData<Resource<List<ApiUser>>>()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            users.postValue(Resource.loading(null))
            apiHelper.getUsersWithError().catch { e ->
                users.postValue(Resource.error(e.toString(),null))
            }.collect {
                users.postValue(Resource.success(it))
            }

        }
    }

    fun getUsers(): LiveData<Resource<List<ApiUser>>> {
        return users
    }
}