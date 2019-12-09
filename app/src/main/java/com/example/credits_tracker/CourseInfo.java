package com.example.credits_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class CourseInfo extends AppCompatActivity {

    private RadioButton taken, inProgress, untaken;
    private ImageButton closeBtn;
    private Button saveBtn;
    private TextView termInput, gradeInput;
    ArrayList<Courses> courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_popup);
        taken = (RadioButton)findViewById(R.id.radioBtn_Taken);
        inProgress = (RadioButton)findViewById(R.id.radioBtn_inProgress);
        untaken = (RadioButton)findViewById(R.id.radioBtn_untaken);
        termInput = (TextView)findViewById(R.id.input_term);
        gradeInput = (TextView)findViewById(R.id.input_grade);

        loadData();
        
        saveBtn = (Button)findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String term = termInput.getText().toString().toUpperCase();
                String grade = gradeInput.getText().toString().toUpperCase();
                Courses c103 = new Courses(term, grade);
                courseList.set(0,c103);
                String test = courseList.get(2).toString();
                Toast.makeText(getApplicationContext(),test, Toast.LENGTH_SHORT).show();
                saveData();
            }
        });

        closeBtn = (ImageButton)findViewById(R.id.closeButton);
        closeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onBackPressed();
            }
        });
    }

    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(courseList);
        editor.putString("task list", json);
        editor.apply();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<Courses>>() {}.getType();
        courseList = gson.fromJson(json, type);

        if (courseList == null){
            courseList = new ArrayList<>();
        }
    }



    public void typeClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();

        String test = "";
        switch (view.getId()){
            case R.id.radioBtn_Taken:
                if (checked)
                    test = "Taken Selected";
                    termInput.setEnabled(true);
                    termInput.setFocusable(true);
                    gradeInput.setEnabled(true);
                    gradeInput.setFocusable(true);
                    termInput.setText(courseList.get(0).getTerm());
                    gradeInput.setText(courseList.get(0).getGrade());
                    break;
            case R.id.radioBtn_inProgress:
                if (checked)
                    test = "In-Progress Selected";
                    termInput.setEnabled(false);
                    termInput.setFocusable(false);
                    gradeInput.setEnabled(false);
                    gradeInput.setFocusable(false);
                    termInput.setText("In Progress");
                    gradeInput.setText("NA");
                    break;
            case R.id.radioBtn_untaken:
                if (checked)
                    test = "Untaken Selected";
                    termInput.setEnabled(false);
                    termInput.setFocusable(false);
                    gradeInput.setEnabled(false);
                    gradeInput.setFocusable(false);
                    termInput.setText("NA");
                    gradeInput.setText("NA");
                    break;
        }
        Toast.makeText(getApplicationContext(),test, Toast.LENGTH_SHORT).show();
    }


}
