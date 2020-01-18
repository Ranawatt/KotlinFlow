package com.example.kotlinflow.operators

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.kotlinflow.R
import kotlinx.android.synthetic.main.activity_example.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class FlatMapConcatFlowActivity : AppCompatActivity() {

    lateinit var flowOne: Flow<String>
    lateinit var flowTwo: Flow<String>


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
            val output = flowOne
                .flatMapConcat {
                    flow {
                        emit(it)
                        emit("Name is $it")
                    }
                }
                .toList()
            textView.text = output.toString()

        }
    }

    private fun setupFlow() {
        flowOne = flowOf("Himanshu", "Amit").flowOn(Dispatchers.Default)

    }
}