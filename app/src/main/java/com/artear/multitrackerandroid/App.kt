package com.artear.multitrackerandroid

import android.app.Application

import com.artear.multitracker.MultiTracker
import com.artear.multitrackerandroid.trackers.AnalyticsTracker
import com.artear.multitrackerandroid.trackers.OtherCustomTracker
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        //In your app register all trackers
        MultiTracker.instance.register(AnalyticsTracker(baseContext))
        MultiTracker.instance.register(OtherCustomTracker(baseContext))
    }
}
