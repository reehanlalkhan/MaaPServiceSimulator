package com.mavenir.mbmp.abcservicesimulator.service;

import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mavenir.mbmp.abcservicesimulator.PropertiesLoader;
import com.mavenir.mbmp.abcservicesimulator.domain.dto.NBMessage;
import com.mavenir.mbmp.abcservicesimulator.utils.LoggerUtils;
import com.mavenir.mbmp.abcservicesimulator.utils.NBMessageFromFileUtil;
import com.mavenir.mbmp.abcservicesimulator.utils.RequestSendingThread;
import com.mavenir.mbmp.abcservicesimulator.utils.constants.ABCRequestHandlerConstants;
import com.mavenir.mbmp.abcservicesimulator.web.NBMessageSender;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ABCRequestHandlerServiceImpl implements ABCRequestHandlerService, ABCRequestHandlerConstants {

	private final NBMessageSender messageSender;
	private final PropertiesLoader properties;

	@Autowired
	public ABCRequestHandlerServiceImpl(NBMessageSender messageSender, PropertiesLoader properties) {
		this.messageSender = messageSender;
		this.properties = properties;
	}

	@Override
	public Optional<NBMessage> sendMessageToSubscriber(NBMessage interaction) {
		Optional<NBMessage> optionalMessage = Optional.empty();
		if (properties.checkIfResponseNeedsToBeSent()) {
			try {
				NBMessageFromFileUtil.loadNbMessagePostbackTemplateFromFile().ifPresent(message -> {
					log.debug("Messag:{}" + message);
					message.getRcsMessage().getSuggestedResponse().getResponse().getReply().getPostback()
							.setData(properties.getCallbackString());
					new RequestSendingThread(messageSender, message, Optional.empty()).start();
					log.debug("ABCRequestHandlerServiceImpl.sendMessageToSubscriber::Submitted interaction to thread");
				});

				optionalMessage = NBMessageFromFileUtil.load200ResponseNbMessageFromFile();
			} catch (Exception ex) {
				Optional<NBMessage> errMsg = NBMessageFromFileUtil.load500ResponseNbMessageFromFile();
				if (errMsg.isPresent()) {
					NBMessage message = errMsg.get();
					message.getRcsMessage().setTextMessage(ex.getMessage());
					optionalMessage = Optional.of(message);
				}
			}
		}
		return optionalMessage;
	}

	@PostConstruct
	public void sendHelloMessage() {
		NBMessageFromFileUtil.loadHelloNbMessageFromFile().ifPresent(message -> {
			LoggerUtils.logOutgoingMessage(message);
			new RequestSendingThread(messageSender, message, Optional.of(WAITTIMEOUT)).start();
		});
	}

}
