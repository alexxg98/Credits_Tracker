package com.example.credits_tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CourseInfo extends AppCompatActivity {

    private RadioButton taken, inProgress, untaken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_popup);
        taken = (RadioButton)findViewById(R.id.radioBtn_Taken);
        inProgress = (RadioButton)findViewById(R.id.radioBtn_inProgress);
        untaken = (RadioButton)findViewById(R.id.radioBtn_untaken);
    }
    public void typeClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();
        String test = "";
        switch (view.getId()){
            case R.id.radioBtn_Taken:
                if (checked)
                    test = "Taken Selected";
                    break;
            case R.id.radioBtn_inProgress:
                if (checked)
                    test = "In-Progess Selected";
                    break;
            case R.id.radioBtn_untaken:
                if (checked)
                    test = "Untaken Selected";
                    break;
        }
        Toast.makeText(getApplicationContext(),test, Toast.LENGTH_SHORT).show();
    }
}
