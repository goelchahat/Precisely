package com.pankaj.maukascholars.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.pankaj.maukascholars.R;
import com.pankaj.maukascholars.adapters.VerticalPagerAdapterWithViewPager;
import com.pankaj.maukascholars.application.VolleyHandling;
import com.pankaj.maukascholars.database.DBHandler;
import com.pankaj.maukascholars.holders.VerticalViewPager;
import com.pankaj.maukascholars.util.Constants;
import com.pankaj.maukascholars.util.CustomTabHelper;
import com.pankaj.maukascholars.util.EventDetails;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project Name: 	<Mauka>
 * Author List: 		Pankaj Baranwal
 * Filename: 		<>
 * Functions: 		<>
 * Global Variables:	<>
 * Date of Creation:    <19/01/2018>
 */
public class VerticalViewPagerWithPageAdapterActivity extends AppCompatActivity implements View.OnClickListener {

    private List<EventDetails> mItems = new ArrayList<>();
    RelativeLayout loading;
    ProgressView progress;
    VerticalViewPager verticalViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inshorts);
        loading = findViewById(R.id.progress_rl);
        progress = findViewById(R.id.progress);
        progress.start();
        loading.setVisibility(View.VISIBLE);
        getData();
    }

    private void init() {

        verticalViewPager = findViewById(R.id.verticleViewPager);
        VerticalPagerAdapterWithViewPager adapter = new VerticalPagerAdapterWithViewPager(this, mItems);
        verticalViewPager.setAdapter(adapter);
        LinearLayout share, star, save, send;
        share = findViewById(R.id.share);
        star = findViewById(R.id.star);
        save = findViewById(R.id.save);
        send= findViewById(R.id.stalk);
        share.setOnClickListener(this);
        star.setOnClickListener(this);
        save.setOnClickListener(this);
        send.setOnClickListener(this);

        loading.setVisibility(View.GONE);
        progress.stop();
    }

    void getData(){
        StringRequest request = new StringRequest(Request.Method.POST, Constants.url_event_details, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.length() > 0){
                    try {
                        if (response.contains("[[\""))
                            response = response.substring(response.indexOf("[[\""));
//                        response = removeEscapeChars(response);
                        JSONArray jA = new JSONArray(response);
                        for (int i = 0; i < jA.length(); i++){
                            mItems.add(new EventDetails(Integer.parseInt(jA.getJSONArray(i).get(0).toString()), jA.getJSONArray(i).get(1).toString(), jA.getJSONArray(i).get(2).toString(), jA.getJSONArray(i).get(3).toString(), jA.getJSONArray(i).get(4).toString(), jA.getJSONArray(i).get(5).toString(), jA.getJSONArray(i).get(6).toString(), jA.getJSONArray(i).get(7).toString()));
                        }
                        init();
                    } catch (JSONException e) {
                        loading.setVisibility(View.GONE);
                        progress.stop();
                        Toast.makeText(VerticalViewPagerWithPageAdapterActivity.this, "ERRORORROROR", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }else{
                    loading.setVisibility(View.GONE);
                    progress.stop();
                    Toast.makeText(VerticalViewPagerWithPageAdapterActivity.this, "Server is no longer speaking to you", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VerticalViewPagerWithPageAdapterActivity.this, "Couldn't connect to server", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
                getData();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                JSONArray jO = new JSONArray();
                for (int i = 0; i < Constants.clickedFilters.size(); i++){
                    jO.put(Constants.filters.get(Constants.clickedFilters.get(i)));
                }
                params.put("id", Constants.user_id);
                params.put("page", "0");
                params.put("tags", jO.toString());
                return params;
            }
        };

        VolleyHandling.getInstance().addToRequestQueue(request, "signin");
    }

    @Override
    public void onClick(View view) {
        int position = verticalViewPager.getCurrentItem();
        switch (view.getId()){
            case R.id.share:
                share(position);
                break;
            case R.id.star:
                starEvent(position);
                break;
            case R.id.save:
                saveEvent(position);
                break;
            case R.id.stalk:
                open(position);
                break;

        }
    }

    private void open(int position) {
        String url = mItems.get(position).getLink();
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;
//        if (url.startsWith("https"))
//            url = url.replace("https://", "http://");
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
//        LinkInWebViewFragment fragment = LinkInWebViewFragment.newInstance(url);
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.add(R.id.container, fragment).addToBackStack(fragment.getTag()).commit();
//        Intent i = new Intent(Intent.ACTION_VIEW);
//        i.setData(Uri.parse(url));
//        startActivity(i);
    }

    private void share(int position) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey! Found a great opportunity for you!\n" + mItems.get(position).getLink() + "\nThere are many more where this came from!\nVisit https://goo.gl/1RGidK now!");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    private void starEvent(int position) {
        DBHandler db = new DBHandler(this);
        if (db.getEvent(mItems.get(position).getId()) != null && db.getEvent(mItems.get(position).getId()).getStarred() == 1){
            Toast.makeText(this, "Already starred!", Toast.LENGTH_SHORT).show();
        }else{
            if (db.getEvent(mItems.get(position).getId()) != null)
                mItems.get(position).setSaved(1);
            mItems.get(position).setStarred(1);
            db.addEvent(mItems.get(position));
            Toast.makeText(this, "Event Starred!", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveEvent(int position) {
        DBHandler db = new DBHandler(this);
        if (db.getEvent(mItems.get(position).getId()) != null && db.getEvent(mItems.get(position).getId()).getSaved() == 1){
            Toast.makeText(this, "Already saved!", Toast.LENGTH_SHORT).show();
        }else {
            if (db.getEvent(mItems.get(position).getId()) != null)
                mItems.get(position).setStarred(1);
            mItems.get(position).setSaved(1);
            db.addEvent(mItems.get(position));
            Toast.makeText(this, "Event Saved!", Toast.LENGTH_SHORT).show();
        }
    }

}