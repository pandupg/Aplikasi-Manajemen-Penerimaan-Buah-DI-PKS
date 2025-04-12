package com.example.databuahpks.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TruckDao {
    @Insert
    void insert(Truck truck);

    @Delete
    void delete(Truck truck);

    @Query("SELECT * FROM trucks")
    LiveData<List<Truck>> getAllTrucks();

    @Query("SELECT * FROM trucks WHERE kode_truck = :kode LIMIT 1")
    LiveData<Truck> getTruckByKode(String kode);

    @Query("SELECT DISTINCT kode_truck FROM trucks")
    LiveData<List<String>> getDistinctKodeTrucks();
}
