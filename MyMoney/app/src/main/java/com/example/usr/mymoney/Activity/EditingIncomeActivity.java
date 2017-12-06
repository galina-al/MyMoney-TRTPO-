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
import com.example.usr.mymoney.R;
import com.example.usr.mymoney.RVAdapter;
import com.example.usr.mymoney.RecyclerItemClickListener;
import com.example.usr.mymoney.Section;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class EditingIncomeActivity extends AppCompatActivity implements View.OnClickListener{

    protected List<Section> sections;
    ImageButton btn_add_inc_section;
    DateFormat df;

    RVAdapter adapter;

    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing_income);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");

        btn_add_inc_section = (ImageButton) findViewById(R.id.btn_add_inc_section);

        dbHelper = new DbHelper(this);
        btn_add_inc_section.setOnClickListener(this);

        sections = dbHelper.getAllSectionIncome();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_editing_income);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RVAdapter(sections);

        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, final int position) {
                        LayoutInflater li = LayoutInflater.from(getApplicationContext());
                        View promptsView = li.inflate(R.layout.name_section, null);

                        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(EditingIncomeActivity.this);

                        mDialogBuilder.setView(promptsView);

                        final EditText edit_name_section = (EditText) promptsView.findViewById(R.id.edit_new_name);
                        final String oldName = sections.get(position).nameSection.toString();
                        edit_name_section.setText(sections.get(position).getNameSection());
                        edit_name_section.setTextColor(Integer.valueOf(R.color.colorPrimaryDark));

                        //Настраиваем сообщение в диалоговом окне:
                        mDialogBuilder
                                .setCancelable(true)
                                .setPositiveButton("Ок",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                String newName = edit_name_section.getText().toString();
                                                if (!newName.equals("") && newName != oldName) {
                                                    Section updateSection = sections.get(position);
                                                    updateSection.setNameSection(newName);
                                                    adapter.updateItem(updateSection, position);
                                                    dbHelper.updateIncomeSection(oldName, newName);
                                                }
                                            }
                                        })
                                .setNegativeButton("Удалить",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                adapter.removeItem(position);
                                                dbHelper.deleteIncomeSection(oldName);
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

        btn_add_inc_section = (ImageButton) findViewById(R.id.btn_add_inc_section);
        btn_add_inc_section.setOnClickListener(this);
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
            case R.id.btn_add_inc_section:

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
                                            Section newSection = new Section(newPosition, nameSection, R.drawable.star, "");
                                            adapter.addItem(newSection, newPosition);
                                            dbHelper.addIncomeSection(newSection);
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


}
