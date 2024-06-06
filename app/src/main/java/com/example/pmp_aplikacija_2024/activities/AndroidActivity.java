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

public class AndroidActivity extends AppCompatActivity {

    private TextView prasanjaaaa;
    private RadioGroup izborrr;
    private Button kopceee;
    private String[] prasanjaaa;
    private String[][] odgovorrr;
    private int[] tocniodgovoriii = {1, 2, 1, 1, 2, 3};
    private int indexxx = 0;
    private int poeniii = 0;
    private List<Integer> pogresniOdgovoriii = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android);

        Resources res = getResources();

        prasanjaaa = new String[]{
                res.getString(R.string.prasanjeee1),
                res.getString(R.string.prasanjeee2),
                res.getString(R.string.prasanjeee3),
                res.getString(R.string.prasanjeee4),
                res.getString(R.string.prasanjeee5),
                res.getString(R.string.prasanjeee6)
        };

        odgovorrr = new String[][]{
                res.getStringArray(R.array.odgovorrr1),
                res.getStringArray(R.array.odgovorrr2),
                res.getStringArray(R.array.odgovorrr3),
                res.getStringArray(R.array.odgovorrr4),
                res.getStringArray(R.array.odgovorrr5),
                res.getStringArray(R.array.odgovorrr6)
        };

        prasanjaaaa = findViewById(R.id.prasanjaa);
        izborrr = findViewById(R.id.izbor);
        kopceee = findViewById(R.id.kopce);

        showQuestion();

        kopceee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer();
            }
        });
    }

    private void showQuestion() {
        prasanjaaaa.setText(prasanjaaa[indexxx]);

        for (int i = 0; i < izborrr.getChildCount(); i++) {
            RadioButton radioButton = (RadioButton) izborrr.getChildAt(i);
            radioButton.setText(odgovorrr[indexxx][i]);

            radioButton.setEnabled(true);
        }
    }

    private void checkAnswer() {
        int selectedRadioButtonId = izborrr.getCheckedRadioButtonId();
        if (selectedRadioButtonId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
            int selectedAnswerIndex = izborrr.indexOfChild(selectedRadioButton);
            int correctAnswerIndex = tocniodgovoriii[indexxx];
            if (selectedAnswerIndex == correctAnswerIndex) {
                poeniii++;
            } else {
                pogresniOdgovoriii.add(indexxx);
            }

            for (int i = 0; i < izborrr.getChildCount(); i++) {
                izborrr.getChildAt(i).setEnabled(false);
            }
            indexxx++;
            if (indexxx < prasanjaaa.length) {
                showQuestion();
            } else {
                showResult();
            }
        } else {
            Toast.makeText(AndroidActivity.this, getResources().getString(R.string.izberiodg), Toast.LENGTH_SHORT).show();
        }
    }

    private void showResult() {
        double presmetaj = ((double)poeniii / prasanjaaa.length) * 100;
        String result;
        if (presmetaj >= 80) {
            result = getResources().getString(R.string.ocenkaaaa1);
        } else if (presmetaj >= 60) {
            result = getResources().getString(R.string.ocenkaaaa2);
        } else if (presmetaj >= 40) {
            result = getResources().getString(R.string.ocenkaaaa3);
        } else {
            result = getResources().getString(R.string.ocenkaaaa4);
        }

        String message = getResources().getString(R.string.rezultatt) + result + ". " + getResources().getString(R.string.tocenodgovorr) + " " + poeniii;

        StringBuilder pogresniPrasanja = new StringBuilder();
        for (Integer pogresnoPrasanjeIndex : pogresniOdgovoriii) {
            pogresniPrasanja.append(prasanjaaa[pogresnoPrasanjeIndex]).append("\n");
            pogresniPrasanja.append(getResources().getString(R.string.tocenodgovorr)).append(odgovorrr[pogresnoPrasanjeIndex][tocniodgovoriii[pogresnoPrasanjeIndex]]).append("\n\n");
        }

        String porakaZaPogresni = getResources().getString(R.string.pogresenodgovorr) + "\n" + pogresniPrasanja.toString();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.rezzzz))
                .setMessage(message + "\n\n" + porakaZaPogresni)
                .setPositiveButton("OK", null)
                .show();
    }
}