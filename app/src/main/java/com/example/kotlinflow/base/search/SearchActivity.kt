package com.example.kotlinflow.base.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import com.example.kotlinflow.R
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.coroutines.CoroutineContext

class SearchActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private lateinit var job: Job
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        job = Job()
        setupSearchStateFlow()
    }


    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }

    private fun setupSearchStateFlow() {
        launch {
            searchView.getQueryTextChangeStateFlow()
                .debounce(300)
                .filter { query ->
                    if (query.isEmpty()) {
                        textViewResult.text = ""
                        return@filter false
                    } else {
                        return@filter true
                    }
                }
                .distinctUntilChanged()
                .flatMapLatest { query ->
                    dataFromNetwork(query)
                        .catch {
                            emitAll(flowOf(""))
                        }
                }
                .flowOn(Dispatchers.Default)
                .collect { result ->
                    textViewResult.text = result
                }
        }
    }

    /**
     * Simulation of network data
     */
    private fun dataFromNetwork(query: String): Flow<String> {
        return flow {
            delay(2000)
            emit(query)
        }
    }


}

private fun SearchView.getQueryTextChangeStateFlow(): StateFlow<String> {

    val query =  MutableStateFlow("")

    setOnQueryTextListener(object  : SearchView.OnQueryTextListener{
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            query.value = newText
            return true
        }

    })
    return query
}
