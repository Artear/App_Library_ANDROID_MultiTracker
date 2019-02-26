package com.artear.multitrackerandroid.trackers


import android.content.Context
import com.artear.multitracker.ContextTracker
import com.artear.multitracker.contract.send.TrackerSend
import com.artear.tools.android.log.logD

class OtherCustomTracker(context: Context) : ContextTracker(context) {

    override fun send(params: TrackerSend) {
        logD { "- OtherCustomTracker - Send params to custom server. Param = $params" }
    }

    override fun onResume() {

    }

    override fun onPause() {

    }

    override fun onDestroy() {

    }
}
