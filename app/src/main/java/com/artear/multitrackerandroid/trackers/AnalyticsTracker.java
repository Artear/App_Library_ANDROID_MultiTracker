package com.artear.multitrackerandroid.trackers;

import android.content.Context;
import android.util.Log;

import com.artear.multitracker.contract.send.TrackerSend;
import com.artear.multitracker.contract.tracker.ContextTracker;


public class AnalyticsTracker implements ContextTracker {

    @Override
    public void init(Context context) {

    }

    @Override
    public void send(TrackerSend params) {
        Log.d("AnalyticsTracker", "Send params to some place. Param = " + params.toString());
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {

    }
}
