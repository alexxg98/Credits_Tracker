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

public class Info211 extends AppCompatActivity {

    private RadioButton taken, inProgress, untaken;
    private RadioGroup statusRadioGroup;
    private ImageButton closeBtn;
    private Button saveBtn;
    private TextView termInput, gradeInput;

    CourseList courseList;
    ArrayList<Courses> course211;

    //default radiobutton checked: untaken
    public static int selectedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info211);

        courseList = new CourseList();
        course211 = courseList.getCourseList();
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
                Courses c211 = new Courses(term, grade);


                if (course211.isEmpty()|| course211.size()<4) {
                    course211.add(c211);
                }
                course211.set(3,c211);
                saveData();

                courseList.setCourseList(course211);
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
        String json = gson.toJson(course211);
        editor.putString("task list", json);
        editor.apply();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<Courses>>() {}.getType();
        course211 = gson.fromJson(json, type);

        if (course211 == null){
            course211 = new ArrayList<>();
        }

        //load previous radiobutton state
        if (selectedButton == 0){
            taken.setChecked(true);
            if (!course211.isEmpty() && course211.size()>3){
                selectionGroup.takenSelect(termInput, gradeInput);
                termInput.setText(course211.get(3).getTerm());
                gradeInput.setText(course211.get(3).getGrade());

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
                if (!course211.isEmpty() && course211.size()>3){
                    termInput.setText(course211.get(3).getTerm());
                    gradeInput.setText(course211.get(3).getGrade());
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
