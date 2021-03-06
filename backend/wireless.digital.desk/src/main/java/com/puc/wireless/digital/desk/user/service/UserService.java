package com.puc.wireless.digital.desk.user.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	private static final String HEADER_KEY = "x-userinfo";
	private static final String JWT_KEY = "username";

	public Optional<String> getUsernameFromHeaders(Map<String, String> headers) {
			if(!headers.containsKey(HEADER_KEY)) {
				return Optional.empty();
			}
			
			byte[] bytes = Base64.getUrlDecoder().decode(headers.get(HEADER_KEY));
	        String decodedJwt = new String(bytes, StandardCharsets.UTF_8);
			JSONObject obj = new JSONObject(decodedJwt);
			
			if(obj.has(JWT_KEY)) {
				return Optional.of(obj.get(JWT_KEY).toString());
			} 
			return Optional.empty();
	}
	
}
