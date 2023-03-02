package com.einstein.model;

import org.springframework.stereotype.Component;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ValidateResponse {

	String jwt;
	
}
