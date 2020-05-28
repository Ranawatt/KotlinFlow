package com.example.kotlinflow.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinflow.data.api.ApiHelper
import com.example.kotlinflow.data.local.DatabaseHelper
import com.example.kotlinflow.data.model.ApiUser
import com.example.kotlinflow.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class SeriesNetworkCallsViewModel(
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
            val allUsersFromApi = mutableListOf<ApiUser>()
            apiHelper.getUsers()
                .flatMapConcat { usersFromApi ->
                    allUsersFromApi.addAll(usersFromApi)
                    apiHelper.getMoreUsers()
                }
                .flowOn(Dispatchers.Default)
                .catch { e ->
                    users.postValue(Resource.error(e.toString(), null))
                }
//                .collect { moreUsersFromApi ->
//                    allUsersFromApi.addAll(moreUsersFromApi)
//                    users.postValue(Resource.success(allUsersFromApi))
//                }
        }
    }

    fun getUsers(): LiveData<Resource<List<ApiUser>>> {
        return users
    }

}