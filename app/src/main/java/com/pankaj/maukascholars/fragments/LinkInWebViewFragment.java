package com.pankaj.maukascholars.fragments;


import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pankaj.maukascholars.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LinkInWebViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LinkInWebViewFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;


    public LinkInWebViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment LinkInWebViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LinkInWebViewFragment newInstance(String param1) {
        LinkInWebViewFragment fragment = new LinkInWebViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_link_in_web_view, container, false);
//        WebView wb;
//        wb= view.findViewById(R.id.webView);
//        wb.getSettings().setJavaScriptEnabled(true);
//        wb.setWebViewClient(new HelloWebViewClient());
//        wb.loadUrl(mParam1);

        return view;
    }

//    private class HelloWebViewClient extends WebViewClient {
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            view.loadUrl(url);
//            return false;
//        }
//    }

}
