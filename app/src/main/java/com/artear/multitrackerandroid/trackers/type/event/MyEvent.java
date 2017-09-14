package com.artear.multitrackerandroid.trackers.type.event;


import com.artear.multitracker.contract.send.TrackerEvent;

public class MyEvent implements TrackerEvent {

    private String name;

    public MyEvent(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "< MyEvent - name = " + name + " >";
    }
}
