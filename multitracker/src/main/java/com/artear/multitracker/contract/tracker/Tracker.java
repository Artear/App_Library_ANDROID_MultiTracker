package com.artear.multitracker.contract.tracker;


import com.artear.multitracker.contract.send.TrackerSend;

public interface Tracker {
    void send(TrackerSend params);
    void onResume();
    void onPause();
    void onDestroy();
}
