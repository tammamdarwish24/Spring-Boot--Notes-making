package com.tammam.secure_notes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tammam.secure_notes.models.Note;
@Repository
public interface NoteRepository extends JpaRepository<Note,Long>{
	
	List<Note>findByOwnerUserName(String name);

}
