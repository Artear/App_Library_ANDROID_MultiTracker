package com.artear.multitrackerandroid.trackers.type.view;

import com.artear.multitracker.contract.send.TrackerView;


public class MyView implements TrackerView {

    private String name;

    public MyView(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "< MyView - name = " + name + " >";
    }
}
