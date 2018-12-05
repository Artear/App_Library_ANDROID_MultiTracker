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

import android.content.Context

import com.artear.multitracker.contract.tracker.Tracker

/**
 * ContextTracker is a tracker which needs a [Context] for the implementation.
 *
 *
 * Most common of libraries to track and receive events needs the context to initialize.
 */
abstract class ContextTracker(val context: Context) : Tracker {

    /**
     * By default the key used is the class name. You can override this
     * and use the key would you prefers
     */
    override fun keyName(): String {
        return javaClass.name
    }
}
