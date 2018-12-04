package com.artear.multitracker

import android.util.Log
import com.artear.multitracker.MultiTracker.Companion.instance
import com.artear.multitracker.contract.send.TrackerSend
import com.artear.multitracker.contract.tracker.Tracker
import java.util.concurrent.Executors

/**
 * A simple class for track and propagate to all trackers registered.
 * To send a [TrackerSend] each tracker registered must implements [Tracker].
 *
 * MultiTracker is a singleton, its creation must be through [instance] .
 */
class MultiTracker private constructor() : Tracker {

    companion object {
        val instance = MultiTracker()
    }

    private val trackers = hashMapOf<String, Tracker>()
    private val executor = Executors.newSingleThreadExecutor()!!

    /**
     * It is used to register a tracker and send to it a [TrackerSend] in the future.
     *
     * @param tracker The tracker to deliver the send message.
     */
    fun register(tracker: Tracker) {
        if (trackers.containsKey(tracker.keyName())) {
            log("Tracker type currently exists: ${tracker.keyName()}")
        }
        trackers[tracker.keyName()] = tracker
    }

    /**
     * Use this method for send only to some specific trackers. For send to all trackers use
     * [send][com.artear.multitracker.contract.tracker.Tracker.send]
     *
     * @param param       The object to be sent.
     * @param trackerKeys The keys that represent to which tracker will be delivered.
     *
     */
    fun send(param: TrackerSend, trackerKeys: Array<String>) {
        trackerKeys.forEach {
            if (trackers.containsKey(it)) {
                executor.submit { trackers[it]?.send(param) }
            } else {
                log("Tracker unknown keyName: $it")
            }
        }
    }

    /**
     * Use this for send to all trackers registered.
     *
     * @param params The object to be sent.
     */
    override fun send(params: TrackerSend) {
        send(params, trackers.keys.toTypedArray())
    }

    override fun onResume() {
        trackers.values.forEach { executor.submit { it.onResume() } }
    }

    override fun onPause() {
        trackers.values.forEach { executor.submit { it.onPause() } }
    }

    override fun onDestroy() {
        trackers.values.forEach { executor.submit { it.onDestroy() } }
        trackers.clear()
    }

    override fun keyName(): String {
        return javaClass.name
    }

    private fun log(logMessage: String) {
        Log.d("MultiTracker", logMessage)
    }

}