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

public class SpendingActivity extends AppCompatActivity {

    protected List<Section> sections;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spending);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_spending);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        sections = new ArrayList<>();
        sections.add(new Section("Питание", R.drawable.food, ""));
        sections.add(new Section("Одежда", R.drawable.trousers, ""));
        sections.add(new Section("Подарки", R.drawable.gift, ""));
        sections.add(new Section("Гигиена", R.drawable.shower, ""));
        sections.add(new Section("Медицина", R.drawable.medical, ""));
        sections.add(new Section("Спорт", R.drawable.sport,""));
        sections.add(new Section("Бары и Кафе", R.drawable.bar, ""));

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
