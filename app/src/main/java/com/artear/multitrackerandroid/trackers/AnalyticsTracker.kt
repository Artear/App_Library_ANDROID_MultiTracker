package com.artear.multitrackerandroid.trackers

import android.content.Context
import com.artear.multitracker.ContextTracker
import com.artear.multitracker.contract.send.TrackerSend
import com.artear.tools.android.log.logD


class AnalyticsTracker(context: Context) : ContextTracker(context) {

    override fun send(params: TrackerSend) {
        logD { "- AnalyticsTracker - Send params to some place. Param = $params" }
    }

    override fun onResume() {

    }

    override fun onPause() {

    }

    override fun onDestroy() {

    }
}
