package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class UserActivity extends AppCompatActivity {
    private EditText noteTextBox;
    private Button delButton;
    private DatabaseAdapter adapter;
    private long NoteId=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        noteTextBox = findViewById(R.id.NoteText);
        delButton = findViewById(R.id.deleteButton);

        adapter = new DatabaseAdapter(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            NoteId = extras.getLong("id");
        }

        if (NoteId > 0) {

            adapter.open();
            Note note = adapter.getUser(NoteId);
            noteTextBox.setText(note.getNoteText());

            adapter.close();
        } else {
            delButton.setVisibility(View.GONE);
        }
    }

    public void save(View view){

        String noteText = noteTextBox.getText().toString();
        Note note = new Note(NoteId, noteText);

        adapter.open();
        if (NoteId > 0) {
            adapter.update(note);
        } else {
            adapter.insert(note);
        }
        adapter.close();
        Toast.makeText(this, "Заметка успешно сохранена!", Toast.LENGTH_SHORT).show();
        goHome();
    }
    public void delete(View view){

        adapter.open();
        adapter.delete(NoteId);
        adapter.close();
        Toast.makeText(this, "Заметка успешно удалена!", Toast.LENGTH_SHORT).show();
        goHome();
    }

    private void goHome(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
    public void showDialog(View v){
        CustomDialogFragment dialog = new CustomDialogFragment();
        dialog.show(getSupportFragmentManager(), "custom");
    }
}
