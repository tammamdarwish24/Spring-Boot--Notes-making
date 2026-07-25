package com.tammam.secure_notes.services;

import java.util.List;

import com.tammam.secure_notes.dto.UserDto;

public interface UserService {

	
	void updateUserRole(Long userId,String roleName);
	List<UserDto>getAllUsers();
	UserDto getUserById(Long id);
	
}
