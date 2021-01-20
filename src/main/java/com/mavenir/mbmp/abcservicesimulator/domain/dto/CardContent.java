package com.mavenir.mbmp.abcservicesimulator.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardContent {
    @Builder.Default
    private Media media = new Media();
    @Builder.Default
    private List<Suggestion> suggestions = new ArrayList<Suggestion>();
    private String description;
    private String title;
}

