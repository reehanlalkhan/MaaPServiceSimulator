package com.mavenir.mbmp.abcservicesimulator.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RichcardMessage {
    private Message message;

    public void setMedia(Media media) {
        this.message.getGeneralPurposeCard().getContent().setMedia(media);
    }

    public void setLayout(CardLayout cardLayout) {
        this.message.getGeneralPurposeCard().setLayout(cardLayout);
    }

    public void setTitle(String title) {
        this.message.getGeneralPurposeCard().getContent().setTitle(title);
    }

    public void setDescription(String description) {
        this.message.getGeneralPurposeCard().getContent().setDescription(description);
    }

    public void addSuggestion(Suggestion suggestion) {
        this.message.getGeneralPurposeCard().getContent().getSuggestions().add(suggestion);
    }
}
