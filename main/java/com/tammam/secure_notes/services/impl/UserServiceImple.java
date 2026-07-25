package com.tammam.secure_notes.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tammam.secure_notes.dto.UserDto;
import com.tammam.secure_notes.models.AppRole;
import com.tammam.secure_notes.models.Role;
import com.tammam.secure_notes.models.User;
import com.tammam.secure_notes.repositories.RoleRepository;
import com.tammam.secure_notes.repositories.UserRepository;
import com.tammam.secure_notes.services.UserService;
@Service
public class UserServiceImple  implements UserService{
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public void updateUserRole(Long userId, String roleName) {
		User user = userRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("user not found"));
		AppRole appRole = AppRole.valueOf(roleName);
		Role role = roleRepository.findByRoleName(appRole).orElseThrow(()->new RuntimeException("role Not found Exception"));
		user.setRole(role);
		userRepository.save(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = userRepository.findAll();
		
		return users.stream().map((user)->UserDto.convertToDto(user)).collect(Collectors.toList());
	}

	@Override
	public UserDto getUserById(Long id) {
		 User user = userRepository.findById(id).get();
		return UserDto.convertToDto(user) ;
	}

}
