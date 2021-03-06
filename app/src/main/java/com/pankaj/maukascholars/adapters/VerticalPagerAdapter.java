package com.pankaj.maukascholars.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pankaj.maukascholars.R;
import com.pankaj.maukascholars.util.EventDetails;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.pankaj.maukascholars.util.Constants.months;

/**
 * Project Name: 	<Visual Perception For The Visually Impaired>
 * Author List: 		Pankaj Baranwal
 * Filename: 		<>
 * Functions: 		<>
 * Global Variables:	<>
 */
public class VerticalPagerAdapter extends PagerAdapter {

    private Activity mContext;
    private LayoutInflater mLayoutInflater;
    private List<EventDetails> cards;
    DisplayMetrics displayMetrics = new DisplayMetrics();
    int height;
    int width;
    private TextView title, description, name, deadline;
    private ImageView event_image;

    public VerticalPagerAdapter(Activity context, List<EventDetails> cards) {
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
        final View itemView = mLayoutInflater.inflate(R.layout.item_card_inshorts, container, false);
//        cardView = itemView.findViewById(R.id.card_view);
        title = itemView.findViewById(R.id.event_title);
        description = itemView.findViewById(R.id.event_description);
        name = itemView.findViewById(R.id.name_poster);
        deadline = itemView.findViewById(R.id.date_posted);
        event_image = itemView.findViewById(R.id.event_image);

        title.setText(cards.get(i).getTitle());
        description.setText(cards.get(i).getDescription());
        name.setText(cards.get(i).getName());
        Picasso.with(mContext).load(cards.get(i).getImage()).fit().error(R.mipmap.j_bezos).into(event_image);
        String date = cards.get(i).getDeadline();
        String final_date ="Deadline: " + months[Integer.parseInt(date.substring(0, 2))-1] + " " + date.substring(2);
        deadline.setText(final_date);
        container.addView(itemView);
        return itemView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}