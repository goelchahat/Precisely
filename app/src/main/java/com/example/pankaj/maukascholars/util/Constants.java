package com.example.pankaj.maukascholars.util;

import com.example.pankaj.maukascholars.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pankaj on 17/11/17.
 */

public class Constants {
    public static int[] imageResources = new int[]{
            R.mipmap.bat,
            R.mipmap.bear,
            R.mipmap.bear,
    };
    public static String[] normal = new String[]{
            "SHARE",
            "SHORTLIST",
            "SAVE"
    };
    public static String[] sub = new String[]{
            "I should share it with others",
            "Seems interesting! I'll decide later",
            "Notify me of important updates"
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
            "Projects ",
            "Summer / Winter School",
            "Trainings",
            "Exchange Programs ",
            "Admissions / Study ",
            "Workshops",
            "Events",
            "Grants",
            "Financial Aid ",
            "Online Courses",
            "Work Opportunities",
            "Jobs"));
    public static List<Integer> clickedFilters = new ArrayList<>();
}
