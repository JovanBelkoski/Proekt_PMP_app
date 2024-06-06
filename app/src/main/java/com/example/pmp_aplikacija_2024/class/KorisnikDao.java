package com.example.pmp_aplikacija_2024;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface KorisnikDao {
    @Insert
    void insertUser(Korisnik korisnik);

    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    Korisnik getUser(String username, String password);
}

