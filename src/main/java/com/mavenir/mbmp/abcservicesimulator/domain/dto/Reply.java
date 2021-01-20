package com.mavenir.mbmp.abcservicesimulator.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reply {
    @Builder.Default
    private Postback postback = new Postback();
    private String displayText;
}
