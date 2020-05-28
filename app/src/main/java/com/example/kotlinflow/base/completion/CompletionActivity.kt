package com.example.kotlinflow.base.completion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.kotlinflow.R
import com.example.kotlinflow.base.ApiUserAdapter
import com.example.kotlinflow.data.api.ApiHelperImpl
import com.example.kotlinflow.data.api.RetrofitBuilder
import com.example.kotlinflow.data.local.DatabaseBuilder
import com.example.kotlinflow.data.local.DatabaseHelperImpl
import com.example.kotlinflow.utils.Status
import com.example.kotlinflow.utils.ViewModelFactory
import kotlinx.android.synthetic.main.activity_example.*
import kotlinx.android.synthetic.main.activity_single_network_call.*

class CompletionActivity : AppCompatActivity() {

    private lateinit var viewModel: CompletionViewModel
    private lateinit var adapter: ApiUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completion)
        setupViewModel()
        setupObserver()
    }

    private fun setupObserver() {
        viewModel.getStatus().observe(this, Observer {
            when (it.status) {
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
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()

                }
            }
        })
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService),
                DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext))
            )
        ).get(CompletionViewModel::class.java)
    }
}
