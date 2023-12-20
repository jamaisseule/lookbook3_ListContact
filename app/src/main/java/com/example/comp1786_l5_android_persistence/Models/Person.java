package com.example.comp1786_l5_android_persistence.Models;// /models/Person.java
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "persons")
public class Person {
    @PrimaryKey(autoGenerate = true)
    public long person_id;
    public String name;
    public String dob;
    public String email;
    public int image;
}