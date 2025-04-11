package com.example.databuahpks.Database;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface TruckDao {
    @Insert
    void insert(Truck truck);
}
