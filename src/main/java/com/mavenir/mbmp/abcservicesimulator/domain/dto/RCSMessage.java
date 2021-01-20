package com.mavenir.mbmp.abcservicesimulator.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RCSMessage {
    private String msgId;
    private StatusType status;
    private String trafficType;
    private String expiry;
    private String timestamp;
    private String locale;
    private ChannelType channel;
    private String intent;
    private String group;
    private String deviceAgent;
    private String textMessage;
    private String sms;
    private String mms;
    private RichcardMessage richcardMessage;
    private String reason;
    private SuggestedResponse suggestedResponse;
    private FileMessage fileMessage;
    private EventTimePicker eventTimePicker;

    @Override
    public String toString() {
        return "RCSMessage{" +
            "msgId='" + msgId + '\'' +
            ", status=" + status +
            ", trafficType='" + trafficType + '\'' +
            ", expiry='" + expiry + '\'' +
            ", timestamp='" + timestamp + '\'' +
            ", locale='" + locale + '\'' +
            ", channel=" + channel +
            ", intent='" + intent + '\'' +
            ", group='" + group + '\'' +
            ", deviceAgent='" + deviceAgent + '\'' +
            ", textMessage='" + textMessage + '\'' +
            ", sms='" + sms + '\'' +
            ", mms='" + mms + '\'' +
            ", richcardMessage=" + richcardMessage +
            ", fileMessage= " + fileMessage +
            ", eventTimePicker= " + eventTimePicker +
            ", reason='" + reason + '\'' +
            '}';
    }
}