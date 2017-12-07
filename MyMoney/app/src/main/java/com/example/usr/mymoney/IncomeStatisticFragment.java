package com.example.usr.mymoney;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.usr.mymoney.DataBase.DbHelper;

import java.util.List;


public class IncomeStatisticFragment extends Fragment {

    private String title;
    private int page;
    DbHelper dbHelper;
    List<Section> sections;
    int pageNumber;
    RVAdapter adapter;

    // newInstance constructor for creating fragment with arguments
    public static IncomeStatisticFragment newInstance(int page, String title) {
        IncomeStatisticFragment fragmentFirst = new IncomeStatisticFragment();
        Bundle args = new Bundle();
        //args.putInt("someInt", page);
        args.putString("someTitle", "Доходы");
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_income_statistic, container, false);

        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv_statistic_inc);

        final Spinner spinner = (Spinner) view.findViewById(R.id.spinner_inc);
        dbHelper = new DbHelper(getContext());
        List<String> allDate = dbHelper.getAllDate(2);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, allDate);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Получаем выбранный объект
                //String item = (String) adapterView.getItemAtPosition(i);


                sections = dbHelper.getTotalAmount(spinner.getSelectedItem().toString(), 2);
                final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setLayoutManager(layoutManager);
                adapter = new RVAdapter(sections);

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return view;
    }
}
