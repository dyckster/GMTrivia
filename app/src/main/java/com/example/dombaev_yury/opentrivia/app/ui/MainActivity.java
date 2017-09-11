package com.example.dombaev_yury.opentrivia.app.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.dyckster.opentrivia.R;
import com.example.dombaev_yury.opentrivia.app.ui.categories.CategoriesFragment;

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            setCurrentFragment(new MainFragment(), false);
        }
    }


    public void setCurrentFragment(@NonNull Fragment fragment, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment_container, fragment, fragment.getClass().getSimpleName());
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        fragmentTransaction.commit();
    }

    /**
     * Sets current fragment adding it to backstack
     *
     * @param fragment
     */
    public void setCurrentFragment(@NonNull Fragment fragment) {
        setCurrentFragment(fragment, true);
    }

}
