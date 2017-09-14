package com.artear.multitrackerandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.artear.multitracker.MultiTracker;
import com.artear.multitrackerandroid.trackers.type.event.MyEvent;
import com.artear.multitrackerandroid.trackers.type.exception.MyException;
import com.artear.multitrackerandroid.trackers.type.view.MyView;

public class TrackerActivity extends AppCompatActivity {

    private MyView testView = new MyView("Main View");
    private MyEvent testEventOne = new MyEvent("Event One");
    private MyEvent testEventTwo = new MyEvent("Event Two");
    private MyException testException = new MyException(MyException.ErrorCode.INTERNAL_ERROR , "Error :(");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);

        Button sendEventButton = (Button) findViewById(R.id.send_event_button);

        sendEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultiTracker.getInstance().send(testEventOne);
            }
        });
    }
}
