package com.einstein.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.einstein.model.EmailId;
import com.einstein.model.Product;
import com.einstein.service.EmailService;
@RestController
public class EmailController {

	@Autowired
	EmailService email;
	
	@PostMapping("/addmail")
	public ResponseEntity<?> saveEmail(@RequestBody EmailId emailid) {
		
		EmailId item=email.saveEmail(emailid);
		return new ResponseEntity<>(item, HttpStatus.OK);
	}
}
