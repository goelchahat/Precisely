package com.example.pankaj.maukascholars.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.pankaj.maukascholars.R;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

/**
 * Project Name: 	<Visual Perception For The Visually Impaired>
 * Author List: 		Pankaj Baranwal
 * Filename: 		<>
 * Functions: 		<>
 * Global Variables:	<>
 */
public class HamButtonActivity extends AppCompatActivity {
    private static int[] imageResources = new int[]{
            R.mipmap.bat,
            R.mipmap.bear,
    };
    private BoomMenuButton bmb;

    private static int imageResourceIndex = 0;

    int getImageResource() {
        if (imageResourceIndex >= imageResources.length) imageResourceIndex = 0;
        return imageResources[imageResourceIndex++];
    }

    HamButton.Builder getHamButtonBuilder(String text, String subText) {
        return new HamButton.Builder()
                .normalImageRes(getImageResource())
                .normalText(text)
                .subNormalText(subText);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ham_button);

        bmb = findViewById(R.id.bmb);
        assert bmb != null;
        bmb.setButtonEnum(ButtonEnum.Ham);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.HAM_3);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_3);
        bmb.addBuilder(getHamButtonBuilder("a", "a"));
        bmb.addBuilder(getHamButtonBuilder("b", "b"));
        bmb.addBuilder(getHamButtonBuilder("c", "c"));
    }
}