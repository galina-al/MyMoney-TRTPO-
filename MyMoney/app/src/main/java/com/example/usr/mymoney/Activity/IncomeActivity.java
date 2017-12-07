package com.example.usr.mymoney.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.usr.mymoney.DataBase.DbHelper;
import com.example.usr.mymoney.FinanceObject;
import com.example.usr.mymoney.R;
import com.example.usr.mymoney.RVAdapter;
import com.example.usr.mymoney.RecyclerItemClickListener;
import com.example.usr.mymoney.Section;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class IncomeActivity extends AppCompatActivity implements View.OnClickListener {

    public RVAdapter adapter;
    public List<Section> sections;
    public EditText editIncome;
    ImageButton im_editing_income;
    DateFormat df;

    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        df = new SimpleDateFormat("MM.yyyy");

        editIncome = (EditText) findViewById(R.id.edit_income);
        dbHelper = new DbHelper(this);

        List<Section> listFromDB = dbHelper.getAllSectionIncome();

        if (listFromDB.size() == 0) {

            sections = new ArrayList<>();
            sections.add(new Section(1, "Зарплата", R.drawable.money_bag, ""));
            sections.add(new Section(2, "Бизнес", R.drawable.business, ""));
            sections.add(new Section(3, "Подарки", R.drawable.gift, ""));
            sections.add(new Section(4, "Подработка", R.drawable.work, ""));
            sections.add(new Section(5, "Продажа личных вещей", R.drawable.selling, ""));

            dbHelper.addIncomeSection(new Section(1, "Зарплата", R.drawable.money_bag, ""));
            dbHelper.addIncomeSection(new Section(2, "Бизнес", R.drawable.business, ""));
            dbHelper.addIncomeSection(new Section(3, "Подарки", R.drawable.gift, ""));
            dbHelper.addIncomeSection(new Section(4, "Подработка", R.drawable.work, ""));
            dbHelper.addIncomeSection(new Section(5, "Продажа личных вещей", R.drawable.selling, ""));
        } else {
            sections = listFromDB;
        }

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_income);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RVAdapter adapter = new RVAdapter(sections);

        recyclerView.setAdapter(adapter);


        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        String amountEditText = editIncome.getText().toString();

                        if (!amountEditText.equals("")) {

                            String nameObj = sections.get(position).nameSection;
                            Double  amount = Double.parseDouble(editIncome.getText().toString());
                            String date = df.format(Calendar.getInstance().getTime());

                            FinanceObject financeObject = new FinanceObject(nameObj, amount, date);
                            dbHelper.addToIncome(financeObject);

                            editIncome.setText("");
                            //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                            recyclerView.requestFocus();
                            Toast.makeText(getApplicationContext(), "Сумма добавлена!", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        im_editing_income = (ImageButton) findViewById(R.id.im_editing_income);
        im_editing_income.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_income);
        List<Section> listFromDB = dbHelper.getAllSectionIncome();
        sections = listFromDB;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RVAdapter(sections);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.im_editing_income:
                Intent intent1 = new Intent(this, EditingIncomeActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
