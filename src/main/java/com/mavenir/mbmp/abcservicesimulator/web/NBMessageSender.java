package com.mavenir.mbmp.abcservicesimulator.web;

import org.springframework.http.ResponseEntity;

import com.mavenir.mbmp.abcservicesimulator.domain.dto.BotMessageRequest;
import com.mavenir.mbmp.abcservicesimulator.domain.dto.CapabilityResponse;
import com.mavenir.mbmp.abcservicesimulator.domain.dto.NBMessage;

import reactor.core.publisher.Mono;

public interface NBMessageSender {
	Mono<CapabilityResponse> sendCapabilityCheck(BotMessageRequest request);

	Mono<ResponseEntity<NBMessage>> sendMessage(NBMessage nbMessage, BotMessageRequest request);
}
