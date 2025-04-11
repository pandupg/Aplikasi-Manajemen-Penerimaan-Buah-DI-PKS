package com.example.databuahpks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.databuahpks.Database.AppDatabase;
import com.example.databuahpks.Database.Buah;

public class InputBuahActivity extends AppCompatActivity {

    EditText etBeratDatang, etBeratPulang, etMatang, etMentah, etKematangan, etBusuk;
    Button btnSimpan;
    AppDatabase db;

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

        btnSimpan.setOnClickListener(v -> {
            double datang = Double.parseDouble(etBeratDatang.getText().toString());
            double pulang = Double.parseDouble(etBeratPulang.getText().toString());
            int matang = Integer.parseInt(etMatang.getText().toString());
            int mentah = Integer.parseInt(etMentah.getText().toString());
            int kematangan = Integer.parseInt(etKematangan.getText().toString());
            int busuk = Integer.parseInt(etBusuk.getText().toString());

            Buah buah = new Buah(datang, pulang, matang, mentah, kematangan, busuk);

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