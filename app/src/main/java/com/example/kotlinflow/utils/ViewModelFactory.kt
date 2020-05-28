package com.example.kotlinflow.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinflow.base.completion.CompletionViewModel
import com.example.kotlinflow.base.errorhandling.EmitAllViewModel
import com.example.kotlinflow.base.room.RoomDBViewModel
import com.example.kotlinflow.data.api.ApiHelper
import com.example.kotlinflow.data.local.DatabaseHelper
import com.example.kotlinflow.network.ParallelNetworkCallsViewModel
import com.example.kotlinflow.network.SeriesNetworkCallsViewModel
import com.example.kotlinflow.network.SingleNetworkCallViewModel

class ViewModelFactory(private val apiHelper: ApiHelper, private val dbHelper: DatabaseHelper) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SingleNetworkCallViewModel::class.java)) {
            return SingleNetworkCallViewModel(apiHelper, dbHelper) as T
        }
        if (modelClass.isAssignableFrom(SeriesNetworkCallsViewModel::class.java)) {
            return SeriesNetworkCallsViewModel(apiHelper, dbHelper) as T
        }
        if (modelClass.isAssignableFrom(ParallelNetworkCallsViewModel::class.java)) {
            return ParallelNetworkCallsViewModel(apiHelper, dbHelper) as T
        }
        if (modelClass.isAssignableFrom(RoomDBViewModel::class.java)) {
            return RoomDBViewModel(apiHelper, dbHelper) as T
        }
//        if (modelClass.isAssignableFrom(CatchViewModel::class.java)) {
//            return CatchViewModel(apiHelper, dbHelper) as T
//        }
        if (modelClass.isAssignableFrom(EmitAllViewModel::class.java)) {
            return EmitAllViewModel(apiHelper, dbHelper) as T
        }
//        if (modelClass.isAssignableFrom(LongRunningTaskViewModel::class.java)) {
//            return LongRunningTaskViewModel(apiHelper, dbHelper) as T
//        }
//        if (modelClass.isAssignableFrom(TwoLongRunningTasksViewModel::class.java)) {
//            return TwoLongRunningTasksViewModel(apiHelper, dbHelper) as T
//        }
        if (modelClass.isAssignableFrom(CompletionViewModel::class.java)) {
            return CompletionViewModel(apiHelper, dbHelper) as T
        }
//        if (modelClass.isAssignableFrom(FilterViewModel::class.java)) {
//            return FilterViewModel(apiHelper, dbHelper) as T
//        }
//        if (modelClass.isAssignableFrom(MapViewModel::class.java)) {
//            return MapViewModel(apiHelper, dbHelper) as T
//        }
//        if (modelClass.isAssignableFrom(ReduceViewModel::class.java)) {
//            return ReduceViewModel(apiHelper, dbHelper) as T
//        }
        throw IllegalArgumentException("Unknown class name")
    }

}