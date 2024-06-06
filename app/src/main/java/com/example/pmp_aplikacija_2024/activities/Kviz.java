package com.example.pmp_aplikacija_2024;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Kviz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kviz);
    }

    public void Klikni(View view) {
        Intent intent = new Intent(Kviz.this, JavaActivity.class);
        startActivity(intent);
    }

    public void Klikk(View view) {
        Intent intent = new Intent(Kviz.this, PajtonActivity.class);
        startActivity(intent);
    }

    public void Klick(View view) {
        Intent intent = new Intent(Kviz.this, AndroidActivity.class);
        startActivity(intent);
    }
}