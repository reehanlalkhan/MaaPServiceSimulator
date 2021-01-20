package com.mavenir.mbmp.abcservicesimulator.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventTimePicker {
    private String title;
    private String subtitle;
    private int timezoneOffset;
    private TimeSlot[] timeslots;

    @Override
    public String toString() {
        return "EventTimePicker{" +
            "title='" + title + '\'' +
            ", subtitle='" + subtitle + '\'' +
            ", timezoneOffset=" + timezoneOffset +
            ", timeslots=" + Arrays.toString(timeslots) +
            '}';
    }
}
