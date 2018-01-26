package com.pankaj.maukascholars.adapters;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pankaj.maukascholars.R;
import com.pankaj.maukascholars.fragments.CardFragment;
import com.pankaj.maukascholars.fragments.ProfileFragment;
import com.pankaj.maukascholars.fragments.WebViewFragment;
import com.pankaj.maukascholars.util.EventDetails;

import java.util.List;

/**
 * Project Name: 	<Mauka>
 * Author List: 		Pankaj Baranwal
 * Filename: 		<>
 * Functions: 		<>
 * Global Variables:	<>
 * Date of Creation:    <19/01/2018>
 */
public class VerticalPagerAdapterWithViewPager extends PagerAdapter {

    private AppCompatActivity mContext;
    private LayoutInflater mLayoutInflater;
    private List<EventDetails> cards;
    DisplayMetrics displayMetrics = new DisplayMetrics();
    int height;
    int width;
    CardFragment cF;
    WebViewFragment wvF;
    ProfileFragment pF;
    private PagerAdapter mPagerAdapter;

    public VerticalPagerAdapterWithViewPager(AppCompatActivity context, List<EventDetails> cards) {
        this.cards = cards;
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContext.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
    }

    @Override
    public int getCount() {
        return cards == null ? 0 : cards.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int i) {
        final View itemView = mLayoutInflater.inflate(R.layout.item_card_inshorts_updated, container, false);
        ViewPager viewPager =  itemView.findViewById(R.id.viewpager);
        mPagerAdapter = new ScreenSlidePagerAdapter(mContext.getSupportFragmentManager());
        viewPager.setAdapter(mPagerAdapter);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((CoordinatorLayout) object);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Toast.makeText(mContext, "...", Toast.LENGTH_SHORT).show();
            return ProfileFragment.newInstance("");
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}