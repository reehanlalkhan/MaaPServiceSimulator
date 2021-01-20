package com.mavenir.mbmp.abcservicesimulator.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mavenir.mbmp.abcservicesimulator.domain.dto.NBMessage;
import com.mavenir.mbmp.abcservicesimulator.service.ABCRequestHandlerService;
import com.mavenir.mbmp.abcservicesimulator.utils.LoggerUtils;
import com.mavenir.mbmp.abcservicesimulator.utils.constants.ABCRequestHandlerConstants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
//@RequestMapping("bot/v1/rluWmkqdYB2tTQI@botplatform-beta.rcs.mavenir.com/messages")
@RequestMapping(ABCRequestHandlerConstants.MESSAGE_REST_CALL)
public class MbmpAbcRequestHandlerServiceApiResource implements ABCRequestHandlerConstants {

	private final ABCRequestHandlerService requestHandler;

	@Autowired
	public MbmpAbcRequestHandlerServiceApiResource(final ABCRequestHandlerService requestHandler) {
		this.requestHandler = requestHandler;
	}

	// http://localhost:8083/bot/v1/{botId}/messages
	@RequestMapping(value = REQUEST_PATH_WITH_BOTID, method = { RequestMethod.POST })
	public @ResponseBody ResponseEntity<NBMessage> acceptRequest(@PathVariable("botId") String botId,
			@RequestBody NBMessage requestBody) {
		log.debug("::acceptRequest::Request recieved: from Bot:" + botId);
		LoggerUtils.logIncomingMessage(requestBody);

		requestHandler.sendMessageToSubscriber(requestBody).ifPresent(message -> {
			new ResponseEntity<NBMessage>(message, HttpStatus.OK);
		});

		return new ResponseEntity<NBMessage>(new NBMessage(), HttpStatus.OK);
	}

}
