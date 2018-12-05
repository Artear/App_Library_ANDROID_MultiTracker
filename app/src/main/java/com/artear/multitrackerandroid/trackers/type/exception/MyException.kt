package com.artear.multitrackerandroid.trackers.type.exception

import com.artear.multitracker.contract.send.TrackerException


class MyException(private val code: ErrorCode, private val messenger: String) : TrackerException {

    enum class ErrorCode {
        INVALID_CHARACTER,
        INTERNAL_ERROR
    }

    override fun toString(): String {
        return "< MyException - code = $code, messenger = $messenger >"
    }
}
