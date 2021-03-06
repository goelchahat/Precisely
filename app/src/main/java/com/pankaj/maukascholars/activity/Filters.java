package com.pankaj.maukascholars.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.pankaj.maukascholars.R;
import com.pankaj.maukascholars.adapters.FiltersAdapter;
import com.pankaj.maukascholars.util.Constants;
import com.pankaj.maukascholars.util.MyVideoView;

import org.json.JSONArray;
import org.json.JSONException;

import static com.pankaj.maukascholars.util.Constants.filters;
import static com.pankaj.maukascholars.util.Constants.key;

public class Filters extends AppCompatActivity {

    ImageView proceed, account;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    MyVideoView videoview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filters);
        setDimension();
        sp = PreferenceManager.getDefaultSharedPreferences(Filters.this);
        if (sp.contains(key)){
            try {
                JSONArray jO = new JSONArray(sp.getString(key, ""));
                Constants.clickedFilters.clear();
                for (int i = 0; i < jO.length(); i++)
                    Constants.clickedFilters.add(jO.getInt(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        proceed = findViewById(R.id.proceed);
        account = findViewById(R.id.account);
//        calendar = findViewById(R.id.calendar);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Constants.clickedFilters.size()>0) {
//                    Intent intent = new Intent(Filters.this, com.pankaj.maukascholars.activity.Cards.class);
                    Intent intent = new Intent(Filters.this, VerticalViewPagerActivity.class);
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

    private void setDimension() {
        // Adjust the size of the video
        // so it fits on the screen
        videoview = findViewById(R.id.videoView);
//        videoview.setAudioFocusRequest(AUDIOFOCUS_NONE);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.o_o_gif);
        videoview.setVideoURI(uri);
        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });
        videoview.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setDimension();
        sp = PreferenceManager.getDefaultSharedPreferences(Filters.this);
        if (sp.contains(key)){
            try {
                JSONArray jO = new JSONArray(sp.getString(key, ""));
                Constants.clickedFilters.clear();
                for (int i = 0; i < jO.length(); i++)
                    Constants.clickedFilters.add(jO.getInt(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (videoview != null)
            videoview.stopPlayback();
        saveClickedToSharedPreferences();
    }

    private void saveClickedToSharedPreferences(){
        JSONArray jO = new JSONArray();
        for (int i = 0; i < Constants.clickedFilters.size(); i++)
            jO.put(Constants.clickedFilters.get(i));

        editor = sp.edit();
        editor.remove(key).apply();
        editor.putString(key, jO.toString());
        editor.apply();
    }
}
