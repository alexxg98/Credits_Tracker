package com.example.credits_tracker;

import android.widget.TextView;

public class selectionGroup {
    public static final String NOT_AVAIL = "NA";

    public static void takenSelect(TextView term, TextView grade) {
        term.setEnabled(true);
        term.setFocusable(true);
        grade.setEnabled(true);
        grade.setFocusable(true);
    }

    public static void inProgSelect(TextView term, TextView grade) {
        term.setEnabled(false);
        grade.setEnabled(false);
        term.setText("In Progress");
        grade.setText(NOT_AVAIL);
    }

    public static void untakenSelect(TextView term, TextView grade) {
        term.setEnabled(false);
        grade.setEnabled(false);
        term.setText(NOT_AVAIL);
        grade.setText(NOT_AVAIL);
    }
}

