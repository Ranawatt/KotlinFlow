package com.example.kotlinflow.base.errorhandling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinflow.R
import com.example.kotlinflow.base.ApiUserAdapter
import com.example.kotlinflow.data.local.DatabaseHelperImpl
import com.example.kotlinflow.data.api.ApiHelperImpl
import com.example.kotlinflow.data.api.RetrofitBuilder
import com.example.kotlinflow.data.local.DatabaseBuilder
import com.example.kotlinflow.data.model.ApiUser
import com.example.kotlinflow.utils.Status
import com.example.kotlinflow.utils.ViewModelFactory
import kotlinx.android.synthetic.main.activity_single_network_call.*

class CatchActivity : AppCompatActivity() {

    private lateinit var viewmodel: CatchViewModel
    private lateinit var adapter: ApiUserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_network_call)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ApiUserAdapter(arrayListOf())
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context,
            (recyclerView.layoutManager as LinearLayoutManager).orientation)
        )
        recyclerView.adapter = adapter
    }

    private fun setupViewModel() {
        viewmodel= ViewModelProviders.of(this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService),
                DatabaseHelperImpl(DatabaseBuilder.getInstance(applicationContext))
            )
        ).get(CatchViewModel::class.java)
    }

    private fun renderList(users: List<ApiUser>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }

    private fun setupObserver() {
        viewmodel.getUsers().observe(this, Observer {
            when(it.status){
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users) }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

}
