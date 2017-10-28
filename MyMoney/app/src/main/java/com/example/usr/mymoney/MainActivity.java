package com.example.usr.mymoney;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;

import static android.R.drawable.ic_dialog_email;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton finance_button = new FloatingActionButton.Builder(this)
                .withDrawable(getResources().getDrawable(ic_dialog_email))
                .withButtonColor(Color.parseColor("#098f00"))
                .withGravity(Gravity.BOTTOM | Gravity.RIGHT)
                .withMargins(250, 380, 0, 0)
                .create();

        FloatingActionButton costs_button = new FloatingActionButton.Builder(this)
                .withDrawable(getResources().getDrawable(ic_dialog_email))
                .withButtonColor(Color.parseColor("#098f79"))
                .withGravity(Gravity.BOTTOM | Gravity.RIGHT)
                .withMargins(30, 80, 0, 0)
                .create();

        FloatingActionButton plan_button = new FloatingActionButton.Builder(this)
                .withDrawable(getResources().getDrawable(ic_dialog_email))
                .withButtonColor(Color.parseColor("#098f79"))
                .withGravity(Gravity.BOTTOM | Gravity.RIGHT)
                .withMargins(50, 60, 0, 0)
                .create();

        FloatingActionButton statistic_button = new FloatingActionButton.Builder(this)
                .withDrawable(getResources().getDrawable(ic_dialog_email))
                .withButtonColor(Color.parseColor("#098f79"))
                .withGravity(Gravity.BOTTOM | Gravity.RIGHT)
                .withMargins(30, 60, 0, 0)
                .create();

        FloatingActionButton exit_button = new FloatingActionButton.Builder(this)
                .withDrawable(getResources().getDrawable(ic_dialog_email))
                .withButtonColor(Color.parseColor("#098f79"))
                .withGravity(Gravity.BOTTOM | Gravity.RIGHT)
                .withMargins(0, 0, 16, 16)
                .create();

        FloatingActionButton info_button = new FloatingActionButton.Builder(this)
                .withDrawable(getResources().getDrawable(ic_dialog_email))
                .withButtonColor(Color.parseColor("#000000"))
                .withGravity(Gravity.BOTTOM | Gravity.RIGHT)
                .withMargins(0, 0, 200, 300)
                .create();
    }
}
