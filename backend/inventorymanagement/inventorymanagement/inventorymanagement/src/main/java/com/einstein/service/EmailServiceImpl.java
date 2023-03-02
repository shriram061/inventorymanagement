package com.einstein.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.einstein.model.EmailId;
import com.einstein.repository.EmailRepository;
@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	EmailRepository repo;
	@Override
	public EmailId saveEmail(EmailId email) {
		// TODO Auto-generated method stub
		EmailId item = repo.save(email);
		return item;
	}
	

}
