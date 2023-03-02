package com.authentication.einstein.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authentication.einstein.model.UserDetail;
import com.authentication.einstein.model.ValidateResponse;

@Service
public class ValidateResponseService {
	
	@Autowired
	private ValidateResponse validateResponse;
	
	public ValidateResponse getValidateResponse(String jwt,UserDetail userDetail) {
		validateResponse.setJwt(jwt);
		return validateResponse;
	}

}
