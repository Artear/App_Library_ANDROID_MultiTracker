package com.artear.multitrackerandroid;

import android.app.Application;

import com.artear.multitracker.MultiTracker;
import com.artear.multitrackerandroid.trackers.AnalyticsTracker;
import com.artear.multitrackerandroid.trackers.OtherCustomTracker;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //In your app register all trackers
        MultiTracker.getInstance().register(new AnalyticsTracker(getBaseContext()));
        MultiTracker.getInstance().register(new OtherCustomTracker(getBaseContext()));
    }
}
