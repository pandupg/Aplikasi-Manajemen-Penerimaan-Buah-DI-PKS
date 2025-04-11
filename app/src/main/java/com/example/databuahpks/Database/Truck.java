package com.example.databuahpks.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "trucks")
public class Truck {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "kode_truck")
    public String kodeTruck;

    @ColumnInfo(name = "nama_pengemudi")
    public String namaPengemudi;

    public Truck(String kodeTruck, String namaPengemudi) {
        this.kodeTruck = kodeTruck;
        this.namaPengemudi = namaPengemudi;
    }
}

