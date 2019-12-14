package com.example.credits_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Info212 extends AppCompatActivity {

    private RadioButton taken, inProgress, untaken;
    private RadioGroup statusRadioGroup;
    private ImageButton closeBtn;
    private Button saveBtn;
    private TextView termInput, gradeInput;

    CourseList courseList;
    ArrayList<Courses> course212;

    //default radiobutton checked: untaken
    public static int selectedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info212);

        courseList = new CourseList();
        course212 = courseList.getCourseList();
        taken = (RadioButton)findViewById(R.id.radioBtn_taken);
        inProgress = (RadioButton)findViewById(R.id.radioBtn_inProgress);
        untaken = (RadioButton)findViewById(R.id.radioBtn_untaken);
        untaken.setChecked(true);
        statusRadioGroup = (RadioGroup)findViewById(R.id.radioGroup_status);
        termInput = (TextView)findViewById(R.id.input_term);
        gradeInput = (TextView)findViewById(R.id.input_grade);

        loadData();

        saveBtn = (Button)findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String term = termInput.getText().toString().toUpperCase();
                String grade = gradeInput.getText().toString().toUpperCase();
                Courses c212 = new Courses(term, grade);


                if (course212.isEmpty()|| course212.size()<5) {
                    course212.add(c212);
                }
                course212.set(4,c212);
                saveData();

                courseList.setCourseList(course212);
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
        String json = gson.toJson(course212);
        editor.putString("task list", json);
        editor.apply();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<Courses>>() {}.getType();
        course212 = gson.fromJson(json, type);

        if (course212 == null){
            course212 = new ArrayList<>();
        }

        //load previous radiobutton state
        if (selectedButton == 0){
            taken.setChecked(true);
            if (!course212.isEmpty() && course212.size()>4){
                selectionGroup.takenSelect(termInput, gradeInput);
                termInput.setText(course212.get(4).getTerm());
                gradeInput.setText(course212.get(4).getGrade());

            }
        }
        else if (selectedButton ==1){
            inProgress.setChecked(true);
        }
        else if (selectedButton==2){
            untaken.setChecked(true);
        }

    }


    //RadioGroup onClick
    public void typeClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.radioBtn_taken:
                if (checked)
                    selectionGroup.takenSelect(termInput, gradeInput);
                selectedButton = 0;
                if (!course212.isEmpty() && course212.size()>4){
                    termInput.setText(course212.get(4).getTerm());
                    gradeInput.setText(course212.get(4).getGrade());
                }
                break;
            case R.id.radioBtn_inProgress:
                if (checked)
                    selectionGroup.inProgSelect(termInput, gradeInput);
                selectedButton = 1;
                break;
            case R.id.radioBtn_untaken:
                if (checked)
                    selectionGroup.untakenSelect(termInput, gradeInput);
                selectedButton = 2;
                break;
        }
    }
}
