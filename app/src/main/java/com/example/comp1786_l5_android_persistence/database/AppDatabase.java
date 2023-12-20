package com.example.comp1786_l5_android_persistence.database;// /database/AppDatabase.java
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.comp1786_l5_android_persistence.Models.Person;
import com.example.comp1786_l5_android_persistence.dao.PersonDao;

@Database(entities = {Person.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PersonDao personDao();
}