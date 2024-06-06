package com.example.pmp_aplikacija_2024;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Korisnik.class}, version = 1)
public abstract class Baza extends RoomDatabase {
    public abstract KorisnikDao korisnikDao();
}
