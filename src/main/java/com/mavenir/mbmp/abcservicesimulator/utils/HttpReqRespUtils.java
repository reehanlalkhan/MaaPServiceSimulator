package com.mavenir.mbmp.abcservicesimulator.utils;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class HttpReqRespUtils {

	private static final String[] IP_HEADER_CANDIDATES = { "X-Forwarded-For", "Proxy-Client-IP", "WL-Proxy-Client-IP",
			"HTTP_X_FORWARDED_FOR", "HTTP_X_FORWARDED", "HTTP_X_CLUSTER_CLIENT_IP", "HTTP_CLIENT_IP",
			"HTTP_FORWARDED_FOR", "HTTP_FORWARDED", "HTTP_VIA", "REMOTE_ADDR" };

	public static String getClientIpAddressIfServletRequestExist() {

		if (RequestContextHolder.getRequestAttributes() == null) {
			return "0.0.0.0";
		}

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String ip = Arrays.asList(IP_HEADER_CANDIDATES).stream().map(request::getHeader)
				.filter(h -> h != null && h.length() != 0 && !"unknown".equalsIgnoreCase(h)).map(h -> h.split(",")[0])
				.reduce("", (h1, h2) -> h1 + ":" + h2);
		if (request.getRemoteAddr().startsWith("0")) {
			ip = "127.0.0.1";
		}
		return ip;
	}
}