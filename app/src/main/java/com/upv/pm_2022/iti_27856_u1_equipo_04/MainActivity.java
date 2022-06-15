package com.upv.pm_2022.iti_27856_u1_equipo_04;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private final String dbName = "merch.db";
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    public ViewPagerAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.ViewPager);
        tabLayout.addTab(tabLayout.newTab().setText("Brand"));
        tabLayout.addTab(tabLayout.newTab().setText("Product"));
        tabLayout.addTab(tabLayout.newTab().setText("Stores"));
        tabLayout.addTab(tabLayout.newTab().setText("Prices"));
        tabLayout.addTab(tabLayout.newTab().setText("Comparative"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager2.setAdapter(adapter);

        String[] tabs = new String[]{"Brands","Products","Stores","Prices","Comparatives"};
        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> tab.setText(tabs[position])
        ).attach();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.Menu_about:
                // Show dialog say our names
                AlertDialog.Builder ADX;
                AlertDialog AD;
                ADX = new AlertDialog.Builder(this);
                AD = ADX.create();
                AD.setMessage(
                        "Josue Elisoe Perales Melendez Y Alcocer \n" +
                        "Carlos Eduardo Sanchez Charles \n" +
                        "Hector Varela Grimaldo\n" +
                        "Sandra Anel Baez Guerrero"
                        );
                AD.show();
                return true;
            case R.id.Menu_import:
                return true;
            case R.id.Menu_export:
                return true;
            case R.id.Menu_graphic:
                Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.dialog_chart);
                BarChart bchart = findViewById(R.id.chart);
                ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
                for (int i = 0; i < 10 + 1; i++) {
                    float val = (float) (Math.random());
                    yVals1.add(new BarEntry(i, val));
                }
                BarDataSet set1;
                set1 = new BarDataSet(yVals1, "The year 2017");
                set1.setColors(ColorTemplate.MATERIAL_COLORS);
                ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
                dataSets.add(set1);
                BarData data = new BarData(dataSets);
                data.setValueTextSize(10f);
                data.setBarWidth(0.9f);
                bchart.setTouchEnabled(true);
                bchart.setData(data);
                bchart.animateXY(2000, 2000);
                bchart.invalidate();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}