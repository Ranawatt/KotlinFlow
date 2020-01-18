package com.example.kotlinflow.operators

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.kotlinflow.R
import kotlinx.android.synthetic.main.activity_example.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.flowOf as flowOf1

class OnCompletionFlowActivity : AppCompatActivity() {
    lateinit var flowOne: Flow<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
        setupFlow()
        setupClick()
    }

    private fun setupClick() {
        btn.setOnClickListener {
            doSomeWork()
        }
    }

    private fun doSomeWork() {
        lifecycleScope.launchWhenCreated {
            val output = flowOne.onCompletion { emit("End Of Flow") }.toList()
            textView.text = output.toString() //["Himanshu", "Amit", "Janishar","End Of Flow"]
        }
    }

    private fun setupFlow() {
        flowOne = flowOf1("Himanshu", "Amit", "Janishar").flowOn(Dispatchers.Default)

    }
}