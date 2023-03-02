package com.einstein.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.einstein.model.EmailId;
import com.einstein.model.User;
@Repository
public interface EmailRepository extends JpaRepository<EmailId, Integer> {
	public EmailId getEmailByEmailId(String emailId);
}
