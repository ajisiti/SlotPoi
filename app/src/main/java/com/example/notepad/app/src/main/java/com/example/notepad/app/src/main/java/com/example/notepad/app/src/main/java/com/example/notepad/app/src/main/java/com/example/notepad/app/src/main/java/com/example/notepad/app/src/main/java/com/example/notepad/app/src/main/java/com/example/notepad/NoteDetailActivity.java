package com.example.notepad;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class NoteDetailActivity extends AppCompatActivity {
    private NoteViewModel noteViewModel;
    private int noteId;
    private String title, content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        noteId = getIntent().getIntExtra("NOTE_ID", -1);
        title = getIntent().getStringExtra("NOTE_TITLE");
        content = getIntent().getStringExtra("NOTE_CONTENT");

        TextView tvTitle = findViewById(R.id.detailTitle);
        TextView tvContent = findViewById(R.id.detailContent);
        tvTitle.setText(title);
        tvContent.setText(content);

        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_SUBJECT, title);
            sendIntent.putExtra(Intent.EXTRA_TEXT, title + "\n\n" + content);
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, "Bagikan catatan via:"));
            return true;
        } else if (item.getItemId() == R.id.action_delete) {
            Note note = new Note(title, content);
            note.setId(noteId);
            noteViewModel.delete(note);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
