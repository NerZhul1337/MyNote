package com.example.mynotes;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity { private ListView userList;
    ArrayAdapter<Note> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userList = findViewById(R.id.list);

        userList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long id) {
                Note note = arrayAdapter.getItem(position);
                if(note!=null) {
                    Intent intent = new Intent(getApplicationContext(),
                            UserActivity.class);
                    intent.putExtra("id",note.getId()); startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        DatabaseAdapter adapter = new DatabaseAdapter(this);
        adapter.open();

        List<Note> notes = adapter.getUsers();

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);
        userList.setAdapter(arrayAdapter);
        adapter.close();
    }
    public void add(View view){
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }
}
