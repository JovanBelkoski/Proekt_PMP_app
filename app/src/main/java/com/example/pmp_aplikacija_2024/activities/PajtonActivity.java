package com.example.pmp_aplikacija_2024;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class PajtonActivity extends AppCompatActivity {

    private TextView prasanjaaa;
    private RadioGroup izborr;
    private Button kopcee;
    private String[] prasanjaa;
    private String[][] odgovorr;
    private int[] tocniodgovorii = {1, 3, 2, 1, 1, 3};
    private int indexx = 0;
    private int poenii = 0;
    private List<Integer> pogresniOdgovorii = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pajton);

        Resources res = getResources();

        prasanjaa = new String[]{
                res.getString(R.string.prasanjee1),
                res.getString(R.string.prasanjee2),
                res.getString(R.string.prasanjee3),
                res.getString(R.string.prasanjee4),
                res.getString(R.string.prasanjee5),
                res.getString(R.string.prasanjee6)
        };

        odgovorr = new String[][]{
                res.getStringArray(R.array.odgovorr1),
                res.getStringArray(R.array.odgovorr2),
                res.getStringArray(R.array.odgovorr3),
                res.getStringArray(R.array.odgovorr4),
                res.getStringArray(R.array.odgovorr5),
                res.getStringArray(R.array.odgovorr6)
        };

        prasanjaaa = findViewById(R.id.prasanjaa);
        izborr = findViewById(R.id.izbor);
        kopcee = findViewById(R.id.kopce);

        showQuestion();

        kopcee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
            }
        });
    }

    private void showQuestion() {
        prasanjaaa.setText(prasanjaa[indexx]);

        for (int i = 0; i < izborr.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) izborr.getChildAt(i);
            radioButton.setText(odgovorr[indexx][i]);

            radioButton.setEnabled(true);
        }
    }

    private void checkAnswer() {
        int selectedRadioButtonId = izborr.getCheckedRadioButtonId();
        if (selectedRadioButtonId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            int selectedAnswerIndex = izborr.indexOfChild(selectedRadioButton);
            int correctAnswerIndex = tocniodgovorii[indexx];
            if (selectedAnswerIndex == correctAnswerIndex) {
                poenii++;
            } else {
                pogresniOdgovorii.add(indexx);
            }

            for (int i = 0; i < izborr.getChildCount(); i++) {
                izborr.getChildAt(i).setEnabled(false);
            }
            indexx++;
            if (indexx < prasanjaa.length) {
                showQuestion();
            } else {
                showResult();
            }
        } else {
            Toast.makeText(PajtonActivity.this, getResources().getString(R.string.izberiodg), Toast.LENGTH_SHORT).show();
        }
    }

    private void showResult() {
        double presmetaj = ((double)poenii / prasanjaa.length) * 100;
        String result;
        if (presmetaj >= 80) {
            result = getResources().getString(R.string.ocenkaa1);
        } else if (presmetaj >= 60) {
            result = getResources().getString(R.string.ocenkaa2);
        } else if (presmetaj >= 40) {
            result = getResources().getString(R.string.ocenkaa3);
        } else {
            result = getResources().getString(R.string.ocenkaa4);
        }

        String message = getResources().getString(R.string.rezultatt) + result + ". " + getResources().getString(R.string.tocenodgovorr) + " " + poenii;

        StringBuilder pogresniPrasanja = new StringBuilder();
        for (Integer pogresnoPrasanjeIndex : pogresniOdgovorii) {
            pogresniPrasanja.append(prasanjaa[pogresnoPrasanjeIndex]).append("\n");
            pogresniPrasanja.append(getResources().getString(R.string.tocenodgovorr)).append(odgovorr[pogresnoPrasanjeIndex][tocniodgovorii[pogresnoPrasanjeIndex]]).append("\n\n");
        }

        String porakaZaPogresni = getResources().getString(R.string.pogresenodgovorr) + "\n" + pogresniPrasanja.toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.rezz))
                .setMessage(message + "\n\n" + porakaZaPogresni)
                .setPositiveButton("OK", null)
                .show();
    }
}