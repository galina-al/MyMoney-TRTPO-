package com.example.usr.mymoney.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

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

public class PlaningActivity extends AppCompatActivity implements View.OnClickListener {

    protected List<Section> sections;
    ImageButton btn_add;
    DateFormat df;

    RVAdapter adapter;

    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");

        btn_add = (ImageButton) findViewById(R.id.btn_add);

        dbHelper = new DbHelper(this);
        btn_add.setOnClickListener(this);

        List<Section> listFromDB = dbHelper.getAllSectionPlaning();

        if (listFromDB.size() == 0) {

            sections = new ArrayList<>();
            sections.add(new Section(1, "Питание", R.drawable.food, ""));
            sections.add(new Section(2, "Одежда", R.drawable.trousers, ""));
            sections.add(new Section(3, "Подарки", R.drawable.gift, ""));
            sections.add(new Section(4, "Гигиена", R.drawable.shower, ""));
            sections.add(new Section(5, "Медицина", R.drawable.medical, ""));
            sections.add(new Section(6, "Спорт", R.drawable.sport, ""));
            sections.add(new Section(7, "Бары и Кафе", R.drawable.bar, ""));

            dbHelper.addPlaningSection(new Section(1, "Питание", R.drawable.food, ""));
            dbHelper.addPlaningSection(new Section(2, "Одежда", R.drawable.trousers, ""));
            dbHelper.addPlaningSection(new Section(3, "Подарки", R.drawable.gift, ""));
            dbHelper.addPlaningSection(new Section(4, "Гигиена", R.drawable.shower, ""));
            dbHelper.addPlaningSection(new Section(5, "Медицина", R.drawable.medical, ""));
            dbHelper.addPlaningSection(new Section(5, "Спорт", R.drawable.sport, ""));
            dbHelper.addPlaningSection(new Section(5, "Бары и Кафе", R.drawable.bar, ""));
        } else {
            sections = listFromDB;
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_planing);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RVAdapter(sections);

        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //Получаем вид с файла prompt.xml, который применим для диалогового окна:
                        LayoutInflater li = LayoutInflater.from(getApplicationContext());
                        View promptsView = li.inflate(R.layout.alert_dilalog, null);

                        //Создаем AlertDialog
                        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(getApplicationContext());

                        //Настраиваем prompt.xml для нашего AlertDialog:
                        mDialogBuilder.setView(promptsView);

                        //Настраиваем отображение поля для ввода текста в открытом диалоге:
                        final EditText edit_name_section = (EditText) promptsView.findViewById(R.id.edit_name_section);
                        edit_name_section.setText(sections.get(position).toString());
                        final String nameSection = edit_name_section.getText().toString();

                        final EditText edit_amount_section = (EditText) promptsView.findViewById(R.id.edit_amount);
                        final String amountSection = edit_amount_section.getText().toString();

                        //Настраиваем сообщение в диалоговом окне:
                        mDialogBuilder
                                .setCancelable(false)
                                .setPositiveButton("Добавить",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                if (!amountSection.equals("")) {

                                                    double amount = Double.parseDouble(amountSection);
                                                    String date = df.format(Calendar.getInstance().getTime());
                                                    FinanceObject financeObject = new FinanceObject(nameSection, amount, date);

                                                    dbHelper.addToPlaning(financeObject);

                                                }
                                            }
                                        })
                                .setNegativeButton("Отмена",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });

                        //Создаем AlertDialog:
                        AlertDialog alertDialog = mDialogBuilder.create();

                        //и отображаем его:
                        alertDialog.show();

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );


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
            case R.id.btn_add:

                //Получаем вид с файла prompt.xml, который применим для диалогового окна:
                LayoutInflater li = LayoutInflater.from(this);
                View promptsView = li.inflate(R.layout.name_section, null);

                //Создаем AlertDialog
                AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(this);

                //Настраиваем prompt.xml для нашего AlertDialog:
                mDialogBuilder.setView(promptsView);

                //Настраиваем отображение поля для ввода текста в открытом диалоге:
                final EditText edit_name_section = (EditText) promptsView.findViewById(R.id.edit_new_section);

                //Настраиваем сообщение в диалоговом окне:
                mDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Добавить",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        final String nameSection = edit_name_section.getText().toString();
                                        if (!nameSection.equals("")) {
                                            int newPosition = sections.size();
                                            Section newSection = new Section(newPosition, nameSection, R.drawable.star, "");
                                            adapter.add(newSection, newPosition);
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

                //Создаем AlertDialog:
                AlertDialog alertDialog = mDialogBuilder.create();

                //и отображаем его:
                alertDialog.show();
                break;
        }
    }
}
