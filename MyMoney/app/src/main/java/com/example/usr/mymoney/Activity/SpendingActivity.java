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


public class SpendingActivity extends AppCompatActivity implements View.OnClickListener{

    private Menu menu;
    private List<Section> sections;
    private EditText editSpending;
    private DateFormat dateFormat;
    private DateFormat dayFormat;
    private ImageButton im_editing_spending;
    private RVAdapterSection adapter;
    private double currentCount;

    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spending);

        //region Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //endregion

        im_editing_spending = (ImageButton) findViewById(R.id.im_editing_spending);
        im_editing_spending.setOnClickListener(this);
        editSpending = (EditText) findViewById(R.id.edit_spending);

        dateFormat = new SimpleDateFormat("MM.yyyy");
        dayFormat = new SimpleDateFormat("dd.MM");
        dbHelper = new DbHelper(this);

        currentCount = MainActivity.getCurrentCount(dbHelper, currentCount);
        sections = getSectionsSpending();

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_spending);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RVAdapterSection(sections);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        String amountEditText = editSpending.getText().toString();
                        if (!amountEditText.equals("")) {

                            String nameObj = sections.get(position).nameSection;
                            double  amount = Double.parseDouble(editSpending.getText().toString());
                            String date = dateFormat.format(Calendar.getInstance().getTime());
                            String day = dayFormat.format(Calendar.getInstance().getTime());
                            int imgId = sections.get(position).getImageId();

                            FinanceObject financeObject = new FinanceObject(nameObj, amount, date, day, imgId);
                            dbHelper.addToSpending(financeObject);
                            dbHelper.minusDB(amount);

                            editSpending.setText("");
                            recyclerView.requestFocus();

                            Toast.makeText(getApplicationContext(), "Сумма добавлена!", Toast.LENGTH_LONG).show();

                            currentCount = Double.valueOf(dbHelper.getCurrentCount());
                            menu.getItem(0).setTitle(String.valueOf(currentCount));
                        }
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }

    /*@Override
    protected void onStart() {
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_spending);
        List<Section> listFromDB = dbHelper.getAllSectionSpending();
        sections = listFromDB;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RVAdapterSection(sections);
        recyclerView.setAdapter(adapter);
        super.onStart();

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
            case R.id.im_editing_spending:
                Intent intent1 = new Intent(this, EditingSpendingActivity.class);
                startActivity(intent1);
                break;
        }
    }

    public List<Section> getSectionsSpending(){
        List<Section> listFromDB = dbHelper.getAllSectionSpending();

        if (listFromDB.size() == 0) {

            sections = new ArrayList<>();
            sections.add(new Section(1, "Питание", R.drawable.food, "",""));
            sections.add(new Section(2, "Одежда", R.drawable.trousers, "",""));
            sections.add(new Section(3, "Подарки", R.drawable.gift, "",""));
            sections.add(new Section(4, "Быт", R.drawable.home, "",""));
            sections.add(new Section(7, "Транспорт", R.drawable.car, "",""));
            sections.add(new Section(5, "Медицина", R.drawable.medical, "",""));
            sections.add(new Section(6, "Спорт", R.drawable.sport, "",""));
            sections.add(new Section(7, "Развлечения", R.drawable.bowling, "",""));

            dbHelper.addSpendingSection(new Section(1, "Питание", R.drawable.food, "",""));
            dbHelper.addSpendingSection(new Section(2, "Одежда", R.drawable.trousers, "",""));
            dbHelper.addSpendingSection(new Section(3, "Подарки", R.drawable.gift, "",""));
            dbHelper.addSpendingSection(new Section(4, "Быт", R.drawable.home, "",""));
            dbHelper.addSpendingSection(new Section(7, "Транспорт", R.drawable.car, "",""));
            dbHelper.addSpendingSection(new Section(5, "Медицина", R.drawable.medical, "",""));
            dbHelper.addSpendingSection(new Section(5, "Спорт", R.drawable.sport, "",""));
            dbHelper.addSpendingSection(new Section(5, "Развлечения", R.drawable.bowling, "",""));
        } else {
            sections = listFromDB;
        }
        return sections;
    }
}
