package com.example.notepad;

import android.content.Context;
import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;

    public NoteRepository(Context context) {
        NoteDatabase db = NoteDatabase.getDatabase(context);
        noteDao = db.noteDao();
    }

    public List<Note> getAllNotes() {
        return noteDao.getAllNotes();
    }

    public void insert(Note note) {
        NoteDatabase.databaseWriteExecutor.execute(() -> {
            noteDao.insertNote(note);
        });
    }

    public void delete(Note note) {
        NoteDatabase.databaseWriteExecutor.execute(() -> {
            noteDao.deleteNote(note);
        });
    }
}
