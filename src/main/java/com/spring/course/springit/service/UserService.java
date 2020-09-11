package com.spring.course.springit.service;

import com.spring.course.springit.domain.User;
import com.spring.course.springit.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final Logger logger = LoggerFactory.getLogger(UserService.class);
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User register(User user) {
		return user;
	}

	public User save(User user) {
		return  userRepository.save(user);
	}
}