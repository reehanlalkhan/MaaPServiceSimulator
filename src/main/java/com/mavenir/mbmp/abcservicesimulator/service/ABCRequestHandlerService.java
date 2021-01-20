/**
 * 
 */
package com.mavenir.mbmp.abcservicesimulator.service;

import java.util.Optional;

import com.mavenir.mbmp.abcservicesimulator.domain.dto.NBMessage;

public interface ABCRequestHandlerService {

	Optional<NBMessage> sendMessageToSubscriber(NBMessage interaction);
}
