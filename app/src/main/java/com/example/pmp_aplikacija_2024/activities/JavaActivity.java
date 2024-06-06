package com.example.pmp_aplikacija_2024;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class JavaActivity extends AppCompatActivity {

    private TextView prasanjaa;
    private RadioGroup izbor;
    private Button kopce;
    private String[] prasanja;
    private String[][] odgovor;
    private int[] tocniodgovori = {3, 2, 1, 0, 2, 0};
    private int index = 0;
    private int poeni = 0;
    private List<Integer> pogresniOdgovori = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);

        Resources res = getResources();

        prasanja = new String[]{
                res.getString(R.string.prasanje1),
                res.getString(R.string.prasanje2),
                res.getString(R.string.prasanje3),
                res.getString(R.string.prasanje4),
                res.getString(R.string.prasanje5),
                res.getString(R.string.prasanje6)
        };

        odgovor = new String[][]{
                res.getStringArray(R.array.odgovor1),
                res.getStringArray(R.array.odgovor2),
                res.getStringArray(R.array.odgovor3),
                res.getStringArray(R.array.odgovor4),
                res.getStringArray(R.array.odgovor5),
                res.getStringArray(R.array.odgovor6)
        };

        prasanjaa = findViewById(R.id.prasanjaa);
        izbor = findViewById(R.id.izbor);
        kopce = findViewById(R.id.kopce);

        showQuestion();

        kopce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
            }
        });
    }

    private void showQuestion() {
        prasanjaa.setText(prasanja[index]);

        for (int i = 0; i < izbor.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) izbor.getChildAt(i);
            radioButton.setText(odgovor[index][i]);

            radioButton.setEnabled(true);
        }
    }

    private void checkAnswer() {
        int selectedRadioButtonId = izbor.getCheckedRadioButtonId();
        if (selectedRadioButtonId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            int selectedAnswerIndex = izbor.indexOfChild(selectedRadioButton);
            int correctAnswerIndex = tocniodgovori[index];
            if (selectedAnswerIndex == correctAnswerIndex) {
                poeni++;
            } else {
                pogresniOdgovori.add(index);
            }

            for (int i = 0; i < izbor.getChildCount(); i++) {
                izbor.getChildAt(i).setEnabled(false);
            }
            index++;
            if (index < prasanja.length) {
                showQuestion();
            } else {
                showResult();
            }
        } else {
            Toast.makeText(JavaActivity.this, getResources().getString(R.string.izberiodg), Toast.LENGTH_SHORT).show();
        }
    }

    private void showResult() {
        double presmetaj = ((double)poeni / prasanja.length) * 100;
        String result;
        if (presmetaj >= 80) {
            result = getResources().getString(R.string.ocenka1);
        } else if (presmetaj >= 60) {
            result = getResources().getString(R.string.ocenka2);
        } else if (presmetaj >= 40) {
            result = getResources().getString(R.string.ocenka3);
        } else {
            result = getResources().getString(R.string.ocenka4);
        }

        String message = getResources().getString(R.string.rezultat) + result + ". " + getResources().getString(R.string.tocenodgovor) + " " + poeni;

        StringBuilder pogresniPrasanja = new StringBuilder();
        for (Integer pogresnoPrasanjeIndex : pogresniOdgovori) {
            pogresniPrasanja.append(prasanja[pogresnoPrasanjeIndex]).append("\n");
            pogresniPrasanja.append(getResources().getString(R.string.tocenodgovor)).append(odgovor[pogresnoPrasanjeIndex][tocniodgovori[pogresnoPrasanjeIndex]]).append("\n\n");
        }

        String porakaZaPogresni = getResources().getString(R.string.pogresenodgovor) + "\n" + pogresniPrasanja.toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.rez))
                .setMessage(message + "\n\n" + porakaZaPogresni)
                .setPositiveButton("OK", null)
                .show();
    }
}
