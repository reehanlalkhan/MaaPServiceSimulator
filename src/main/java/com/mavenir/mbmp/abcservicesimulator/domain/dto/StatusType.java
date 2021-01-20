package com.mavenir.mbmp.abcservicesimulator.domain.dto;

import lombok.Getter;

@Getter
public enum StatusType {
    sent("sent"),
    pending("pending"),
    delivered("delivered"),
    displayed("displayed"),
    revoked("revoked"),
    failed("failed"),
    read("read");

    private final String status;

    StatusType(String status) {
        this.status = status;
    }
}
