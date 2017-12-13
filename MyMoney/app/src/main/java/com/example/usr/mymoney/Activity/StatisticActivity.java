package com.example.usr.mymoney.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.usr.mymoney.DataBase.DbHelper;
import com.example.usr.mymoney.R;

public class StatisticActivity extends AppCompatActivity {

    private FragmentPagerAdapter adapterViewPager;
    private DbHelper dbHelper;
    private Menu menu;
    private double currentCount;

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

        dbHelper = new DbHelper(this);
        currentCount = MainActivity.getCurrentCount(dbHelper, currentCount);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
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

        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

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

        @Override
        public CharSequence getPageTitle(int position) {
            CharSequence title = "";
            switch (position) {
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
