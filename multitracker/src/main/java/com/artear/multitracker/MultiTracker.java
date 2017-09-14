package com.artear.multitracker;


import android.support.annotation.NonNull;
import android.util.Log;

import com.artear.multitracker.contract.send.TrackerSend;
import com.artear.multitracker.contract.tracker.Tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiTracker implements Tracker {

    private static MultiTracker instance;
    private Map<Class<? extends Tracker>, Tracker> trackers = new HashMap<>();

    public static MultiTracker getInstance() {
        if (instance == null) {
            instance = new MultiTracker();
        }
        return instance;
    }

    public void register(final Tracker tracker) {
        Class<? extends Tracker> type = tracker.getClass();
        if (trackers.keySet().contains(type)) {
            log("Tracker type currently exists: " + type);
        }
        trackers.put(tracker.getClass(), tracker);
    }

    public void send(final TrackerSend params,
                     @NonNull final List<Class<? extends Tracker>> trackersTypes) {

        for (Class<? extends Tracker> type : trackersTypes) {
            if (trackers.containsKey(type)) {
                trackers.get(type).send(params);
            } else {
                log("Tracker Protocol Unknown: " + type);
            }
        }
    }

    @Override
    public void send(TrackerSend params) {
        send(params, new ArrayList<>(trackers.keySet()));
    }

    @Override
    public void onResume() {
        for (Tracker tracker : trackers.values()) {
            tracker.onResume();
        }
    }

    @Override
    public void onPause() {
        for (Tracker tracker : trackers.values()) {
            tracker.onPause();
        }
    }

    @Override
    public void onDestroy() {
        for (Tracker tracker : trackers.values()) {
            tracker.onDestroy();
        }
        trackers.clear();
    }

    private void log(String logMessage) {
        Log.d("MultiTracker", logMessage);
    }
}
