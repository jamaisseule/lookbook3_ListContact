package com.example.comp1786_l5_android_persistence.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.comp1786_l5_android_persistence.Models.Person;
import com.example.comp1786_l5_android_persistence.R;
import com.example.comp1786_l5_android_persistence.database.AppDatabase;

public class MainActivity extends AppCompatActivity {
    private AppDatabase appDatabase;
    private RadioButton image1, image2, image3;
    private int selectedOptionId = R.drawable.android; // Default to Option 1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "contact_db")
                .allowMainThreadQueries() // For simplicity, don't use this in production
                .build();

        Button saveDetailsButton = findViewById(R.id.saveDetailsButton);
        Button viewDetailsButton = findViewById(R.id.viewDetailsButton);

        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionSelected(1);
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionSelected(2);
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionSelected(3);
            }
        });

        saveDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDetails();

            }
        });
        viewDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveDetails() {
        EditText nameTxt = findViewById(R.id.nameText);
        EditText dobTxt = findViewById(R.id.dobText);
        EditText emailTxt = findViewById(R.id.emailText);

        String name = nameTxt.getText().toString();
        String dob = dobTxt.getText().toString();
        String email = emailTxt.getText().toString();

        Person person = new Person();
        person.name = name;
        person.dob = dob;
        person.email = email;
        person.image = selectedOptionId;

        long personId = appDatabase.personDao().insertPerson(person);

        Toast.makeText(this, "Person has been created with id: " + personId,
                Toast.LENGTH_LONG
        ).show();

        // Launch Details Activity
        Intent intent = new Intent(this, DetailsActivity.class);
        startActivity(intent);
    }
    private void onOptionSelected(int optionId) {
//        selectedOptionId = "drawable/image1.png";
        // Uncheck all checkboxes and check the selected one
        image1.setChecked(false);
        image2.setChecked(false);
        image3.setChecked(false);

        switch (optionId) {
            case 1:
                image1.setChecked(true);
                selectedOptionId = R.drawable.android;
                break;
            case 2:
                image2.setChecked(true);
                selectedOptionId = R.drawable.apple;
                break;
            case 3:
                image3.setChecked(true);
                selectedOptionId = R.drawable.card;
                break;
        }
    }
}