package com.example.credits_tracker;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Courses implements Serializable {
    private String term, grade;

    public Courses(String term, String grade) {
        this.term = term;
        this.grade = grade;

    }

    public String getTerm() {
        return term;
    }
    public void setTerm(String term) {
        this.term = term;
    }

    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }

    @NonNull
    @Override
    public String toString() {
        return term + " " + grade;
    }
}
