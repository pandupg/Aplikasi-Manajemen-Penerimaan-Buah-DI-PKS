package com.example.databuahpks.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BuahDao {
    @Insert
    void insert(Buah buah);

    @Query("SELECT * FROM buah WHERE kode_truck = :kodeTruck")
    LiveData<List<Buah>> getBuahByKodeTruck(String kodeTruck);
}
