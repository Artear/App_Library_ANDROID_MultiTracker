package com.artear.multitrackerandroid.trackers.type.event


import com.artear.multitracker.contract.send.TrackerEvent

class MyEvent(private val name: String) : TrackerEvent {

    override fun toString(): String {
        return "< MyEvent - name = $name >"
    }
}
