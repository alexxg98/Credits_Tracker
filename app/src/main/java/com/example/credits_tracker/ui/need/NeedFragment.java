package com.example.credits_tracker.ui.need;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.credits_tracker.CourseInfo;
import com.example.credits_tracker.MainActivity;
import com.example.credits_tracker.R;

public class NeedFragment extends Fragment implements View.OnClickListener{
    CardView csc103;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_need, container, false);
        csc103 = (CardView) view.findViewById(R.id.course_103);
        csc103.setOnClickListener(this);
        return view;
    }
    @Override
    public void onClick(View v){
        startActivity(new Intent(getActivity(), CourseInfo.class));
    }
}