package com.example.kotlinflow.base.reduce

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinflow.R

class ReduceActivity : AppCompatActivity() {
    private lateinit var viewModel: ReduceViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_long_running_task)
        setupViewModel()
        setupObserver()
    }

    private fun setupViewModel() {
        TODO("Not yet implemented")
    }

    private fun setupObserver() {
        TODO("Not yet implemented")
    }
}