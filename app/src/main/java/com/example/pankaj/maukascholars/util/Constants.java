package com.example.pankaj.maukascholars.util;

import com.example.pankaj.maukascholars.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pankaj on 17/11/17.
 */

public class Constants {
    public static String user_email = "pankaj11520@ducic.ac.in";

    public static int[] imageResources = new int[]{
            R.mipmap.bat,
            R.mipmap.bear,
            R.mipmap.bear,
            R.mipmap.bat,
    };
    public static String[] normal = new String[]{
            "SHARE",
            "STAR",
            "SAVE",
            "SEND"
    };
    public static String[] sub = new String[]{
            "I should share it with others",
            "Seems interesting! I'll decide later though",
            "Notify me of important updates",
            "Send it to me via mail"
    };

    public static int[] normal_color = new int[]{
            0xff009688,
            0xff03a9f4,
            0xff795548,
            0xffe91e63
    };


    public static List<String> filters = new ArrayList<>(Arrays.asList(
            "Competitions",
            "Awards",
            "Conferences ",
            "Internships",
            "Volunteering",
            "Scholarships ",
            "Fellowships ",
            "Summer / Winter School",
            "Trainings",
            "Exchange Programs ",
            "Admissions",
            "Workshops",
            "Events",
            "Grants",
            "Financial Aid ",
            "Online Courses",
            "Jobs"));

    public static List<Integer> clickedFilters = new ArrayList<>();
}
