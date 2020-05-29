package com.example.kotlinflow.base.task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.kotlinflow.R
import com.example.kotlinflow.data.api.ApiHelperImpl
import com.example.kotlinflow.data.api.RetrofitBuilder
import com.example.kotlinflow.data.local.DatabaseBuilder
import com.example.kotlinflow.data.local.DatabaseHelperImpl
import com.example.kotlinflow.utils.Status
import com.example.kotlinflow.utils.ViewModelFactory
import kotlinx.android.synthetic.main.activity_long_running_task.*


class LongRunningTaskActivity : AppCompatActivity() {

    private lateinit var viewmodel: LongRunningTaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_long_running_task)
        setupViewModel()
        setupLongRunningTask()
    }

    private fun setupViewModel() {
        viewmodel = ViewModelProviders.of(this, ViewModelFactory(
            ApiHelperImpl(RetrofitBuilder.apiService),
            DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext))
        )).get(LongRunningTaskViewModel::class.java)
    }

    private fun setupLongRunningTask() {
        viewmodel.getStatus().observe(this, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    textView.text = it.data
                    textView.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this,it.message,Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    textView.visibility = View.GONE
                }
            }
        })
        viewmodel.startLongRunningTask()
    }
}
