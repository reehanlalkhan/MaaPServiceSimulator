package com.mavenir.mbmp.abcservicesimulator.domain.dto;

import lombok.Getter;

@Getter
public enum EventType {
    message("message"),
    isTyping("isTyping"),
    messageStatus("messageStatus"),
    fileStatus("fileStatus"),
    response("response"),
    alias("alias"),
    hitlStart("hitlStart"),
    hitlStop("hitlStop"),
    close("close"),
    healthProbe("healthProbe");

    private final String event;

    EventType(String event) {
        this.event = event;
    }
}