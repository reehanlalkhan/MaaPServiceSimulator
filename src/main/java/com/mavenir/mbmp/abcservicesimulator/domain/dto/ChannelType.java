package com.mavenir.mbmp.abcservicesimulator.domain.dto;

import lombok.Getter;

@Getter
public enum ChannelType {
    rcs("rcs"),
    grbm("grbm"),
    abc("abc"),
    sms("sms"),
    mms("mms");

    private final String channel;

    ChannelType(String channel) {
        this.channel = channel;
    }
}
