package com.mavenir.mbmp.abcservicesimulator.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mavenir.mbmp.abcservicesimulator.domain.dto.BotMessageRequest;
import com.mavenir.mbmp.abcservicesimulator.domain.dto.CapabilityResponse;
import com.mavenir.mbmp.abcservicesimulator.domain.dto.NBMessage;

import reactor.core.publisher.Mono;

@Service
public class NBMessageSenderImpl implements NBMessageSender {
	
    private final NBClient nbClient;
    
    public NBMessageSenderImpl(NBClient nbClient) {
        this.nbClient = nbClient;
    }

	@Override
    public Mono<CapabilityResponse> sendCapabilityCheck(BotMessageRequest request) {
        return nbClient.sendCapabilityCheck(request);
    }

	@Override
    public Mono<ResponseEntity<NBMessage>> sendMessage(NBMessage nbMessage, BotMessageRequest request) {
        return nbClient.sendMessage(nbMessage, request);
    }

}
