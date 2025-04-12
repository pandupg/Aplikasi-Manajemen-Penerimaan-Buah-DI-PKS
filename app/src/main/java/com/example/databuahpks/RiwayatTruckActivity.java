package com.example.databuahpks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.databuahpks.Adapter.TruckAdapter;
import com.example.databuahpks.Database.AppDatabase;
import com.example.databuahpks.Database.Truck;
import java.util.List;

public class RiwayatTruckActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TruckAdapter adapter;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_truck);

        recyclerView = findViewById(R.id.recyclerViewTruck);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = AppDatabase.getInstance(this);

        // Observe LiveData dari Room
        db.truckDao().getAllTrucks().observe(this, new Observer<List<Truck>>() {
            @Override
            public void onChanged(List<Truck> truckList) {
                adapter = new TruckAdapter(RiwayatTruckActivity.this, truckList, truck -> {
                    // TODO: pindah ke halaman buah sesuai truck
                });
                recyclerView.setAdapter(adapter);
            }
        });
    }
}