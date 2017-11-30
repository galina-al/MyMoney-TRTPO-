package com.example.usr.mymoney.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.usr.mymoney.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    at.markushi.ui.CircleButton btn_income;
    at.markushi.ui.CircleButton btn_spending;
    at.markushi.ui.CircleButton btn_plans;
    at.markushi.ui.CircleButton btn_statistic;
    at.markushi.ui.CircleButton btn_exit;
    at.markushi.ui.CircleButton btn_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_income = (at.markushi.ui.CircleButton) findViewById(R.id.btn_income);
        btn_spending = (at.markushi.ui.CircleButton) findViewById(R.id.btn_spending);
        btn_plans = (at.markushi.ui.CircleButton) findViewById(R.id.btn_plans);
        btn_statistic = (at.markushi.ui.CircleButton) findViewById(R.id.btn_statistic);
        btn_exit = (at.markushi.ui.CircleButton) findViewById(R.id.btn_exit);
        btn_info = (at.markushi.ui.CircleButton) findViewById(R.id.btn_info);

        btn_income.setOnClickListener(this);
        btn_spending.setOnClickListener(this);
        btn_plans.setOnClickListener(this);
        btn_statistic.setOnClickListener(this);
        btn_exit.setOnClickListener(this);
        btn_info.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_income:
                Intent intent1 = new Intent(this, IncomeActivity.class);
                startActivity(intent1);
                break;

            case R.id.btn_spending:
                Intent intent2 = new Intent(this, SpendingActivity.class);
                startActivity(intent2);
                break;
            case R.id.btn_plans:
                Intent intent3 = new Intent(this, PlansActivity.class);
                startActivity(intent3);
                break;
            case R.id.btn_statistic:
                Intent intent4 = new Intent(this, StatisticActivity.class);
                startActivity(intent4);
                break;
            case R.id.btn_exit:
                this.finish();
                break;
            case R.id.btn_info:
                Intent intent6 = new Intent(this, InfoActivity.class);
                startActivity(intent6);
                break;
            default:
                break;
        }
    }
}
