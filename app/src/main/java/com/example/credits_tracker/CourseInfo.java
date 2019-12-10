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
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CourseInfo extends AppCompatActivity {

    private RadioButton taken, inProgress, untaken;
    private RadioGroup statusRadioGroup;
    private ImageButton closeBtn;
    private Button saveBtn;
    private TextView termInput, gradeInput;
    ArrayList<Courses> courseList;

    //default radiobutton checked: untaken
    public static int selectedButton = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_popup);
        taken = (RadioButton)findViewById(R.id.radioBtn_taken);
        inProgress = (RadioButton)findViewById(R.id.radioBtn_inProgress);
        untaken = (RadioButton)findViewById(R.id.radioBtn_untaken);
        statusRadioGroup = (RadioGroup)findViewById(R.id.radioGroup_status);
        termInput = (TextView)findViewById(R.id.input_term);
        gradeInput = (TextView)findViewById(R.id.input_grade);

        loadData();

        //load previous radiobutton state
        if (selectedButton == 0){
            taken.setChecked(true);
        }
        else if (selectedButton ==1){
            inProgress.setChecked(true);
        }
        else if (selectedButton==2){
            untaken.setChecked(true);
        }

        statusRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton)group.findViewById(checkedId);
                if (null != rb && checkedId > -1){
                    Toast.makeText(getApplicationContext(), rb.getText(), Toast.LENGTH_SHORT).show();

                }
            }
        });
        
        saveBtn = (Button)findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String term = termInput.getText().toString().toUpperCase();
                String grade = gradeInput.getText().toString().toUpperCase();
//                int type = statusRadioGroup.getCheckedRadioButtonId();
//                System.out.println(type);
                Courses c103 = new Courses(term, grade);
                courseList.set(0,c103);
                String test = courseList.get(0).toString();
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
            case R.id.radioBtn_taken:
                if (checked)
                    test = "Taken Selected";
                    termInput.setEnabled(true);
                    termInput.setFocusable(true);
                    gradeInput.setEnabled(true);
                    gradeInput.setFocusable(true);
                    selectedButton = 0;
                    if (!courseList.isEmpty()){
                        termInput.setText(courseList.get(0).getTerm());
                        gradeInput.setText(courseList.get(0).getGrade());
                    }
                    break;
            case R.id.radioBtn_inProgress:
                if (checked)
                    test = "In-Progress Selected";
                    termInput.setEnabled(false);
                    termInput.setFocusable(false);
                    gradeInput.setEnabled(false);
                    gradeInput.setFocusable(false);
                    selectedButton = 1;
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
                    selectedButton = 2;
                    termInput.setText("NA");
                    gradeInput.setText("NA");
                    break;
        }
        Toast.makeText(getApplicationContext(),test, Toast.LENGTH_SHORT).show();
    }


}
