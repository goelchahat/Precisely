package com.example.pankaj.maukascholars.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.pankaj.maukascholars.R;
import com.example.pankaj.maukascholars.fragments.EventsAppliedFragment;
import com.example.pankaj.maukascholars.fragments.FavouritesFragment;
import com.example.pankaj.maukascholars.fragments.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity implements EventsAppliedFragment.OnListFragmentInteractionListener, FavouritesFragment.OnListFragmentInteractionListener{

    SharedPreferences sp;
    EventsAppliedFragment eaf;
    FavouritesFragment ff;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sp = PreferenceManager.getDefaultSharedPreferences(this);

        ViewPager viewPager =  findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout =  findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.mipmap.ap_resize);
        tabLayout.getTabAt(1).setIcon(R.mipmap.history);

//        viewPager.setCurrentItem(1);
    }


    public void setupViewPager(ViewPager upViewPager) {
        eaf = new EventsAppliedFragment();
        ff = new FavouritesFragment();
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(eaf, "History");
        adapter.addFragment(ff, "Starred");
        upViewPager.setAdapter(adapter);
    }

    public void getAllPosts(int counter){

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_inside, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

//        if(id == R.id.action_shout){
//            // TODO: FILL THIS SECTION
//        }else{
//            finish();
//        }
        return super.onOptionsItemSelected(item);
    }
}