package com.example.usr.mymoney.Activity;

import android.content.Intent;
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
import com.example.usr.mymoney.R;
import com.example.usr.mymoney.Adapter.RVAdapterSection;
import com.example.usr.mymoney.Adapter.RecyclerItemClickListener;
import com.example.usr.mymoney.Entity.Section;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


public class SpendingStatisticFragment extends Fragment {

    private String title;
    private int page;
    private DbHelper dbHelper;
    private List<Section> sections;
    private RVAdapterSection adapter;
    private RecyclerView recyclerView;
    private String selectedMonth;

    public static SpendingStatisticFragment newInstance(int page, String title) {
        SpendingStatisticFragment fragmentFirst = new SpendingStatisticFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", "Расходы");
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spending_statistic, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_statistic_spend);

        final Spinner spinner = (Spinner) view.findViewById(R.id.spinner_spend);
        dbHelper = new DbHelper(getContext());
        sections = new ArrayList<>();
        List<String> allDate = dbHelper.getAllDate(1);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, allDate);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                selectedMonth = spinner.getSelectedItem().toString();
                sections = dbHelper.getTotalAmount(selectedMonth, 1);
                for (int k = 0; k < sections.size(); k++) {
                    sections.get(k).setPercent(getPercent(Double.parseDouble(sections.get(k).getAmount())));
                }
                final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setLayoutManager(layoutManager);
                adapter = new RVAdapterSection(sections);

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, final int position) {

                        Intent intent = new Intent(getContext(), EditingFinanceObjActivity.class);
                        intent.putExtra("name", sections.get(position).getNameSection());
                        intent.putExtra("imgId", sections.get(position).getImageId());
                        intent.putExtra("date", spinner.getSelectedItem().toString());
                        intent.putExtra("number", 1);

                        startActivity(intent);

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }

                })
        );
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        sections = dbHelper.getTotalAmount(selectedMonth, 1);
        adapter = new RVAdapterSection(sections);
        adapter.updateAll(sections);
        recyclerView.setAdapter(adapter);
    }

    public String getPercent(double amount) {
        double precent = new BigDecimal((amount * 100 / dbHelper.getTotalSpending())).setScale(1, RoundingMode.UP).doubleValue();
        String strPrecent = precent + "%";
        return strPrecent;
    }
}
