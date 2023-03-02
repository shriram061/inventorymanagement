package com.authentication.einstein.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.authentication.einstein.controller.FeignUserController;
import com.authentication.einstein.model.PersonalDetails;
import com.authentication.einstein.model.UserDetail;


@Service
public class UserDetailService implements UserDetailsService{
	

	@Autowired
	private FeignUserController feigncontroller;
	
	
	@Override
	public UserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDetail userDetail = null;
		PersonalDetails personalDetails = feigncontroller.getUserName(username);
		if(personalDetails!=null) {
			 userDetail= new UserDetail(feigncontroller.getUserName(username));
		}
		
		return userDetail;
	}
	
}
