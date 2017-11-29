package com.example.bottomnavigate;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    List<Fragment> mfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        mfragment=new ArrayList<>();
       // Log.d("aaaaaaa", "onCreate: ");
        mfragment.add(new FragmentRecommend());
       // Log.d("bbbbbbbb", "onCreate: ");
        mfragment.add(new Fragmentvid());
        mfragment.add(new FragmentPhoto());
        mfragment.add(new FragmentTalk());
        viewPagerAdapter = new ViewPagerAdapter(mfragment, getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        // viewPagerAdapter=;
    }
}
