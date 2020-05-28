package com.example.kotlinflow.base.errorhandling

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinflow.data.api.ApiHelper
import com.example.kotlinflow.data.local.DatabaseHelper
import com.example.kotlinflow.data.model.ApiUser
import com.example.kotlinflow.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class EmitAllViewModel(
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
                .zip(
                    apiHelper.getUsersWithError()
                        .catch { emitAll(flowOf(emptyList())) }) { usersFromApi, moreUsersFromApi ->
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