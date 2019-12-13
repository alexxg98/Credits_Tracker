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

public class Info104 extends AppCompatActivity {
    private RadioButton taken, inProgress, untaken;
    private RadioGroup statusRadioGroup;
    private ImageButton closeBtn;
    private Button saveBtn;
    private TextView termInput, gradeInput;

    CourseList courseList;
    ArrayList<Courses> course104;

    //default radiobutton checked: untaken
    public static int selectedButton = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info104);

        courseList = new CourseList();
        course104 = courseList.getCourseList();
//        System.out.println(course104.get(0).toString());
//        System.out.println(course104.get(1).toString());

        taken = (RadioButton)findViewById(R.id.radioBtn_taken);
        inProgress = (RadioButton)findViewById(R.id.radioBtn_inProgress);
        untaken = (RadioButton)findViewById(R.id.radioBtn_untaken);
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
                Courses c104 = new Courses(term, grade);

                if (course104.isEmpty() || course104.size()<2) {
                    course104.add(c104);
                }
                course104.set(1,c104);
//                String test = course104.get(1).toString();
//                Toast.makeText(getApplicationContext(),test, Toast.LENGTH_SHORT).show();
                saveData();

                courseList.setCourseList(course104);
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
        String json = gson.toJson(course104);
        editor.putString("task list", json);
        editor.apply();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<Courses>>() {}.getType();
        course104 = gson.fromJson(json, type);

        if (course104 == null){
            course104 = new ArrayList<>();
        }

        //load previous radiobutton state
        if (selectedButton == 0){
            taken.setChecked(true);
            if (!course104.isEmpty()){
                selectionGroup.takenSelect(termInput, gradeInput);
                termInput.setText(course104.get(1).getTerm());
                gradeInput.setText(course104.get(1).getGrade());

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
                if (!course104.isEmpty()){
                    termInput.setText(course104.get(1).getTerm());
                    gradeInput.setText(course104.get(1).getGrade());
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
