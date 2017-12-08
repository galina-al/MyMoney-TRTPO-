package com.example.usr.mymoney.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.usr.mymoney.DataBase.DbHelper;
import com.example.usr.mymoney.R;
import com.example.usr.mymoney.Adapter.RVAdapterSection;
import com.example.usr.mymoney.Adapter.RecyclerItemClickListener;
import com.example.usr.mymoney.Entity.Section;

import java.util.ArrayList;
import java.util.List;

public class PlaningActivity extends AppCompatActivity implements View.OnClickListener {

    private Menu menu;
    private RVAdapterSection adapter;
    private DbHelper dbHelper;
    private List<Section> sections;
    private double currentCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planing);

        //region Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //endregion

        ImageButton btn_add_plan_section = (ImageButton) findViewById(R.id.btn_add_plan_section);
        btn_add_plan_section.setOnClickListener(this);

        dbHelper = new DbHelper(this);
        sections = getSectionsPlaning();
        currentCount = MainActivity.getCurrentCount(dbHelper, currentCount);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_planing);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RVAdapterSection(sections);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, final int position) {

                        LayoutInflater li = LayoutInflater.from(getApplicationContext());
                        View promptsView = li.inflate(R.layout.alert_dilalog, null);

                        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(PlaningActivity.this);
                        mDialogBuilder.setView(promptsView);

                        final EditText edit_name_section = (EditText) promptsView.findViewById(R.id.edit_name_section);
                        final String oldName = sections.get(position).nameSection.toString();
                        edit_name_section.setText(oldName);

                        final EditText edit_amount_section = (EditText) promptsView.findViewById(R.id.edit_amount);
                        String oldAmount = sections.get(position).getAmount();
                        edit_amount_section.setText(oldAmount);

                        mDialogBuilder
                                .setCancelable(true)
                                .setPositiveButton("Ок",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                final String newName = edit_name_section.getText().toString();
                                                final String amountSection = edit_amount_section.getText().toString();
                                                if (!amountSection.equals("") && newName != "") {

                                                    Section updateSection = sections.get(position);
                                                    updateSection.setNameSection(newName);
                                                    updateSection.setAmount(amountSection);
                                                    dbHelper.updatePlaningSection(oldName, updateSection);
                                                    adapter.updateItem(updateSection, position);
                                                }
                                            }
                                        })
                                .setNegativeButton("Удалить",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                adapter.removeItem(position);
                                                dbHelper.deletePlaningSection(oldName);
                                                dialog.cancel();
                                            }
                                        });
                        AlertDialog alertDialog = mDialogBuilder.create();
                        alertDialog.show();
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                    }
                })
        );

    }

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
            case R.id.btn_add_plan_section:

                LayoutInflater li = LayoutInflater.from(this);
                View promptsView = li.inflate(R.layout.name_section, null);

                AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(this);

                mDialogBuilder.setView(promptsView);

                final EditText edit_name_section = (EditText) promptsView.findViewById(R.id.edit_new_name);

                mDialogBuilder
                        .setCancelable(true)
                        .setPositiveButton("Добавить",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        final String nameSection = edit_name_section.getText().toString();
                                        if (!nameSection.equals("")) {

                                            int newPosition = sections.size();
                                            Section newSection = new Section(newPosition, nameSection, R.drawable.star, "","");
                                            adapter.addItem(newSection, newPosition);
                                            dbHelper.addPlaningSection(newSection);
                                        }
                                    }
                                })
                        .setNegativeButton("Отмена",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });
                AlertDialog alertDialog = mDialogBuilder.create();
                alertDialog.show();
                break;
        }
    }

    public List<Section> getSectionsPlaning(){
        List<Section> listFromDB = dbHelper.getAllSectionPlaning();

        if (listFromDB.size() == 0) {

            sections = new ArrayList<>();
            sections.add(new Section(1, "Питание", R.drawable.food, "", ""));
            sections.add(new Section(2, "Одежда", R.drawable.trousers, "", ""));
            sections.add(new Section(3, "Подарки", R.drawable.gift, "", ""));
            sections.add(new Section(4, "Быт", R.drawable.home, "", ""));
            sections.add(new Section(7, "Транспорт", R.drawable.car, "", ""));
            sections.add(new Section(5, "Медицина", R.drawable.medical, "", ""));
            sections.add(new Section(6, "Спорт", R.drawable.sport, "", ""));
            sections.add(new Section(7, "Развлечения", R.drawable.bowling, "", ""));

            dbHelper.addPlaningSection(new Section(1, "Питание", R.drawable.food, "", ""));
            dbHelper.addPlaningSection(new Section(2, "Одежда", R.drawable.trousers, "", ""));
            dbHelper.addPlaningSection(new Section(3, "Подарки", R.drawable.gift, "", ""));
            dbHelper.addPlaningSection(new Section(4, "Быт", R.drawable.home, "", ""));
            dbHelper.addPlaningSection(new Section(7, "Транспорт", R.drawable.car, "", ""));
            dbHelper.addPlaningSection(new Section(5, "Медицина", R.drawable.medical, "", ""));
            dbHelper.addPlaningSection(new Section(5, "Спорт", R.drawable.sport, "", ""));
            dbHelper.addPlaningSection(new Section(5, "Развлечения", R.drawable.bowling, "", ""));
        } else {
            sections = listFromDB;
        }
        return sections;
    }
}
