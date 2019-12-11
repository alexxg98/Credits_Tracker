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

import com.example.credits_tracker.CourseInfo;
import com.example.credits_tracker.Courses;
import com.example.credits_tracker.R;

import java.util.ArrayList;

public class OverviewFragment extends Fragment{

    private NotificationsViewModel overviewViewModel;

    private EditText displayQPA;
    private ArrayList<Courses> coursesList;
    public int points;
    private Character grade103;
    private int qpaTotal = 0, qpa103;

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


        displayQPA = (EditText)root.findViewById(R.id.qpaDisplay);
        coursesList = CourseInfo.getCourseList();
        if (!coursesList.isEmpty()) {
            grade103 = coursesList.get(0).getGrade().charAt(0);
            qpa103 = convertGrade(grade103, 3);
            displayQPA.setText("" + qpa103);
        }
        else {
            displayQPA.setText("0");
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
        return qpaTotal + points;
    }
}