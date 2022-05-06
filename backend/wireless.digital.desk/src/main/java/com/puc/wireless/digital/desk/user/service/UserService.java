package com.puc.wireless.digital.desk.user.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	private final static String HEADER_KEY = "x-userinfo";
	private final static String JWT_KEY = "username";

	public Optional<String> getUsernameFromHeaders(Map<String, String> headers) {
			if(!headers.containsKey("x-userinfo")) {
				return Optional.empty();
			}
			
			byte[] bytes = Base64.getUrlDecoder().decode(headers.get(HEADER_KEY));
	        String decodedJwt = new String(bytes, StandardCharsets.UTF_8);
			JSONObject obj = new JSONObject(decodedJwt);
			
			if(obj.has("username")) {
				return Optional.of(obj.get(JWT_KEY).toString());
			} 
			return Optional.empty();
	}
	
}
