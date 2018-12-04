/*
 * Copyright 2017 Artear S.A.
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
package com.artear.multitracker.contract.tracker


import com.artear.multitracker.contract.send.TrackerSend

/**
 * A basic interface which can send a [TrackerSend] object and receive
 * the lifecycle events of a traditional component. Also define a key to identify itself.
 *
 *
 * For example: [MultiTracker][com.artear.multitracker.MultiTracker] or
 * [ContextTracker][com.artear.multitracker.ContextTracker].
 */
interface Tracker {

    fun send(params: TrackerSend)

    fun onResume()

    fun onPause()

    fun onDestroy()

    fun keyName(): String
}
