package com.artear.multitrackerandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.artear.multitracker.MultiTracker;
import com.artear.multitracker.contract.tracker.Tracker;
import com.artear.multitrackerandroid.trackers.AnalyticsTracker;
import com.artear.multitrackerandroid.trackers.OtherCustomTracker;
import com.artear.multitrackerandroid.trackers.type.event.MyEvent;
import com.artear.multitrackerandroid.trackers.type.exception.MyException;
import com.artear.multitrackerandroid.trackers.type.view.MyView;

import java.util.ArrayList;
import java.util.List;

import static com.artear.multitrackerandroid.trackers.type.exception.MyException.ErrorCode.INTERNAL_ERROR;

public class TrackerActivity extends AppCompatActivity {

    private MyView testView = new MyView("Main View");
    private MyEvent testEventOne = new MyEvent("Event One");
    private MyEvent testEventTwo = new MyEvent("Event Two");
    private MyException testException = new MyException(INTERNAL_ERROR , "Error :(");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);

        Button sendViewButton = (Button) findViewById(R.id.send_view_button);

        sendViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Class<? extends Tracker>> justSomeTrackers = new ArrayList<>();
                justSomeTrackers.add(AnalyticsTracker.class);

                MultiTracker.getInstance().send(testView, justSomeTrackers);
            }
        });

        Button sendEventButton = (Button) findViewById(R.id.send_event_button);

        sendEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<Class<? extends Tracker>> justSomeTrackers = new ArrayList<>();
                justSomeTrackers.add(AnalyticsTracker.class);
                justSomeTrackers.add(OtherCustomTracker.class);

                MultiTracker.getInstance().send(testEventOne, justSomeTrackers);
                MultiTracker.getInstance().send(testEventTwo, justSomeTrackers);
            }
        });

        Button sendExceptionButton = (Button) findViewById(R.id.send_exception_button);

        sendExceptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Send all tracker registered
                MultiTracker.getInstance().send(testException);
            }
        });
    }
}
