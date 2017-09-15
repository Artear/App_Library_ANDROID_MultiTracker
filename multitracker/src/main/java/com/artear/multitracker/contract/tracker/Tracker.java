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
package com.artear.multitracker.contract.tracker;


import com.artear.multitracker.contract.send.TrackerSend;

/**
 * A basic interface which can send a {@link TrackerSend} object and receive
 * the lifecycle events of a traditional component. Also define a key to identify itself.
 * <p>
 * For example: {@link com.artear.multitracker.MultiTracker MultiTracker} or
 * {@link com.artear.multitracker.ContextTracker ContextTracker}.
 *
 */
public interface Tracker {

    void onResume();

    void onPause();

    void onDestroy();

    String keyName();

    void send(TrackerSend params);
}
