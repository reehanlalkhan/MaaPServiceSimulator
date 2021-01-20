package com.mavenir.mbmp.abcservicesimulator.utils;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.mavenir.mbmp.abcservicesimulator.domain.dto.BotMessageRequest;
import com.mavenir.mbmp.abcservicesimulator.domain.dto.NBMessage;
import com.mavenir.mbmp.abcservicesimulator.utils.constants.ABCRequestHandlerConstants;
import com.mavenir.mbmp.abcservicesimulator.web.NBMessageSender;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestSendingThread extends Thread implements ABCRequestHandlerConstants {

	private final NBMessage nbMessage;
	private final NBMessageSender messageSender;
	private final Optional<Integer> delay;

	public RequestSendingThread(NBMessageSender messageSender, NBMessage nbMessage, Optional<Integer> delay) {
		this.nbMessage = nbMessage;
		this.messageSender = messageSender;
		this.delay = delay;
	}

	public void run() {
		long tId = Thread.currentThread().getId();
		log.debug("RequestSendingThread.run::Thread ID:" + tId + " uri:" + getUri());
		delay.ifPresent(delatTime -> {
			try {
				Thread.sleep(delatTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		LoggerUtils.logOutgoingMessage(nbMessage);
		ResponseEntity<NBMessage> responseFromServer = messageSender
				.sendMessage(nbMessage, BotMessageRequest.builder().build()).block();

		// Call the service for the next message
		// handlerService.sendMessageToSubscriber(interaction);
		LoggerUtils.logResponseMessage(responseFromServer.getBody());
	}

	// Create the URL: Currently assuming fixed Business Messaging App URL
	private String getUri() {
		return BM_APP_URL;
	}

}
