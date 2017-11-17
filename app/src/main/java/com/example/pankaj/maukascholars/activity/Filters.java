package com.example.pankaj.maukascholars.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.example.pankaj.maukascholars.R;
import com.example.pankaj.maukascholars.adapters.FiltersAdapter;
import com.example.pankaj.maukascholars.holders.FiltersViewHolder;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.pankaj.maukascholars.util.Constants.filters;

public class Filters extends AppCompatActivity {

    ImageButton proceed, account, calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);
        proceed = findViewById(R.id.proceed);
        account = findViewById(R.id.account);
        calendar = findViewById(R.id.calendar);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Filters.this, Cards.class);
                startActivity(intent);
            }
        });
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
