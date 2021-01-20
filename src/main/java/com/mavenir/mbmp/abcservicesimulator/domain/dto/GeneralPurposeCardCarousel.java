package com.mavenir.mbmp.abcservicesimulator.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class GeneralPurposeCardCarousel {
    private List<CardContent> content;
    private CardLayout layout;
}
