package com.authentication.einstein.model;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AuthenticationResponse {
//	@NotNull
	private String name;
//	@NotNull
	private boolean isValid;
//	private String message;
}
