package com.artear.multitrackerandroid.trackers.type.exception;

import com.artear.multitracker.contract.send.TrackerException;


public class MyException implements TrackerException {

    public enum ErrorCode {
        INVALID_CHARACTER,
        INTERNAL_ERROR
    }

    private ErrorCode code;
    private String messenger;

    public MyException(ErrorCode code, String messenger) {
        this.code = code;
        this.messenger = messenger;
    }

    @Override
    public String toString() {
        return "< MyException - code = " + code + ", messenger = " + messenger + " >";
    }
}
