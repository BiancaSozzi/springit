package com.spring.course.springit.service;

import com.spring.course.springit.domain.User;
import com.spring.course.springit.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

	private final Logger logger = LoggerFactory.getLogger(UserService.class);
	private final UserRepository userRepository;
	private final RoleService roleService;
	private final BCryptPasswordEncoder encoder;
	private final MailService mailService;

	public UserService(UserRepository userRepository, RoleService roleService, MailService mailService) {
		this.userRepository = userRepository;
		this.roleService = roleService;
		this.mailService = mailService;
		encoder = new BCryptPasswordEncoder();
	}

	public User register(User user) {
		// Take password from the code and encode it
		String secret = "{bcrypt}" + encoder.encode(user.getPassword());
		user.setPassword(secret);

		// Confirm password
		user.setConfirmPassword(secret); // we need to set it because if not the validator will trigger constraint error

		// assign a role to the user (always type user for now)
		user.addRole(roleService.findByName("ROLE_USER"));

		// set an activation code
		user.setActivationCode(UUID.randomUUID().toString());

		// disable the user before saving
		user.setEnabled(false);

		// save user
		save(user);

		// send activation email
		sendActivationEmail(user);

		// return the user
		return user;
	}

	public User save(User user) {
		return  userRepository.save(user);
	}

	@Transactional // will save all users in the list or none. To use it enable it by using @EnableTransactionManagement
	public void saveUsers(User... users) {
		for (User user: users) {
			logger.info("Saving User: " + user.getEmail());
			userRepository.save(user);
		}
	}

	public void sendActivationEmail(User user) {
		mailService.sendActivationEmail(user);
	}

	public void sendWelcomeEmail(User user) {
		mailService.sendWelcomeEmail(user);
	}

	public Optional<User> findByEmailAndActivationCode(String email, String activationCode) {
		return userRepository.findByEmailAndActivationCode(email, activationCode);
	}
}
