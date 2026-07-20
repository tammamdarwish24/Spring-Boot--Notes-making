package com.tammam.secure_notes.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tammam.secure_notes.models.User;
import com.tammam.secure_notes.repositories.UserRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
   @Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
	 User user = userRepository.findByUserName(username).
			 orElseThrow(()-> new UsernameNotFoundException ("user name not found"+username));
	return UserDetailsImpl.buid(user);
	
	}

}
