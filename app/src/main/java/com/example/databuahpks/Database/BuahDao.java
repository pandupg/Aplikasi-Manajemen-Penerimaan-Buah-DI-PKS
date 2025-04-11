package com.example.databuahpks.Database;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface BuahDao {
    @Insert
    void insert(Buah buah);
}
