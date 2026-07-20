package com.tammam.secure_notes.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tammam.secure_notes.models.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
	
	Optional<User> findByUserName(String name);
	Boolean existsByUserName (String userName);

}
