package com.mavenir.mbmp.abcservicesimulator.web;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;

import com.mavenir.mbmp.abcservicesimulator.domain.dto.BotMessageRequest;
import com.mavenir.mbmp.abcservicesimulator.domain.dto.CapabilityResponse;
import com.mavenir.mbmp.abcservicesimulator.domain.dto.NBMessage;
import com.mavenir.mbmp.abcservicesimulator.domain.dto.RCSMessage;
import com.mavenir.mbmp.abcservicesimulator.utils.constants.ABCRequestHandlerConstants;

import io.netty.handler.ssl.SslHandshakeTimeoutException;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class NBClient implements ABCRequestHandlerConstants {

	private final WebClient nbWebClient;

	public NBClient(WebClient nbWebClient) {
		this.nbWebClient = nbWebClient;
	}

	public Mono<ResponseEntity<NBMessage>> sendMessage(NBMessage nbMessage, BotMessageRequest request) {
		return nbWebClient.post()
				.headers(httpHeaders -> httpHeaders.setBearerAuth(request.getAuthToken()))
				.headers(httpHeaders -> httpHeaders.setContentType(MediaType.APPLICATION_JSON)).bodyValue(nbMessage)
				.exchangeToMono(response -> response.toEntity(NBMessage.class))
				.doOnError(Exception.class, this::handleException);
	}
	
	public Mono<ResponseEntity<NBMessage>> sendMessageWithBot(NBMessage nbMessage, BotMessageRequest request) {
		return nbWebClient.post().uri(uriBuilder -> {
			URI uri = uriBuilder.pathSegment("bot").pathSegment(VERSION).pathSegment(request.getBotId())
					.pathSegment("messages").build();
			return uri;
		}).headers(httpHeaders -> httpHeaders.setBearerAuth(request.getAuthToken()))
				.headers(httpHeaders -> httpHeaders.setContentType(MediaType.APPLICATION_JSON)).bodyValue(nbMessage)
				.exchangeToMono(response -> response.toEntity(NBMessage.class))
				.doOnError(Exception.class, this::handleException);
	}

	private ResponseEntity<NBMessage> handleException(Exception e) {
		String error = e.getMessage();
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		if (e instanceof SslHandshakeTimeoutException) {
			status = HttpStatus.GATEWAY_TIMEOUT;
		} else if (e instanceof WebClientResponseException) {
			status = ((WebClientResponseException) e).getStatusCode();
			error = ((WebClientResponseException) e).getResponseBodyAsString();
		}
		return ResponseEntity.status(status).body(buildErrorNBMessage(error));
	}

	private NBMessage buildErrorNBMessage(String error) {
		log.debug("HTTP Error:{}", error);
		return NBMessage.builder().rcsMessage(RCSMessage.builder().reason(error).build()).build();
	}

	public Mono<CapabilityResponse> sendCapabilityCheck(BotMessageRequest request) {
		return nbWebClient.get().uri(uriBuilder -> {
			UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUri(uriBuilder.build());
			URI uri = uriComponentsBuilder.pathSegment("bot").pathSegment(VERSION).pathSegment(request.getBotId())
					.pathSegment("contactCapabilities")
					.queryParam("userContact", request.getUserContact().replace("+", "%2B")).build(true).toUri();
			return uri;
		}).headers(httpHeaders -> httpHeaders.setBearerAuth(request.getAuthToken()))
				.exchangeToMono(response -> response.bodyToMono(CapabilityResponse.class))
				.onErrorReturn(new CapabilityResponse());
	}
}
