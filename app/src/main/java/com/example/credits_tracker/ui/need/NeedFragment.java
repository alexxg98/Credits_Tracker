package com.example.credits_tracker.ui.need;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.credits_tracker.CourseList;
import com.example.credits_tracker.Courses;
import com.example.credits_tracker.Info103;
import com.example.credits_tracker.Info104;
import com.example.credits_tracker.Info113;
import com.example.credits_tracker.Info211;
import com.example.credits_tracker.Info212;
import com.example.credits_tracker.R;

import java.util.ArrayList;

public class NeedFragment extends Fragment{
    CardView csc103, csc104, csc113, csc211, csc212;
    ArrayList<Courses> courseList;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_need, container, false);

        courseList = CourseList.getCourseList();
        csc103 = (CardView)view.findViewById(R.id.course_103);
        csc104 = (CardView)view.findViewById(R.id.course_104);
        csc113 = (CardView)view.findViewById(R.id.course_113);
        csc211 = (CardView)view.findViewById(R.id.course_211);
        csc212 = (CardView)view.findViewById(R.id.course_212);

        //when click on card, open the corresponding activity
        csc103.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Info103.class));
            }
        });
        csc104.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Info104.class));
            }
        });
        csc113.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Info113.class));
            }
        });
        csc211.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Info211.class));
            }
        });
        csc212.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Info212.class));
            }
        });

        //Change card color if course passed
        if (!courseList.isEmpty()) {
            if (CourseList.coursePassed(0)) {
                csc103.setCardBackgroundColor(Color.rgb(50, 205, 50));
            }
            if (CourseList.coursePassed(1)) {
                csc104.setCardBackgroundColor(Color.rgb(50, 205, 50));
            }
            if (CourseList.coursePassed(2)){
                csc113.setCardBackgroundColor(Color.rgb(50, 205, 50));
            }
            if (CourseList.coursePassed(3)){
                csc211.setCardBackgroundColor(Color.rgb(50, 205, 50));
            }
            if (CourseList.coursePassed(4)){
                csc212.setCardBackgroundColor(Color.rgb(50, 205, 50));
            }
        }

        return view;
    }


}