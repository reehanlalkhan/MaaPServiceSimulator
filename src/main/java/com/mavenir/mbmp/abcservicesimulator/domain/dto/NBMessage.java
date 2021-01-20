package com.mavenir.mbmp.abcservicesimulator.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NBMessage {
    private String callbackUrl;
    private String callbackUrlValidity;
    private EventType event;
    @JsonProperty("RCSMessage")
    @Builder.Default
    private RCSMessage rcsMessage = new RCSMessage();
    @Builder.Default
    private MessageContact messageContact = new MessageContact();

    @JsonIgnore
    public void setTextMessage(String textMessage) {
        this.rcsMessage.setTextMessage(textMessage);
    }

    @JsonIgnore
    public void setRichcardMessage(RichcardMessage richcardMessage) {
        this.rcsMessage.setRichcardMessage(richcardMessage);
    }

    @JsonIgnore
    public String getPostBackData() {
        return this.rcsMessage.getSuggestedResponse().getResponse().getReply().getPostback().getData();
    }

    @Override
    public String toString() {
        return "NBMessage{" +
            "callbackUrl='" + callbackUrl + '\'' +
            ", callbackUrlValidity='" + callbackUrlValidity + '\'' +
            ", event=" + event +
            ", rcsMessage=" + rcsMessage +
            ", messageContact=" + messageContact +
            '}';
    }
}
