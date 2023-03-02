package com.authentication.einstein.service;

import org.springframework.stereotype.Component;

@Component
public class ConstantFile {

	public String getJwt(String jwt) {
		return jwt.substring(7);
	}
}
