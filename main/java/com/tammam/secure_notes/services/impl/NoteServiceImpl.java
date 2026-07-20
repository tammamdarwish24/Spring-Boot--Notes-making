package com.tammam.secure_notes.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tammam.secure_notes.models.Note;
import com.tammam.secure_notes.repositories.NoteRepository;
import com.tammam.secure_notes.services.NoteService;
@Service
public class NoteServiceImpl implements NoteService{
	@Autowired
	private NoteRepository noteRepository;

	@Override
	public Note createNoteForUser(String userName, String content) {
     
		Note note = new Note();
		note.setContent(content);
	    //note.setContent(content);
	    note.setOwnerUserName(userName);
	    Note savedNote = noteRepository.save(note);
		return savedNote ;
	}

	@Override
	public Note updateNoteForUser(Long noteId, String content, String username) {
	
		Note updatedNote = noteRepository.findById(noteId).orElseThrow(()->new RuntimeException("note doesn't exist"));
		updatedNote.setContent(content);
	    return updatedNote ;
	}

	@Override
	public void deleteNoteForUser(Long noteId, String username) {
		
		noteRepository.deleteById(noteId);
	}

	@Override
	public List<Note> getNotesForUser(String username) {
		List<Note> personalNotes  = noteRepository.findAll();
		return personalNotes;
	}

}
