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
public class CapabilityResponse {
    @Builder.Default
    private List<String> capabilities = new ArrayList<>();
}