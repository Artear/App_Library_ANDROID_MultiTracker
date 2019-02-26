/*
 * Copyright 2018 Artear S.A.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.artear.multitracker

import com.artear.multitracker.MultiTracker.Companion.instance
import com.artear.multitracker.contract.send.TrackerSend
import com.artear.multitracker.contract.tracker.Tracker
import com.artear.tools.android.log.logD
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
            logD { "- MultiTracker - Tracker type currently exists: ${tracker.keyName()}" }
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
                logD { "- MultiTracker - Tracker unknown keyName: $it " }
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

}