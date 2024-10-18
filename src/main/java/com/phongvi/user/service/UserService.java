package com.phongvi.user.service;

import com.phongvi.user.User;
import com.phongvi.user.dto.UserCreateDTO;

public interface UserService {

	User createUser(User user);

	void registerUser(UserCreateDTO userDTO);

}
