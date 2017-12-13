package com.example.usr.mymoney.Activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.usr.mymoney.Adapter.RVAdapterObject;
import com.example.usr.mymoney.Adapter.RecyclerItemClickListener;
import com.example.usr.mymoney.DataBase.DbHelper;
import com.example.usr.mymoney.Entity.FinanceObject;
import com.example.usr.mymoney.R;
import com.example.usr.mymoney.VibrateService;

import java.util.ArrayList;
import java.util.List;

public class EditingFinanceObjActivity extends AppCompatActivity {

    private Menu menu;
    private DbHelper dbHelper;
    private RVAdapterObject adapter;
    private String name;
    private String date;
    private int imgId;
    private int number;
    private double currentCount;
    private List<FinanceObject> objects;
    private final int DIAOLG_ID = 1;
    private int selected = 0;
    private RecyclerView recyclerView;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //region Toolbar
        setContentView(R.layout.activity_editing_finance_obj);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //endregion

        dbHelper = new DbHelper(this);
        objects = new ArrayList<>();
        currentCount = MainActivity.getCurrentCount(dbHelper, currentCount);

        Bundle extras = getIntent().getExtras();
        name = extras.getString("name");
        date = extras.getString("date");
        imgId = extras.getInt("imgId");
        number = extras.getInt("number");

        objects = dbHelper.getOneSection(name, date, number);
        recyclerView = (RecyclerView) findViewById(R.id.rv_editing_obj);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RVAdapterObject(objects);

        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, final int position) {

                        final int objId = objects.get(position).getObjId();
                        final String oldAmount = objects.get(position).getAmount().toString();
                        Intent intent = new Intent(EditingFinanceObjActivity.this, EditingOneObjActivity.class);
                        intent.putExtra("number", number);
                        intent.putExtra("objId", objId);
                        intent.putExtra("imgId", imgId);
                        intent.putExtra("name", name);
                        intent.putExtra("oldAmount", oldAmount);
                        intent.putExtra("day", objects.get(position).getDay());
                        intent.putExtra("date", date);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        selected = position;
                        Intent intentVibrate =new Intent(getApplicationContext(),VibrateService.class);
                        startService(intentVibrate);
                        showDialog(DIAOLG_ID);
                    }
                })
        );

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIAOLG_ID:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Вы уверены, что хотите ЭТО удалить? :D")
                        .setCancelable(false)
                        .setPositiveButton("Удалить",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        final int objId = objects.get(selected).getObjId();
                                        final String oldAmount = objects.get(selected).getAmount().toString();
                                        FinanceObject financeObject = new FinanceObject();
                                        financeObject.setNameObj(name);
                                        financeObject.setAmount(Double.valueOf(oldAmount));
                                        if (number == 2) {
                                            dbHelper.deleteIncomeObject(financeObject, objId);
                                            dbHelper.minusDB(Double.valueOf(oldAmount));
                                        } else {
                                            dbHelper.deleteSpendingObject(financeObject, objId);
                                            dbHelper.plusDB(Double.valueOf(oldAmount));
                                        }

                                        currentCount = Double.valueOf(dbHelper.getCurrentCount());
                                        menu.getItem(0).setTitle(String.valueOf(currentCount));

                                        objects = dbHelper.getOneSection(name, date, number);
                                        adapter.updateAll(objects);
                                        if(adapter.getItemCount() == 0){
                                            textView = (TextView) findViewById(R.id.tv_editing_fin_obj);
                                            textView.setVisibility(View.VISIBLE);
                                        }
                                        recyclerView.setAdapter(adapter);
                                        dialog.cancel();
                                    }
                                })
                        .setNegativeButton("Отмена",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        dialog.cancel();
                                    }
                                });

                return builder.create();
            default:
                return null;
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        objects = dbHelper.getOneSection(name, date, number);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_editing_obj);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RVAdapterObject(objects);
        recyclerView.setAdapter(adapter);

        currentCount = Double.valueOf(dbHelper.getCurrentCount());
        menu.getItem(0).setTitle(String.valueOf(currentCount));
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

}
