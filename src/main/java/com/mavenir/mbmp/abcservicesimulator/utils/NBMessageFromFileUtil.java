package com.mavenir.mbmp.abcservicesimulator.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mavenir.mbmp.abcservicesimulator.domain.dto.NBMessage;
import com.mavenir.mbmp.abcservicesimulator.utils.constants.ABCRequestHandlerConstants;

public class NBMessageFromFileUtil implements ABCRequestHandlerConstants {
	private final static ObjectMapper jsonMapper = new ObjectMapper();

	private static Resource loadEmployeesWithClassPathResource(String fileName) {
		return new ClassPathResource(fileName);
	}

	public static Optional<NBMessage> loadNbMessageFromFile(String fileName) {
		try {
			fileName = TEMPLATES + SLASH + fileName;
			JSONParser parser = new JSONParser(loadEmployeesWithClassPathResource(fileName).getInputStream());
			return Optional.of(jsonMapper.convertValue(parser.parse(), NBMessage.class));
		} catch (IllegalArgumentException | ParseException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public static Optional<NBMessage> loadHelloNbMessageFromFile() {
		return loadNbMessageFromFile("hello.json");
	}

	public static Optional<NBMessage> load200ResponseNbMessageFromFile() {
		return loadNbMessageFromFile("200response.json");
	}

	public static Optional<NBMessage> loadNbMessagePostbackTemplateFromFile() {
		return loadNbMessageFromFile("postbackTemplate.json");
	}

	public static Optional<NBMessage> load500ResponseNbMessageFromFile() {
		return loadNbMessageFromFile("500response.json");
	}

	/*
	 * file.ifPresent(fileName -> { try (BufferedReader br = new BufferedReader(new
	 * FileReader(fileName))) { InputStream inJson =
	 * NBMessage.class.getResourceAsStream(fileName); NBMessage sample = new
	 * ObjectMapper().readValue(inJson, NBMessage.class); } catch (IOException e) {
	 * log.error("Exception is caught:" + e); e.printStackTrace(); } });
	 */
}
