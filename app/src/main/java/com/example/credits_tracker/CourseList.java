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

    public static boolean coursePassed(int index){
        boolean status = false;

        if (courseList.size()<=index){
            status = false;
        }
        else {
            Character course = courseList.get(index).getGrade().charAt(0);
            if (course == 'A' || course == 'B' || course == 'C') {
                status = true;
            }
        }
        return status;
    }
}
