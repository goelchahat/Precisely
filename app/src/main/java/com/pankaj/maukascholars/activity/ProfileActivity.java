package com.pankaj.maukascholars.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.pankaj.maukascholars.R;
import com.pankaj.maukascholars.fragments.SavedFragment;
import com.pankaj.maukascholars.fragments.StarredFragment;
import com.pankaj.maukascholars.util.CustomTabHelper;
import com.pankaj.maukascholars.util.EventDetails;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity implements SavedFragment.OnListFragmentInteractionListener, StarredFragment.OnListFragmentInteractionListener{

    SharedPreferences sp;
    SavedFragment eaf;
    StarredFragment ff;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sp = PreferenceManager.getDefaultSharedPreferences(this);

        ViewPager viewPager =  findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout =  findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
         tabLayout.getTabAt(0).setText("Starred");
        tabLayout.getTabAt(1).setText("Saved");

//        viewPager.setCurrentItem(1);
    }


    public void setupViewPager(ViewPager upViewPager) {
        eaf = new SavedFragment();
        ff = new StarredFragment();
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(ff, "Starred");
        adapter.addFragment(eaf, "Saved");
        upViewPager.setAdapter(adapter);
    }

    public void getAllPosts(int counter){

    }

    @Override
    public void onListFragmentInteraction(EventDetails item) {
        String url = item.getLink();
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;
        open(url);
    }

    private void open(String url) {
        CustomTabHelper mCustomTabHelper = new CustomTabHelper();
        if (mCustomTabHelper.getPackageName(this).size() != 0) {
            CustomTabsIntent customTabsIntent =
                    new CustomTabsIntent.Builder()
                            .build();
            customTabsIntent.intent.setPackage(mCustomTabHelper.getPackageName(this).get(0));
            customTabsIntent.launchUrl(this, Uri.parse(url));
        } else {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        }
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