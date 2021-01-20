package com.mavenir.mbmp.abcservicesimulator.utils.constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface ABCRequestHandlerConstants {

	String _logAppenderName = "MbmpLogger";
	String SLASH = "/";
	String VERSION = "v1";
	String BOT_VERSION = "bot" + SLASH + VERSION;
	String MESSAGE_REST_CALL = SLASH + BOT_VERSION + SLASH;

	String REQUEST_PATH_WITH_BOTID = "{botId}/messages";

	String DOT_STAR = ".*";

	// http://localhost:9001/api/mbmp/executor/AQ3Campaign:30debc5f-eae2-4d54-8660-a6eb0500be2c
	String BM_APP_PATH = "api/mbmp/executor/";
	String BM_CAMPAIGN_ID = "AQ3Campaign:30debc5f-eae2-4d54-8660-a6eb0500be2c";

	String BM_APP_URL = "http://127.0.0.1:9001" + SLASH + BM_APP_PATH + BM_CAMPAIGN_ID;

	int WAITTIMEOUT = 20000;

	String TEMPLATES = "templates";
	
	Logger MBMPLog = LoggerFactory.getLogger(_logAppenderName);
}
