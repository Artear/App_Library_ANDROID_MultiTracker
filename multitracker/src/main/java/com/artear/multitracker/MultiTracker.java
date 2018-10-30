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



import android.support.annotation.NonNull;
import android.util.Log;

import com.artear.multitracker.contract.send.TrackerSend;
import com.artear.multitracker.contract.tracker.Tracker;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static android.R.attr.type;

/**
 * A simple class for track and propagate to all trackers registered.
 * To send a {@link TrackerSend} each tracker registered must implements
 * {@link Tracker Tracker}.
 */
public final class MultiTracker implements Tracker {

    private static MultiTracker instance;
    private Map<String, Tracker> trackers = new HashMap<>();
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    /**
     * MultiTracker is a singleton, its creation must be through {@link #getInstance()} .
     */
    private MultiTracker() {
    }

    public static MultiTracker getInstance() {
        if (instance == null) {
            instance = new MultiTracker();
        }
        return instance;
    }

    /**
     * It is used to register a tracker and send to it a {@link TrackerSend} in the future.
     *
     * @param tracker The tracker to deliver the send message.
     */
    public void register(final Tracker tracker) {
        if (trackers.containsKey(tracker.keyName())) {
            log("Tracker type currently exists: " + type);
        }
        trackers.put(tracker.keyName(), tracker);
    }

    /**
     * Use this method for send only to some specific trackers. For send to all trackers
     * use {@link #send(TrackerSend)}.
     *
     * @param param       The object to be sent.
     * @param trackerKeys The keys that represent to which tracker will be delivered.
     */
    public void send(final TrackerSend param, @NonNull final String[] trackerKeys) {
        for (final String keyName : trackerKeys) {
            if (trackers.containsKey(keyName)) {
                executor.submit(new Runnable() {
                    @Override
                    public void run() {
                        trackers.get(keyName).send(param);
                    }
                });
            } else {
                log("Tracker unknown keyName: " + keyName);
            }
        }
    }

    /**
     * Use this for send to all trackers registered.
     *
     * @param params The object to be sent.
     */
    @Override
    public void send(final TrackerSend params) {
        send(params, trackers.keySet().toArray(new String[trackers.size()]));
    }

    @Override
    public String keyName() {
        return getClass().getName();
    }

    @Override
    public void onResume() {
        for (final Tracker tracker : trackers.values()) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    tracker.onResume();
                }
            });
        }
    }

    @Override
    public void onPause() {
        for (final Tracker tracker : trackers.values()) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    tracker.onPause();
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        for (final Tracker tracker : trackers.values()) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    tracker.onDestroy();
                }
            });
        }
        trackers.clear();
    }

    private void log(final String logMessage) {
        Log.d("MultiTracker", logMessage);
    }
}
