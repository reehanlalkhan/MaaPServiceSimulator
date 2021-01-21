package com.mavenir.mbmp.abcservicesimulator.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Suggestion {
    private Reply reply;
    private Action action;
    private String imageIdentifier;

    public void setDisplayText(String displayText) {
        this.reply.setDisplayText(displayText);
    }

    public void setPostbackData(String postbackData) {
        this.reply.getPostback().setData(postbackData);
    }
}
