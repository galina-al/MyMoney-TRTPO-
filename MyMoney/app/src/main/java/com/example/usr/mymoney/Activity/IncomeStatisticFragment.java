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
import android.widget.TextView;

import com.example.usr.mymoney.Adapter.RVAdapterSection;
import com.example.usr.mymoney.Adapter.RecyclerItemClickListener;
import com.example.usr.mymoney.DataBase.DbHelper;
import com.example.usr.mymoney.Entity.Section;
import com.example.usr.mymoney.R;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


public class IncomeStatisticFragment extends Fragment {

    private String title;
    private int page;
    private DbHelper dbHelper;
    private List<Section> sections;
    private RVAdapterSection adapter;
    private RecyclerView recyclerView;
    private String selectedMonth;
    private TextView textView;

    public static IncomeStatisticFragment newInstance(int page, String title) {
        IncomeStatisticFragment fragmentFirst = new IncomeStatisticFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", "Доходы");
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 1);
        title = getArguments().getString("someTitle");
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_income_statistic, container, false);


        recyclerView = (RecyclerView) view.findViewById(R.id.rv_statistic_inc);

        final Spinner spinner = (Spinner) view.findViewById(R.id.spinner_inc);
        dbHelper = new DbHelper(getContext());
        sections = new ArrayList<>();
        List<String> allDate = dbHelper.getAllDate(2);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, allDate);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                selectedMonth = spinner.getSelectedItem().toString();
                sections = dbHelper.getTotalAmount(selectedMonth, 2);
                for (int k = 0; k < sections.size(); k++) {
                    sections.get(k).setPercent(getPercent(Double.parseDouble(sections.get(k).getAmount())));
                }
                final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setLayoutManager(layoutManager);
                adapter = new RVAdapterSection(sections);
                recyclerView.setAdapter(adapter);
                /*if(adapter.getItemCount() > 0){
                    textView = (TextView) getActivity().findViewById(R.id.tv_fragm_income);
                    textView.setVisibility(View.INVISIBLE);
                }*/
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
                        intent.putExtra("number", 2);

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
        sections = dbHelper.getTotalAmount(selectedMonth, 2);
        for (int k = 0; k < sections.size(); k++) {
            sections.get(k).setPercent(getPercent(Double.parseDouble(sections.get(k).getAmount())));
        }
        adapter = new RVAdapterSection(sections);
        adapter.updateAll(sections);
        recyclerView.setAdapter(adapter);
        /*if(adapter.getItemCount() > 0){
            textView = (TextView) getActivity().findViewById(R.id.tv_fragm_income);
            textView.setVisibility(View.INVISIBLE);
        }*/
    }

    public String getPercent(double amount) {
        Double precent = new BigDecimal((amount * 100 / dbHelper.getTotalIncome())).setScale(1, RoundingMode.UP).doubleValue();
        String strPrecent = precent + "%";
        return strPrecent;
    }


}
