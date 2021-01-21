package com.mavenir.mbmp.abcservicesimulator.domain.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardContent {
    private Media media;
    private List<Suggestion> suggestions;
    private List<Media> mediaList;
    private boolean multiSelect;
    private String description;
    private String title;
}

