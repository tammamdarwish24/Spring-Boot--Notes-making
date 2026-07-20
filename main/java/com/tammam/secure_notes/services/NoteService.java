package com.tammam.secure_notes.services;

import java.util.List;

import com.tammam.secure_notes.models.Note;

public interface NoteService {

	Note createNoteForUser(String userName,String content);
    Note updateNoteForUser(Long noteId,String content,String username);
    void deleteNoteForUser(Long noteId,String username);
    List<Note> getNotesForUser(String username);
}
