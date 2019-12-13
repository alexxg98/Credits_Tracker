package com.example.credits_tracker;

import java.util.ArrayList;

public class CourseList {
    private static ArrayList<Courses> courseList = new ArrayList<>();


    public static ArrayList<Courses> getCourseList() {
        return courseList;
    }

    public void setCourseList(ArrayList<Courses> courseList) {
        this.courseList = courseList;
    }
}
