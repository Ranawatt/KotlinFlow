package com.example.kotlinflow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.kotlinflow.operators.*

class OperatorsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operators)
    }

    fun startSimpleActivity(view: View?) =
        startActivity(Intent(this, SimpleFlowActivity::class.java))

    // To Zip the string sequentially
    fun startZipActivity(view: View?) = startActivity(Intent(this, ZipFlowActivity::class.java))

    // To remove the duplicate string value
    fun startDistinctUntilChangedActivity(view: View?) =
        startActivity(Intent(this, DistinctUntilChangedFlowActivity::class.java))
    // After emit the whole list it will append given value to the list
    fun startOnCompletionActivity(view: View?) =
        startActivity(Intent(this, OnCompletionFlowActivity::class.java))
    // To map the value with given input
    fun startFlatMapConcatActivity(view: View) =
        startActivity(Intent(this, FlatMapConcatFlowActivity::class.java))

    fun startFlatMapMergeActivity(view: View) =
        startActivity(Intent(this, FlatMapMergeFlowActivity::class.java))

    fun startFlattenConcatFlowActivity(view: View) =
        startActivity(Intent(this, FlattenConcatFlowActivity::class.java))

    fun startMergeFlowActivity(view: View) =
        startActivity(Intent(this, MergeFlowActivity::class.java))

    fun startFlatterMergeFlowActivity(view: View) =
        startActivity(Intent(this, FlattenMergeFlowActivity::class.java))

    fun startTransformLatestFlowActivity(view: View) =
        startActivity(Intent(this, TransformLatestFlowActivity::class.java))

    fun startFlatMapLatestFlowActivity(view: View) =
        startActivity(Intent(this, FlatMapLatestFlowActivity::class.java))

    fun startFilterActivity(view: View) =
        startActivity(Intent(this, FilterFlowActivity::class.java))

    fun startFilterNotActivity(view: View) =
        startActivity(Intent(this, FilterNotFlowActivity::class.java))

    fun startFilterIsInstanceFlowActivity(view: View) =
        startActivity(Intent(this, FilterIsInstanceFlowActivity::class.java))

    fun startFilterNotNullFlowActivity(view: View) =
        startActivity(Intent(this, FilterNotNullFlowActivity::class.java))

    fun startMapActivity(view: View) =
        startActivity(Intent(this, MapFlowActivity::class.java))


    fun startScanReduceActivity(view: View) =
        startActivity(Intent(this, ScanReduceFlowActivity::class.java))

    fun startOnStartActivity(view: View) =
        startActivity(Intent(this, OnStartFlowActivity::class.java))

    fun startSingleNetworkCallActivity(view: View) {
        startActivity(Intent(this, SingleNetworkCallActivity::class.java))
    }

    fun startSeriesNetworkCallsActivity(view: View) {
        startActivity(Intent(this, SeriesNetworkCallsActivity::class.java))
    }
//
//    fun startParallelNetworkCallsActivity(view: View) {
//        startActivity(Intent(this@MainActivity, ParallelNetworkCallsActivity::class.java))
//    }
//
//    fun startRoomDatabaseActivity(view: View) {
//        startActivity(Intent(this@MainActivity, RoomDBActivity::class.java))
//    }
//
//    fun startCatchActivity(view: View) {
//        startActivity(Intent(this@MainActivity, CatchActivity::class.java))
//    }
//
//    fun startEmitAllActivity(view: View) {
//        startActivity(Intent(this@MainActivity, EmitAllActivity::class.java))
//    }
//
//    fun startCompletionActivity(view: View) {
//        startActivity(Intent(this@MainActivity, CompletionActivity::class.java))
//    }
//
//    fun startLongRunningTaskActivity(view: View) {
//        startActivity(Intent(this@MainActivity, LongRunningTaskActivity::class.java))
//    }
//
//    fun startTwoLongRunningTasksActivity(view: View) {
//        startActivity(Intent(this@MainActivity, TwoLongRunningTasksActivity::class.java))
//    }
//
//    fun startFilterActivity(view: View) {
//        startActivity(Intent(this@MainActivity, FilterActivity::class.java))
//    }
//
//    fun startReduceActivity(view: View) {
//        startActivity(Intent(this@MainActivity, ReduceActivity::class.java))
//    }
//
//    fun startSearchActivity(view: View) {
//        startActivity(Intent(this, SearchActivity::class.java))
//    }
}
