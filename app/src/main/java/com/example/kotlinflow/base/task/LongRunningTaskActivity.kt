package com.example.kotlinflow.base.task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinflow.R

class LongRunningTaskActivity : AppCompatActivity() {

    private lateinit var viewmodel: LongRunningTaskViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_long_running_task)
        setupViewModel()
        setupLongRunningTask()
    }
}
