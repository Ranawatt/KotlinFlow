package com.example.kotlinflow.data.api

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override fun getUsers() = flow { emit(apiService.getUsers()) }

    override fun getMoreUsers() = flow { emit(apiService.getMoreUsers()) }

    override fun getUsersWithError() = flow { emit(apiService.getUsersWithError()) }

}