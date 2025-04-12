package com.example.databuahpks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.databuahpks.Adapter.BuahAdapter;
import com.example.databuahpks.Database.AppDatabase;
import com.example.databuahpks.Database.Buah;
import java.util.List;

public class RiwayatBuahActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private BuahAdapter adapter;
    private AppDatabase db;
    private String kodeTruck;  // Kode truck yang diteruskan dari RiwayatTruckActivity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_buah);

        // Ambil kode truck dari Intent
        kodeTruck = getIntent().getStringExtra("kode_truck");

        recyclerView = findViewById(R.id.recyclerViewBuah);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = AppDatabase.getInstance(this);

        // Mengambil data buah yang terkait dengan kode truck
        db.buahDao().getBuahByKodeTruck(kodeTruck).observe(this, new Observer<List<Buah>>() {
            @Override
            public void onChanged(List<Buah> buahList) {
                adapter = new BuahAdapter(RiwayatBuahActivity.this, buahList);
                recyclerView.setAdapter(adapter);
            }
        });
    }
}