package com.example.databuahpks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity {

    Button btnMulaiInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnMulaiInput = findViewById(R.id.btn_mulai_input);

        btnMulaiInput.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, InputTruckActivity.class);
            startActivity(intent);
        });

        Button btnRiwayat = findViewById(R.id.btnRiwayat);
        btnRiwayat.setOnClickListener(v -> {
            Intent intent = new Intent(DashboardActivity.this, RiwayatTruckActivity.class);
            startActivity(intent);
        });
    }
}