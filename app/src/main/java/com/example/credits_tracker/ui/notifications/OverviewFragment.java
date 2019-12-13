package com.example.credits_tracker.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.credits_tracker.CourseList;
import com.example.credits_tracker.Courses;
import com.example.credits_tracker.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class OverviewFragment extends Fragment{

    private NotificationsViewModel overviewViewModel;

    private EditText displayQPA;
//    private ArrayList<Courses> coursesList;
    public int points;
    private int qpaTotal = 0;

    ArrayList<Courses> courseList;
    ArrayList<Character> courseGrades;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        overviewViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_overview, container, false);
        final TextView textView = root.findViewById(R.id.text_qpa);
        overviewViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        courseGrades = new ArrayList<>();
        displayQPA = (EditText)root.findViewById(R.id.qpaDisplay);
        courseList = CourseList.getCourseList();
        
        if (courseList.isEmpty()) {
            displayQPA.setText("No Courses Taken Yet!");
        }
        else {
            //Calculate the QPA
            int i = 0;
            while (i< courseList.size()){
                if (CourseList.coursePassed(i)){
                    Character g = courseList.get(i).getGrade().charAt(0);
                    System.out.println(g);
                    courseGrades.add(g);
                }
                i++;
            }
            for (Character c : courseGrades){
                qpaTotal += convertGrade(c, 3);
                System.out.println(qpaTotal);
            }
            displayQPA.setText("" + qpaTotal);

        }
        return root;
    }
    public int convertGrade(Character grade, int credits){
        switch (grade){
            case 'A':
                points = 2*credits;
                break;
            case 'B':
                points = credits;
                break;
            case 'C':
                points = 0;
                break;
            case 'D':
                points = -1*credits;
                break;
            case 'F':
                points = -2*credits;
                break;
        }
        return points;
    }
}