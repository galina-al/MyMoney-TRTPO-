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


public class SpendingActivity extends AppCompatActivity implements View.OnClickListener{

    protected List<Section> sections;
    public EditText editSpending;
    public DateFormat df;
    public ImageButton im_editing_spending;
    public RVAdapter adapter;

    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spending);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");

        editSpending = (EditText) findViewById(R.id.edit_spending);
        dbHelper = new DbHelper(this);

        List<Section> listFromDB = dbHelper.getAllSectionSpending();

        if (listFromDB.size() == 0) {

            sections = new ArrayList<>();
            sections.add(new Section(1, "Питание", R.drawable.food, ""));
            sections.add(new Section(2, "Одежда", R.drawable.trousers, ""));
            sections.add(new Section(3, "Подарки", R.drawable.gift, ""));
            sections.add(new Section(4, "Гигиена", R.drawable.shower, ""));
            sections.add(new Section(5, "Медицина", R.drawable.medical, ""));
            sections.add(new Section(6, "Спорт", R.drawable.sport, ""));
            sections.add(new Section(7, "Бары и Кафе", R.drawable.bar, ""));

            dbHelper.addSpendingSection(new Section(1, "Питание", R.drawable.food, ""));
            dbHelper.addSpendingSection(new Section(2, "Одежда", R.drawable.trousers, ""));
            dbHelper.addSpendingSection(new Section(3, "Подарки", R.drawable.gift, ""));
            dbHelper.addSpendingSection(new Section(4, "Гигиена", R.drawable.shower, ""));
            dbHelper.addSpendingSection(new Section(5, "Медицина", R.drawable.medical, ""));
            dbHelper.addSpendingSection(new Section(5, "Спорт", R.drawable.sport, ""));
            dbHelper.addSpendingSection(new Section(5, "Бары и Кафе", R.drawable.bar, ""));
        } else {
            sections = listFromDB;
        }

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_spending);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RVAdapter(sections);

        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        String amountEditText = editSpending.getText().toString();

                        if (!amountEditText.equals("")) {

                            String nameObj = sections.get(position).nameSection;
                            double amount = Double.parseDouble(editSpending.getText().toString());
                            String date = df.format(Calendar.getInstance().getTime());

                            FinanceObject financeObject = new FinanceObject(nameObj, amount, date);
                            dbHelper.addToSpending(financeObject);

                            editSpending.setText("");
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


        im_editing_spending = (ImageButton) findViewById(R.id.im_editing_spending);
        im_editing_spending.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_spending);
        List<Section> listFromDB = dbHelper.getAllSectionSpending();
        sections = listFromDB;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RVAdapter(sections);
        recyclerView.setAdapter(adapter);
        super.onStart();

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
}
