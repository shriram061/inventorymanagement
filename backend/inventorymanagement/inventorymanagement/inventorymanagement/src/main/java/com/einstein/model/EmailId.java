package com.einstein.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailId {
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private int id;
	private String emailId;
	@OneToOne
    private User user;
}
