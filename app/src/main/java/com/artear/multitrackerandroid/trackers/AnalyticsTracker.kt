package com.artear.multitrackerandroid.trackers

import android.content.Context
import android.util.Log
import com.artear.multitracker.ContextTracker
import com.artear.multitracker.contract.send.TrackerSend


class AnalyticsTracker(context: Context) : ContextTracker(context) {

    override fun send(params: TrackerSend) {
        Log.d("AnalyticsTracker", "Send params to some place. Param = " + params.toString())
    }

    override fun onResume() {

    }

    override fun onPause() {

    }

    override fun onDestroy() {

    }
}
