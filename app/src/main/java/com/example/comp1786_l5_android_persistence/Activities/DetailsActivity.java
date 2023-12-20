package com.example.comp1786_l5_android_persistence.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.comp1786_l5_android_persistence.Models.Person;
import com.example.comp1786_l5_android_persistence.R;
import com.example.comp1786_l5_android_persistence.database.AppDatabase;

import java.util.List;


public class DetailsActivity extends AppCompatActivity {
    private AppDatabase appDatabase;
    private RecyclerView recyclerView;
    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "contact_db")
                .allowMainThreadQueries() // For simplicity, don't use this in production
                .build();

        Button add_button = findViewById(R.id.add_button);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Person> persons = appDatabase.personDao().getAllPersons();

        adapter = new ContactAdapter(persons);
        recyclerView.setAdapter(adapter);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
//                Intent intent = new Intent(DetailsActivity.this, MainActivity.class);
//                startActivity(intent);
            }
        });
    }
}

