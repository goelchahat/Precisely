package com.example.pankaj.maukascholars.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pankaj.maukascholars.R;
import com.example.pankaj.maukascholars.adapters.FiltersAdapter;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

import static com.example.pankaj.maukascholars.util.Constants.filters;

public class Filters extends AppCompatActivity {

    ImageView proceed, account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);
        TextView heading = findViewById(R.id.heading_filter);
        heading.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Quicksand-Light.ttf"));
        proceed = findViewById(R.id.proceed);
        account = findViewById(R.id.account);
//        calendar = findViewById(R.id.calendar);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if (filters.size()>0) {
                Intent intent = new Intent(Filters.this, Cards.class);
                startActivity(intent);
            }else{
                Toast.makeText(Filters.this, "Please select at least one filter!", Toast.LENGTH_SHORT).show();
            }
            }
        });
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Filters.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
//        calendar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                long startMillis = System.currentTimeMillis();
//                Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
//                builder.appendPath("time");
//                ContentUris.appendId(builder, startMillis);
//                Intent intent = new Intent(Intent.ACTION_VIEW).setData(builder.build());
//                startActivity(intent);
//            }
//        });
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setAlignItems(AlignItems.STRETCH);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter adapter = new FiltersAdapter(this, filters);
        recyclerView.setAdapter(adapter);
    }
}
