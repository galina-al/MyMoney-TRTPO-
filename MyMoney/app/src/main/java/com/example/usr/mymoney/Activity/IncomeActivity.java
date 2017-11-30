package com.example.usr.mymoney.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.usr.mymoney.R;
import com.example.usr.mymoney.RVAdapter;
import com.example.usr.mymoney.Section;

import java.util.ArrayList;
import java.util.List;

public class IncomeActivity extends AppCompatActivity {

    protected List<Section> sections;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_income);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        sections = new ArrayList<>();
        sections.add(new Section("Зарплата", R.drawable.money_bag, ""));
        sections.add(new Section("Бизнес", R.drawable.business, ""));
        sections.add(new Section("Подарки", R.drawable.gift, ""));
        sections.add(new Section("Подработка", R.drawable.work, ""));
        sections.add(new Section("Продажа личных вещей", R.drawable.selling, ""));

        RVAdapter adapter = new RVAdapter(sections);
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
}
