package com.example.notepad;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        EditText etTitle = findViewById(R.id.editTextTitle);
        EditText etContent = findViewById(R.id.editTextContent);
        Button btnSave = findViewById(R.id.buttonSave);

        btnSave.setOnClickListener(v -> {
            String title = etTitle.getText().toString().trim();
            String content = etContent.getText().toString().trim();
            if (!title.isEmpty() && !content.isEmpty()) {
                NoteViewModel viewModel = new ViewModelProvider(this).get(NoteViewModel.class);
                viewModel.insert(new Note(title, content));
            }
            finish();
        });
    }
          }
