package com.example.databuahpks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.databuahpks.Database.AppDatabase;
import com.example.databuahpks.Database.Truck;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InputTruckActivity extends AppCompatActivity {

    AutoCompleteTextView inputKodeTruck;
    EditText etNamaPengemudi;
    Button btnSimpan;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_truck);

        inputKodeTruck = findViewById(R.id.inputKodeTruck);
        etNamaPengemudi = findViewById(R.id.et_nama_pengemudi);
        btnSimpan = findViewById(R.id.btn_simpan_truck);

        db = AppDatabase.getInstance(this);

        // Observe data dari database dan update AutoCompleteTextView
        db.truckDao().getAllTrucks().observe(this, new Observer<List<Truck>>() {
            @Override
            public void onChanged(List<Truck> truckList) {
                // Menghapus duplikat kode truck
                Set<String> kodeSet = new HashSet<>();
                for (Truck truck : truckList) {
                    kodeSet.add(truck.getKodeTruck()); // hanya simpan yang unik
                }

                List<String> kodeList = new ArrayList<>(kodeSet); // Mengonversi Set menjadi List
                ArrayAdapter<String> adapter = new ArrayAdapter<>(InputTruckActivity.this,
                        android.R.layout.simple_dropdown_item_1line, kodeList);
                inputKodeTruck.setAdapter(adapter);
            }
        });

        // Aksi ketika tombol simpan ditekan
        btnSimpan.setOnClickListener(v -> {
            String kode = inputKodeTruck.getText().toString();
            String nama = etNamaPengemudi.getText().toString();

            // Membuat objek Truck dan menyimpannya ke database
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
