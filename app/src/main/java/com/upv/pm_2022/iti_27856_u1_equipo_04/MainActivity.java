package com.upv.pm_2022.iti_27856_u1_equipo_04;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class MainActivity extends AppCompatActivity {

    private final String dbName = "merch.db";
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;

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
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
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
                        "Carlos Sanchez Charles \n" +
                        "Hector Varela \n" +
                        "Anel Baez"
                        );
                AD.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}