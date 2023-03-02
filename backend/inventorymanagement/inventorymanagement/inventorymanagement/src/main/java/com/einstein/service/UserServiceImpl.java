package com.einstein.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.einstein.model.User;
import com.einstein.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository uDao;

	public List<User> getAllUsers() {
// TODO Auto-generated method stub
		return uDao.findAll();
	}
	
	public User saveToUserByUser(User prop) {
// TODO Auto-generated method stub
		return uDao.save(prop);
	}
	
	public User getUserByUserId(int userId) {
// TODO Auto-generated method stub
		return uDao.findByUserId(userId);
	}
	
	public User updateUser(int id, User userReq) {
		Optional<User> optuser=uDao.findById(id);
		if(!optuser.isPresent()) {
			throw new RuntimeException("user with id "+id+"does not exist");
		}
		User user=optuser.get();
		if(userReq.getUserName()!=null) {
			user.setUserName(userReq.getUserName());
			user.setEmailId(userReq.getEmailId());
		}
		if(userReq.getPassword()!=null) {
			user.setPassword(userReq.getPassword());
		}
		uDao.save(user);
		return user;
	}
	
	public User deleteUser(int id) {
		// TODO Auto-generated method stub
		User user = uDao.findByUserId(id);
		uDao.deleteById(id);
		return user;
	}

	public User getUserByUserName(String username) {
		// TODO Auto-generated method stub
		return uDao.getUserByUserName(username);
	}

}