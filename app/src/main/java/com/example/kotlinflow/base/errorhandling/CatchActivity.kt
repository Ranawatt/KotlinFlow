package com.example.kotlinflow.base.errorhandling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinflow.R
import com.example.kotlinflow.base.ApiUserAdapter

class CatchActivity : AppCompatActivity() {

    private lateinit var viewmodel: CatchViewModel
    private lateinit var adapter: ApiUserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_network_call)
    }
}
