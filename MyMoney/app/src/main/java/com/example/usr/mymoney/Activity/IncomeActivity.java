package com.example.usr.mymoney.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.usr.mymoney.DataBase.DbHelper;
import com.example.usr.mymoney.FinanceObject;
import com.example.usr.mymoney.R;
import com.example.usr.mymoney.RVAdapterSection;
import com.example.usr.mymoney.RecyclerItemClickListener;
import com.example.usr.mymoney.Section;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class IncomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Menu menu;

    private RVAdapterSection adapter;
    private List<Section> sections;
    private EditText editIncome;
    private ImageButton im_editing_income;
    private DateFormat dateFormat;
    private DateFormat dayFormat;
    private double currentCount;

    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        //region Toolbar initialize
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //endregion

        dateFormat = new SimpleDateFormat("MM.yyyy");
        dayFormat = new SimpleDateFormat("dd.MM");

        editIncome = (EditText) findViewById(R.id.edit_income);
        im_editing_income = (ImageButton) findViewById(R.id.im_editing_income);
        im_editing_income.setOnClickListener(this);

        dbHelper = new DbHelper(this);
        currentCount = MainActivity.getCurrentCount(dbHelper, currentCount);
        sections = getSectionsIncome();

        //region recyclerView (initialize and listener)
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_income);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RVAdapterSection adapter = new RVAdapterSection(sections);

        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        String amountEditText = editIncome.getText().toString();
                        if (!amountEditText.equals("")) {

                            String nameObj = sections.get(position).nameSection;
                            double  amount = Double.parseDouble(editIncome.getText().toString());
                            String date = dateFormat.format(Calendar.getInstance().getTime());
                            String day = dayFormat.format(Calendar.getInstance().getTime());
                            int imgId = sections.get(position).getImageId();

                            FinanceObject financeObject = new FinanceObject(nameObj, amount, date, day, imgId);
                            dbHelper.addToIncome(financeObject);
                            dbHelper.plusDB(amount);

                            currentCount = Double.valueOf(dbHelper.getCurrentCount());
                            menu.getItem(0).setTitle(String.valueOf(currentCount));

                            editIncome.setText("");
                            recyclerView.requestFocus();

                            Toast.makeText(getApplicationContext(), "Сумма добавлена!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onLongItemClick(View view, int position) {
                    }
                })
        );
        //endregion

    }

    /*@Override
    protected void onStart() {
        super.onStart();
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_income);
        List<Section> listFromDB = dbHelper.getAllSectionIncome();
        sections = listFromDB;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RVAdapterSection(sections);
        recyclerView.setAdapter(adapter);
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_statistic, menu);
        menu.getItem(0).setTitle(String.valueOf(currentCount));
        return true;
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

    public List<Section> getSectionsIncome(){

        List<Section> listFromDB = dbHelper.getAllSectionIncome();
        if (listFromDB.size() == 0) {

            sections = new ArrayList<>();
            sections.add(new Section(1, "Зарплата", R.drawable.money_bag, "", ""));
            sections.add(new Section(2, "Бизнес", R.drawable.business, "",""));
            sections.add(new Section(3, "Подарки", R.drawable.gift, "",""));
            sections.add(new Section(4, "Подработка", R.drawable.work, "",""));

            dbHelper.addIncomeSection(new Section(1, "Зарплата", R.drawable.money_bag, "",""));
            dbHelper.addIncomeSection(new Section(2, "Бизнес", R.drawable.business, "",""));
            dbHelper.addIncomeSection(new Section(3, "Подарки", R.drawable.gift, "",""));
            dbHelper.addIncomeSection(new Section(4, "Подработка", R.drawable.work, "",""));
        } else {
            sections = listFromDB;
        }
        return sections;
    }

}
