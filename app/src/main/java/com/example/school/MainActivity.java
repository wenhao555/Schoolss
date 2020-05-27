package com.example.school;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.school.fragment.MessageFragment;
import com.example.school.fragment.MineFragment;
import com.example.school.fragment.NewsFragment;
import com.example.school.fragment.ProductFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigation;
    private Fragment[] fragmentsUser;
    private NewsFragment newsFragment = new NewsFragment();
    private MessageFragment messageFragment = new MessageFragment();
    private ProductFragment productFragment = new ProductFragment();
    private MineFragment mineFragment = new MineFragment();
    private int lastFragments = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigation = findViewById(R.id.bottomNavigation);
        fragmentsUser = new Fragment[]{newsFragment, messageFragment, productFragment, mineFragment};
        getSupportFragmentManager().beginTransaction().replace(R.id.mframeLayout, fragmentsUser[0]).show(fragmentsUser[0]).commit();
        bottomNavigation.setOnNavigationItemSelectedListener(changeFragment);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener changeFragment = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.news:
                    if (lastFragments != 0) {
                        switchFragment(lastFragments, 0);
                        lastFragments = 0;
                    }
                    return true;
                case R.id.message:
                    if (lastFragments != 1) {
                        switchFragment(lastFragments, 1);
                        lastFragments = 1;
                    }

                    return true;
                case R.id.prodcut:
                    if (lastFragments != 2) {
                        switchFragment(lastFragments, 2);
                        lastFragments = 2;
                    }
                    return true;
                case R.id.mine:
                    if (lastFragments != 3) {
                        switchFragment(lastFragments, 3);
                        lastFragments = 3;
                    }

                    return true;
            }

            return false;
        }
    };

    private void switchFragment(int lastFragments, int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragmentsUser[lastFragments]);
        if (!fragmentsUser[index].isAdded()) {
            transaction.add(R.id.mframeLayout, fragmentsUser[index]);
        }
        transaction.show(fragmentsUser[index]).commitAllowingStateLoss();
    }
}
