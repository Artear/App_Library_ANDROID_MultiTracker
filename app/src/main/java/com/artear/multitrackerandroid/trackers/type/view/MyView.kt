package com.artear.multitrackerandroid.trackers.type.view

import com.artear.multitracker.contract.send.TrackerView


class MyView(private val name: String) : TrackerView {

    override fun toString(): String {
        return "< MyView - name = $name >"
    }
}
