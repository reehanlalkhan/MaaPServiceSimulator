package com.mavenir.mbmp.abcservicesimulator.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mavenir.mbmp.abcservicesimulator.domain.dto.NBMessage;
import com.mavenir.mbmp.abcservicesimulator.utils.constants.ABCRequestHandlerConstants;

public class LoggerUtils implements ABCRequestHandlerConstants {
	private final static ObjectMapper jsonMapper = new ObjectMapper();

	public static void logIncomingMessage(NBMessage nbMessage) {
		logMessage(nbMessage, "Incoming request from MBMP to MaaP");
	}

	public static void logOutgoingMessage(NBMessage nbMessage) {
		logMessage(nbMessage, "Outgoing request from MaaP to MBMP");
	}

	public static void logResponseMessage(NBMessage nbMessage) {
		logMessage(nbMessage, "Incoming response from MBMP to MaaP");
	}

	private static void logMessage(NBMessage nbMessage, String message) {
		jsonMapper.setSerializationInclusion(Include.NON_NULL);
		try {
			String nbMessageStr = jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(nbMessage);
			MBMPLog.debug(message + ":\n" + nbMessageStr);
			MBMPLog.debug("======================================================================");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
