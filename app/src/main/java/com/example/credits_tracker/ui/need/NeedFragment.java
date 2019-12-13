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
import com.example.credits_tracker.R;

import java.util.ArrayList;

public class NeedFragment extends Fragment{
    CardView csc103, csc104;
    ArrayList<Courses> courseList;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_need, container, false);

        courseList = CourseList.getCourseList();
        csc103 = (CardView)view.findViewById(R.id.course_103);
        csc104 = (CardView)view.findViewById(R.id.course_104);

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

        //Change card color if course passed
        if (!courseList.isEmpty()) {
            if (CourseList.coursePassed(0)) {
                csc103.setCardBackgroundColor(Color.rgb(50, 205, 50));
//            System.out.println("Works");
            }
            if (CourseList.coursePassed(1)) {
                csc104.setCardBackgroundColor(Color.rgb(50, 205, 50));
            }
        }


//        String test = courseList.get(0).toString();
//        Toast.makeText(getActivity(),test, Toast.LENGTH_SHORT).show();
        return view;
    }


}