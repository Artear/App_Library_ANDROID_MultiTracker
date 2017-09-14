package com.artear.multitrackerandroid;

import android.app.Application;

import com.artear.multitracker.MultiTracker;
import com.artear.multitrackerandroid.trackers.AnalyticsTracker;
import com.artear.multitrackerandroid.trackers.OtherCustomTracker;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        MultiTracker multiTracker = MultiTracker.getInstance();
        multiTracker.register(new AnalyticsTracker());
        multiTracker.register(new OtherCustomTracker());
    }
}
