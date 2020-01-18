package com.example.kotlinflow.operators

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.kotlinflow.R
import kotlinx.android.synthetic.main.activity_example.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList

class FlatMapLatestFlowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
        setupClick()
    }

    private fun setupClick() {
        btn.setOnClickListener {
            doSomeWork()
        }
    }

    private fun doSomeWork() {
        lifecycleScope.launchWhenCreated {
            val output =
                flow {
                    emit("Himanshu")
                    delay(100)
                    emit("Amit")
                    delay(100)
                    emit("Janishar")
                }.flatMapLatest { name ->
                    flow {
                        delay(200)
                        emit("$name Ali")
                    }
                }.toList()
            textView.text = output.toString()

        }
    }


}