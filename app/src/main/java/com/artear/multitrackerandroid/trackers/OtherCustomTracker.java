package com.artear.multitrackerandroid.trackers;


import android.content.Context;
import android.util.Log;

import com.artear.multitracker.contract.send.TrackerSend;
import com.artear.multitracker.ContextTracker;

public class OtherCustomTracker extends ContextTracker {

    public OtherCustomTracker(Context context) {
        super(context);
    }

    @Override
    public void send(TrackerSend params) {
        Log.d("OtherCustomTracker", "Send params to custom server. Param = " + params.toString());
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
