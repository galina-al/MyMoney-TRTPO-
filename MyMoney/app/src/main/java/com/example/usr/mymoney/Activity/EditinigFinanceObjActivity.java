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
import android.widget.Spinner;

import com.example.usr.mymoney.DataBase.DbHelper;
import com.example.usr.mymoney.FinanceObject;
import com.example.usr.mymoney.R;
import com.example.usr.mymoney.RVAdapter;
import com.example.usr.mymoney.RecyclerItemClickListener;
import com.example.usr.mymoney.Section;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class EditinigFinanceObjActivity extends AppCompatActivity {

    public RVAdapter adapter;
    public List<Section> sections;
    public EditText editIncome;
    ImageButton im_editing_income;
    DateFormat dateFormat;
    DateFormat dayFormat;

    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing_finance_obj);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        dbHelper = new DbHelper(this);
        sections = new ArrayList<>();

        Bundle extras = getIntent().getExtras();

        String name = extras.getString("name");
        String date = extras.getString("date");
        int imgId = extras.getInt("imgId");
        int number = extras.getInt("number");

        List<FinanceObject> listFromDB = dbHelper.getOneSection(name, date, number);
        for (int i = 0; i < listFromDB.size(); i++) {
            Section section = new Section();
            section.setNameSection(listFromDB.get(i).getNameObj());
            section.setImageId(imgId);
            section.setAmount(listFromDB.get(i).getAmount().toString());
            sections.add(section);
        }

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_editing_obj);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RVAdapter(sections);

        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, final int position) {
                        LayoutInflater li = LayoutInflater.from(EditinigFinanceObjActivity.this);
                        View promptsView = li.inflate(R.layout.editing_obj, null);

                        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(EditinigFinanceObjActivity.this);
                        mDialogBuilder.setView(promptsView);

                        final EditText edit_name_obj = (EditText) promptsView.findViewById(R.id.edit_name_obj);
                        final String oldName = sections.get(position).nameSection.toString();
                        edit_name_obj.setText(oldName);

                        final EditText edit_amount_obj = (EditText) promptsView.findViewById(R.id.edit_amount_obj);
                        String oldAmount = sections.get(position).getAmount();
                        edit_amount_obj.setText(oldAmount);

                        final Spinner spinner = (Spinner) view.findViewById(R.id.spinner_day);


                        mDialogBuilder
                                .setCancelable(true)
                                .setPositiveButton("Ок",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                final String newName = edit_name_obj.getText().toString();
                                                final String amountObj = edit_amount_obj.getText().toString();
                                                final String day = spinner.getSelectedItem().toString();

                                                /*if (!amountObj.equals("") && newName != "" && day != "") {

                                                    FinanceObject
                                                    Section updateSection = sections.get(position);
                                                    updateSection.setNameSection(newName);
                                                    updateSection.setAmount(amountObj);
                                                    dbHelper.updatePlaningSection(oldName, updateSection);
                                                    adapter.updateItem(updateSection, position);

                                                }*/
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

}
