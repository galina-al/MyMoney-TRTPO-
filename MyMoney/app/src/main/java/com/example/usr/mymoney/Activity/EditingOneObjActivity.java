package com.example.usr.mymoney.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.usr.mymoney.Adapter.RVAdapterObject;
import com.example.usr.mymoney.DataBase.DbHelper;
import com.example.usr.mymoney.Entity.FinanceObject;
import com.example.usr.mymoney.R;

import java.util.List;

public class EditingOneObjActivity extends AppCompatActivity implements View.OnClickListener {

    private Menu menu;
    private DbHelper dbHelper;
    private RVAdapterObject adapter;
    private Spinner spinner_section;
    private Spinner spinner_day;
    private TextView selectedDate;
    private EditText edit_amount_obj;
    private int number;
    private int objId;
    private int imgId;
    private String date;
    private String day;
    private String oldName;
    private String oldAmount;
    private double currentCount;
    private FinanceObject oldObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editing_one_obj);

        //region Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //endregion

        Bundle extras = getIntent().getExtras();

        number = extras.getInt("number");
        objId = extras.getInt("objId");
        imgId = extras.getInt("imgId");
        oldName = extras.getString("name");
        oldAmount = extras.getString("oldAmount");
        date = extras.getString("date");
        day = extras.getString("day").substring(0, 2);

        oldObject = new FinanceObject(oldName, Double.parseDouble(oldAmount), date, extras.getString("day"), imgId);
        oldObject.setObjId(objId);
        dbHelper = new DbHelper(this);
        currentCount = MainActivity.getCurrentCount(dbHelper, currentCount);

        spinner_section = (Spinner) findViewById(R.id.spinner_name_section);
        edit_amount_obj = (EditText) findViewById(R.id.edit_amount_obj);
        edit_amount_obj.setText(oldAmount);
        edit_amount_obj.requestFocus();
        Button btn_update = (Button) findViewById(R.id.btn_update_obj);
        btn_update.setOnClickListener(this);

        List<String> allNameSection = dbHelper.getAllNameSection(number);
        int idName = allNameSection.indexOf(oldName);

        ArrayAdapter<String> arrayAdapterName = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, allNameSection);
        arrayAdapterName.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_section.setAdapter(arrayAdapterName);
        spinner_section.setSelection(idName);

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendar);
        selectedDate = (TextView) findViewById(R.id.selected_date);
        selectedDate.setText(day + "." + date);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {

                /*if (i1 < 9) {
                    date = 0 + String.valueOf(i1 + 1) + "." + String.valueOf(i);
                } else {
                    date = String.valueOf(i1 + 1) + "." + String.valueOf(i);
                }*/
                if (i2 < 10) {
                    day = 0 + String.valueOf(i2) + "." + (i1 + 1);
                } else {
                    day = String.valueOf(i2) + "." + (i1 + 1);
                }
                date = (i1 + 1) + "." + i;
                String select = day + "." + i;
                selectedDate.setText(select);
            }
        });
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

    public void onClick(View view) {
        final String newName = spinner_section.getSelectedItem().toString();
        final String amountObj = edit_amount_obj.getText().toString();
        final String newDay = day;

        switch (view.getId()) {
            case R.id.btn_update_obj:
                if (!amountObj.equals("") && newName != "") {
                    if (newName != oldName) {
                        imgId = dbHelper.getImgSectionByName(newName, number);
                    }
                    FinanceObject financeObject = new FinanceObject(newName, Double.parseDouble(amountObj), date, newDay, imgId);
                    financeObject.setObjId(objId);
                    if (number == 2) {
                        dbHelper.updateIncomeObject(oldObject, financeObject);
                        dbHelper.minusDB(Double.valueOf(oldAmount));
                        dbHelper.plusDB(Double.valueOf(amountObj));
                    } else {
                        dbHelper.updateSpendingObject(oldObject, financeObject);
                        dbHelper.plusDB(Double.valueOf(oldAmount));
                        dbHelper.minusDB(Double.valueOf(amountObj));
                    }

                    currentCount = Double.valueOf(dbHelper.getCurrentCount());
                    menu.getItem(0).setTitle(String.valueOf(currentCount));
                    this.finish();
                }
                break;
        }
    }

}
