package com.mavenir.mbmp.abcservicesimulator.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BotMessageRequest {
	@Builder.Default
    private String botId = "8d62ed510f13480c946657cd86e8@botplatform-beta.rcs.mavenir.com";
    private String userContact;
    @Builder.Default
    private ChannelType channel = ChannelType.abc;
    @Builder.Default
    private String authToken = "8d62ed510f13480c946657cd86e8";
}
