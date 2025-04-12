package com.example.databuahpks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.databuahpks.Database.AppDatabase;
import com.example.databuahpks.Database.Buah;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class InputBuahActivity extends AppCompatActivity {

    EditText etBeratDatang, etBeratPulang, etMatang, etMentah, etKematangan, etBusuk;
    Button btnSimpan;
    AppDatabase db;
    String kodeTruck; // Menyimpan kode_truck yang diterima

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_buah);

        etBeratDatang = findViewById(R.id.et_berat_datang);
        etBeratPulang = findViewById(R.id.et_berat_pulang);
        etMatang = findViewById(R.id.et_jumlah_matang);
        etMentah = findViewById(R.id.et_jumlah_mentah);
        etKematangan = findViewById(R.id.et_jumlah_kematangan);
        etBusuk = findViewById(R.id.et_jumlah_busuk);
        btnSimpan = findViewById(R.id.btn_simpan_buah);

        db = AppDatabase.getInstance(this);

        // Ambil kode_truck yang dikirimkan dari InputTruckActivity
        kodeTruck = getIntent().getStringExtra("kode_truck");

        btnSimpan.setOnClickListener(v -> {
            double datang = Double.parseDouble(etBeratDatang.getText().toString());
            double pulang = Double.parseDouble(etBeratPulang.getText().toString());
            int matang = Integer.parseInt(etMatang.getText().toString());
            int mentah = Integer.parseInt(etMentah.getText().toString());
            int kematangan = Integer.parseInt(etKematangan.getText().toString());
            int busuk = Integer.parseInt(etBusuk.getText().toString());

            SimpleDateFormat sdfHari = new SimpleDateFormat("EEEE", Locale.getDefault());
            SimpleDateFormat sdfTanggal = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
            SimpleDateFormat sdfWaktu = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

            Date sekarang = new Date();

            String hari = sdfHari.format(sekarang);
            String tanggal = sdfTanggal.format(sekarang);
            String waktu = sdfWaktu.format(sekarang);

            Buah buah = new Buah(datang, pulang, matang, mentah, kematangan, busuk, hari, tanggal, waktu, kodeTruck);

            new Thread(() -> {
                db.buahDao().insert(buah);
                runOnUiThread(() -> {
                    double total = matang + mentah + kematangan + busuk;
                    double persenMasuk = 100.0 * matang / total;

                    Toast.makeText(this, "Data buah disimpan. Buah masuk: " + persenMasuk + "%", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(InputBuahActivity.this, DashboardActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                });
            }).start();
        });
    }
}