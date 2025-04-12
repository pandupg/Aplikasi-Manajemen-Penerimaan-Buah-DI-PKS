package com.example.databuahpks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.databuahpks.Database.AppDatabase;
import com.example.databuahpks.Database.Truck;

public class InputTruckActivity extends AppCompatActivity {

    EditText etKodeTruck, etNamaPengemudi;
    Button btnSimpan;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_truck);

        etKodeTruck = findViewById(R.id.et_kode_truck);
        etNamaPengemudi = findViewById(R.id.et_nama_pengemudi);
        btnSimpan = findViewById(R.id.btn_simpan_truck);

        db = AppDatabase.getInstance(this);

        btnSimpan.setOnClickListener(v -> {
            String kode = etKodeTruck.getText().toString();
            String nama = etNamaPengemudi.getText().toString();

            Truck truck = new Truck(kode, nama);

            new Thread(() -> {
                db.truckDao().insert(truck);
                runOnUiThread(() -> {
                    Toast.makeText(this, "Truck disimpan!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(InputTruckActivity.this, InputBuahActivity.class);
                    intent.putExtra("kode_truck", kode); // Mengirimkan kode_truck
                    startActivity(intent);
                    finish(); // tutup halaman input_truck
                });
            }).start();
        });
    }
}