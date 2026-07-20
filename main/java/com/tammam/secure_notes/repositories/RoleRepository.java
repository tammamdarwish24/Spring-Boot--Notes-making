package com.tammam.secure_notes.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tammam.secure_notes.models.AppRole;
import com.tammam.secure_notes.models.Role;

public interface RoleRepository extends JpaRepository<Role,Long>{
	
 Optional<Role>findByRoleName(AppRole appRole);

}
