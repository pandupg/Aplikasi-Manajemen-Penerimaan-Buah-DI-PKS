package com.example.databuahpks;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databuahpks.Adapter.TruckAdapter;
import com.example.databuahpks.Database.AppDatabase;
import com.example.databuahpks.Database.Truck;

import java.util.ArrayList;
import java.util.List;

public class RiwayatTruckActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TruckAdapter adapter;
    private AppDatabase db;
    private List<Truck> truckList = new ArrayList<>(); // menyimpan daftar truk

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_truck);

        recyclerView = findViewById(R.id.recyclerViewTruck);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = AppDatabase.getInstance(this);

        adapter = new TruckAdapter(this, truckList,
                truck -> {
                    // Klik item untuk lihat buah
                    Intent intent = new Intent(RiwayatTruckActivity.this, RiwayatBuahActivity.class);
                    intent.putExtra("kode_truck", truck.getKodeTruck());
                    startActivity(intent);
                },
                truck -> {
                    // klik tombol hapus → tampilkan konfirmasi
                    new AlertDialog.Builder(RiwayatTruckActivity.this)
                            .setTitle("Konfirmasi Hapus")
                            .setMessage("Apakah Anda yakin ingin menghapus truk ini?")
                            .setPositiveButton("Ya", (dialog, which) -> {
                                new Thread(() -> db.truckDao().delete(truck)).start();
                            })
                            .setNegativeButton("Batal", null)
                            .show();
                }
        );

        recyclerView.setAdapter(adapter);

        db.truckDao().getAllTrucks().observe(this, new Observer<List<Truck>>() {
            @Override
            public void onChanged(List<Truck> trucks) {
                // Menyaring hanya kode truck yang unik
                List<Truck> uniqueTrucks = new ArrayList<>();
                List<String> uniqueKodeTruck = new ArrayList<>();

                // Menyaring data truck agar hanya kode truck yang unik
                for (Truck truck : trucks) {
                    if (!uniqueKodeTruck.contains(truck.getKodeTruck())) {
                        uniqueKodeTruck.add(truck.getKodeTruck());
                        uniqueTrucks.add(truck);
                    }
                }

                // Update truckList dengan data truck yang sudah difilter
                truckList.clear();
                truckList.addAll(uniqueTrucks);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
