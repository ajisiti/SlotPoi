package com.example.notepad;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository repository;
    private MutableLiveData<List<Note>> allNotes;

    public NoteViewModel(Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = new MutableLiveData<>();
        loadNotes();
    }

    public void loadNotes() {
        allNotes.postValue(repository.getAllNotes());
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    public void insert(Note note) {
        repository.insert(note);
        loadNotes();
    }

    public void delete(Note note) {
        repository.delete(note);
        loadNotes();
    }
  }
