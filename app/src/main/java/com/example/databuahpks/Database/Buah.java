package com.example.databuahpks.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "buah")
public class Buah {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "berat_datang")
    public double beratDatang;

    @ColumnInfo(name = "berat_pulang")
    public double beratPulang;

    @ColumnInfo(name = "jumlah_matang")
    public int jumlahMatang;

    @ColumnInfo(name = "jumlah_mentah")
    public int jumlahMentah;

    @ColumnInfo(name = "jumlah_kematangan")
    public int jumlahKematangan;

    @ColumnInfo(name = "jumlah_busuk")
    public int jumlahBusuk;

    public Buah(double beratDatang, double beratPulang, int jumlahMatang, int jumlahMentah, int jumlahKematangan, int jumlahBusuk) {
        this.beratDatang = beratDatang;
        this.beratPulang = beratPulang;
        this.jumlahMatang = jumlahMatang;
        this.jumlahMentah = jumlahMentah;
        this.jumlahKematangan = jumlahKematangan;
        this.jumlahBusuk = jumlahBusuk;
    }
}

