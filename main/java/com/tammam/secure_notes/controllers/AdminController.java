package com.tammam.secure_notes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tammam.secure_notes.dto.UserDto;
import com.tammam.secure_notes.services.UserService;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {
	@Autowired
	private UserService userService;
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/getusers")
	public ResponseEntity<List<UserDto> > getAllUsers()
	{
		List<UserDto> allUsers = userService.getAllUsers();
		return new ResponseEntity<>(allUsers,HttpStatus.OK);
	}
    @PutMapping ("/update-role")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> updateUserRole(@RequestParam Long userId,@RequestParam String roleName)
	{
    	userService.updateUserRole(userId, roleName);
		return ResponseEntity.ok("user role updated") ;
		
	}
    @GetMapping("/user/{id}")
    public  ResponseEntity <UserDto> getUser(@PathVariable("id") Long userId)
    {
    	UserDto userById = userService.getUserById(userId);
    	return new ResponseEntity<>(userById,HttpStatus.OK);
    }
}
