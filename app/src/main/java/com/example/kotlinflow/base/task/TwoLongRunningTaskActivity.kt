package com.example.kotlinflow.base.task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.kotlinflow.R
import com.example.kotlinflow.data.api.ApiHelper
import com.example.kotlinflow.data.api.ApiHelperImpl
import com.example.kotlinflow.data.api.RetrofitBuilder
import com.example.kotlinflow.data.local.DatabaseBuilder
import com.example.kotlinflow.data.local.DatabaseHelperImpl
import com.example.kotlinflow.utils.Status
import com.example.kotlinflow.utils.ViewModelFactory
import kotlinx.android.synthetic.main.activity_two_long_running_task.*

class TwoLongRunningTaskActivity : AppCompatActivity() {

    private lateinit var viewmodel: TwoLongRunningTaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two_long_running_task)
        setupViewModel()
        setupObserver()
    }

    private fun setupViewModel() {
        viewmodel = ViewModelProviders.of(this, ViewModelFactory(
            ApiHelperImpl(RetrofitBuilder.apiService),
            DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext))
        )).get(TwoLongRunningTaskViewModel::class.java)
    }

    private fun setupObserver() {
        viewmodel.getStatus().observe(this, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    textView.text = it.data
                    textView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    textView.visibility = View.GONE
                }
                Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message , Toast.LENGTH_LONG).show()
                }
            }
        })
        viewmodel.startLongRunningTask()
    }
}
