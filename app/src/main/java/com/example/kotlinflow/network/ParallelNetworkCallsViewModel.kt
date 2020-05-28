package com.example.kotlinflow.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ParallelNetworkCallsViewModel(
    private val apiHelper: ApiHelper,
    private val dbHelper: DatabaseHelper
) : ViewModel() {

    private val users = MutableLiveData<Resource<List<ApiUser>>>()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            users.postValue(Resource.loading(null))
            apiHelper.getUsers()
                .zip(apiHelper.getMoreUsers()) { usersFromApi, moreUsersFromApi ->
                    val allUsersFromApi = mutableListOf<ApiUser>()
                    allUsersFromApi.addAll(usersFromApi)
                    allUsersFromApi.addAll(moreUsersFromApi)
                    return@zip allUsersFromApi
                }
                .flowOn(Dispatchers.Default)
                .catch { e ->
                    users.postValue(Resource.error(e.toString(), null))
                }
                .collect {
                    users.postValue(Resource.success(it))
                }
        }
    }

    fun getUsers(): LiveData<Resource<List<ApiUser>>> {
        return users
    }

}