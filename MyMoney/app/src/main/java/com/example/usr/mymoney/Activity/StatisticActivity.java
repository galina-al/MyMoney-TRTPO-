package com.example.usr.mymoney.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.usr.mymoney.DataBase.DbHelper;
import com.example.usr.mymoney.IncomeStatisticFragment;
import com.example.usr.mymoney.R;
import com.example.usr.mymoney.SpendingStatisticFragment;

public class StatisticActivity extends AppCompatActivity {

    FragmentPagerAdapter adapterViewPager;
    private ViewPager mViewPager;
    DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ViewPager vpPager = (ViewPager) findViewById(R.id.view_pager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.current_count) {
            return true;
        }

        if (id == android.R.id.home) {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }


    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 2;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return SpendingStatisticFragment.newInstance(0, "Расходы");
                case 1:
                    return IncomeStatisticFragment.newInstance(1, "Доходы");
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            CharSequence title = "";
            switch (position){
                case 0:
                    title = "Расходы";
                    break;
                case 1:
                    title = "Доходы";
                    break;
            }
            return title;
        }

    }
}

/*pieChart = (PieChart) findViewById(R.id.pie_chart);
        pieChart.setDescription("Sales by employee (In Thousands $)");
        pieChart.setRotationEnabled(true);

        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("Super Cool chart");
        pieChart.setCenterTextSize(10);

        addDataSet(pieChart);

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, int i, Highlight highlight) {

            }

            @Override
            public void onNothingSelected() {

            }
        });*/


        /*PieChart chart = (PieChart) findViewById(R.id.pie_chart);

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(4f, 0));
        entries.add(new Entry(8f, 1));
        entries.add(new Entry(6f, 2));
        entries.add(new Entry(12f, 3));
        entries.add(new Entry(18f, 4));
        entries.add(new Entry(9f, 5));

        PieDataSet dataset = new PieDataSet(entries, "# of Calls");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Январь");
        labels.add("Февраль");
        labels.add("Март");
        labels.add("Апрель");
        labels.add("Май");
        labels.add("Июнь");

        PieData data = new PieData(labels, dataset);
        chart.setData(data);
        chart.invalidate();*/