package com.upv.pm_2022.iti_27856_equipo_04;

import android.os.Bundle;

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
        tabLayout.addTab(tabLayout.newTab().setText("Comparative"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager2.setAdapter(adapter);

        String[] tabs = new String[]{"Brand","Product","Comparative"};
        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> tab.setText(tabs[position])
        ).attach();

    }


}