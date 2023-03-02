package com.authentication.einstein.service;

import com.authentication.einstein.model.PersonalDetails;
import com.authentication.einstein.model.UserRegistration;

public interface UserDetailsService {

	public void savePersonalDetails(UserRegistration userRegistration);
	
	public PersonalDetails getPersonalDetials(String jwt);
}
