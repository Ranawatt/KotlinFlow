package com.example.kotlinflow.base.reduce

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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

class ReduceActivity : AppCompatActivity() {
    private lateinit var viewModel: ReduceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_long_running_task)
        setupViewModel()
        setupObserver()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, ViewModelFactory(
            ApiHelperImpl(RetrofitBuilder.apiService),
            DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext))
        )).get(ReduceViewModel::class.java)
    }

    private fun setupObserver() {
        viewModel.getStatus().observe(this, Observer {
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
                    Toast.makeText(this, it.message,Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}