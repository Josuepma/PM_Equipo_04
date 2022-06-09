package com.upv.pm_2022.iti_27856_equipo_04;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private final int NUM_TABS = 3;

    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager,lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1)
            return new ProductFragment();
        if (position == 2)
            return new ComparativeFragment();
        return new BrandFragment();
    }

    @Override
    public int getItemCount() {
        return NUM_TABS;
    }
}