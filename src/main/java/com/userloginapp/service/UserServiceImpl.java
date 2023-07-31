package com.userloginapp.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.userloginapp.dto.UserDTO;
import com.userloginapp.entity.User;
import com.userloginapp.exception.UserException;
import com.userloginapp.repository.UserRepository;

@Service(value = "userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public String postUserData(UserDTO userDto) throws UserException {

		User user = new User();
		if (userDto != null) {
			user.setEmail(userDto.getEmail());
			user.setName(userDto.getName());
			user.setPhone(userDto.getPhone());
			user.setPassword(encryptPassword(userDto.getPassword()));

			userRepository.save(user);
			return "Success";
		}
		return "Please Fill the Fields";
	}

	// To Encrypt password
	private static String encryptPassword(String pwd) {
		return Base64.getEncoder().encodeToString(pwd.getBytes());
	}

	// To Decrypt Password
	private static String decryptPassword(String pwd) {
		return new String(Base64.getMimeDecoder().decode(pwd));
	}

	@Override
	public String authenticateUser(String email, String password) {
		User user = userRepository.findByEmail(email);
		if (user != null && decryptPassword(user.getPassword()).equals(password)) {
			return "Login Success!!";
		}
		return "Invalid Credentials!!";
	}
}
