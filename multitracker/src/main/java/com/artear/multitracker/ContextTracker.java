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
package com.artear.multitracker;

import android.content.Context;

import com.artear.multitracker.contract.tracker.Tracker;

/**
 * ContextTracker is a tracker which needs a {@link Context} for the implementation.
 * <p>
 * Most common of libraries to track and receive events needs the context to initialize.
 */
public abstract class ContextTracker implements Tracker {

    /**
     * This constructor needs a context for the classes that extends from this.
     *
     * @param context The context associated
     */
    protected ContextTracker(final Context context){
    }

    /**
     * By default the key used is the class name. You can override this
     * and use the key would you prefers
     */
    @Override
    public String keyName() {
        return getClass().getName();
    }
}
