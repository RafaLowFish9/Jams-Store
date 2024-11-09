package com.example.jamsstore;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class day_of_birth_activity extends AppCompatActivity {
    //Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_day_of_birth);
//        spinner = findViewById(R.id.spinner);
//        //ArrayAdapter
//        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(
//                this,
//                R.array.dias_mes,
//                android.R.
//        )
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.day_of_birth_activity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}