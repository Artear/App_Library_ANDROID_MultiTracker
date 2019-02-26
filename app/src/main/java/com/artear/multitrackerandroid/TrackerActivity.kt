package com.artear.multitrackerandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.artear.multitracker.MultiTracker
import com.artear.multitrackerandroid.trackers.AnalyticsTracker
import com.artear.multitrackerandroid.trackers.OtherCustomTracker
import com.artear.multitrackerandroid.trackers.type.event.MyEvent
import com.artear.multitrackerandroid.trackers.type.exception.MyException
import com.artear.multitrackerandroid.trackers.type.view.MyView
import kotlinx.android.synthetic.main.activity_tracker.*


class TrackerActivity : AppCompatActivity() {

    private val testView = MyView("Main View")
    private val testEventOne = MyEvent("Event One")
    private val testEventTwo = MyEvent("Event Two")
    private val testException = MyException(MyException.ErrorCode.INTERNAL_ERROR, "Error :(")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracker)

        sendViewButton.setOnClickListener {
            //Only send to an specific tracker
            MultiTracker.instance.send(testView, arrayOf(AnalyticsTracker::class.java.name))
        }

        sendEventButton.setOnClickListener {
            val justSomeTrackers = arrayOf(AnalyticsTracker::class.java.name,
                    OtherCustomTracker::class.java.name)
            MultiTracker.instance.send(testEventOne, justSomeTrackers)
            MultiTracker.instance.send(testEventTwo, justSomeTrackers)
        }

        sendExceptionButton.setOnClickListener {
            //Send to all tracker registered
            MultiTracker.instance.send(testException)
        }
    }
}
