package com.example.pankaj.maukascholars.util;

import com.example.pankaj.maukascholars.holders.FiltersViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pankaj on 17/11/17.
 */

public class Constants {
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
