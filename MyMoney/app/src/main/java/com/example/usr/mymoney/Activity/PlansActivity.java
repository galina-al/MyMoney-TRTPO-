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

public class PlansActivity extends AppCompatActivity {

    protected List<Section> sections;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plans);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_spending);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        sections = new ArrayList<>();
        sections.add(new Section("Питание", R.drawable.food, "150"));
        sections.add(new Section("Одежда", R.drawable.trousers, "200"));
        sections.add(new Section("Подарки", R.drawable.gift, "40"));
        sections.add(new Section("Гигиена", R.drawable.shower, "70"));
        sections.add(new Section("Медицина", R.drawable.medical, "200"));
        sections.add(new Section("Спорт", R.drawable.sport,"100"));
        sections.add(new Section("Бары и Кафе", R.drawable.bar, "150"));


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
