package com.userloginapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userloginapp.dto.UserDTO;
import com.userloginapp.exception.UserException;
import com.userloginapp.service.UserService;

@RestController
@RequestMapping(value = "/userAuth")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/registeruser")
	public ResponseEntity<String> registration(@RequestBody UserDTO userdto) throws UserException {
		String message = userService.postUserData(userdto);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@PostMapping("/login")
	public String login(@RequestBody UserDTO userdto) {
		return userService.authenticateUser(userdto.getEmail(), userdto.getPassword());
	}
}
