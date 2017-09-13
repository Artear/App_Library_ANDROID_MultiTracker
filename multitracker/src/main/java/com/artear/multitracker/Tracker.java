package com.artear.multitracker;


import com.artear.multitracker.sendcontract.TrackerSend;

public interface Tracker {
    void send(TrackerSend params);
    void onResume();
    void onPause();
    void onDestroy();
}
