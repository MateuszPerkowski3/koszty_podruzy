package com.example.koszty_podruzy;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText etDystans = findViewById(R.id.etDystans);
        EditText etSpalanie = findViewById(R.id.etSpalanie);
        EditText etPasazerowie = findViewById(R.id.etPasazerowie);
        RadioButton rbBenzyna = findViewById(R.id.rbBenzyna);
        RadioButton rbDiesel = findViewById(R.id.rbDiesel);
        TextView tvWynik = findViewById(R.id.tvWynik);
        Button btnOblicz = findViewById(R.id.btnOblicz);


        btnOblicz.setOnClickListener(v -> {
            try {
                double dystans = Double.parseDouble(etDystans.getText().toString());
                double spalanie = Double.parseDouble(etSpalanie.getText().toString());
                int pasazerowie = Integer.parseInt(etPasazerowie.getText().toString());

                double cena = 3.10;
                if (rbBenzyna.isChecked()) {
                    cena = 6.50;
                } else if (rbDiesel.isChecked()) {
                    cena = 6.70;
                }

                double koszt = dystans * spalanie / 100 * cena;
                double naOsobe = koszt / pasazerowie;

                tvWynik.setTextColor(Color.GREEN);
                tvWynik.setText("Koszt całkowity: " + String.format("%.2f", koszt) + " zł\nKoszt na osobę: " + String.format("%.2f", naOsobe) + " zł");
            } catch (Exception e) {
                tvWynik.setTextColor(Color.RED);
                tvWynik.setText("Niepoprawne dane wejściowe!");
            }

        });
    };
};