package com.userloginapp.service;

import com.userloginapp.dto.UserDTO;
import com.userloginapp.exception.UserException;

public interface UserService {
	public String postUserData(UserDTO user) throws UserException;
	public String authenticateUser(String name, String password);
}
