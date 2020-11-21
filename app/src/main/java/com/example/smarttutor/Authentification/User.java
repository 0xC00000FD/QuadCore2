package com.example.smarttutor.Authentification;

import android.util.Pair;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class User {
    public String   email;
    public boolean  profileCompletition;
    public boolean  type;
    public String schoolYear;
    public String   telephoneNumber;
    public String   school;
    public Map<String, Pair< String, Boolean> > problemsSolved    = new HashMap<>();
    public Map<String, Pair< String, Boolean> > problemsAttempted = new HashMap<>();
    public Map<String, Pair< String, Boolean> > proposedProblems  = new HashMap<>();

    public User() {

    }

    public User(String email, boolean profileCompletition, boolean type, String schoolYear, String telephoneNumber, String school, Map<String, Pair<String, Boolean>> problemsSolved, Map<String, Pair<String, Boolean>> problemsAttempted, Map<String, Pair<String, Boolean>> proposedProblems) {
        this.email = email;
        this.profileCompletition = profileCompletition;
        this.type = type;
        this.schoolYear = schoolYear;
        this.telephoneNumber = telephoneNumber;
        this.school = school;
        this.problemsSolved = problemsSolved;
        this.problemsAttempted = problemsAttempted;
        this.proposedProblems = proposedProblems;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isProfileCompletition() {
        return profileCompletition;
    }

    public void setProfileCompletition(boolean profileCompletition) {
        this.profileCompletition = profileCompletition;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Map<String, Pair<String, Boolean>> getProblemsSolved() {
        return problemsSolved;
    }

    public void setProblemsSolved(Map<String, Pair<String, Boolean>> problemsSolved) {
        this.problemsSolved = problemsSolved;
    }

    public Map<String, Pair<String, Boolean>> getProblemsAttempted() {
        return problemsAttempted;
    }

    public void setProblemsAttempted(Map<String, Pair<String, Boolean>> problemsAttempted) {
        this.problemsAttempted = problemsAttempted;
    }

    public Map<String, Pair<String, Boolean>> getProposedProblems() {
        return proposedProblems;
    }

    public void setProposedProblems(Map<String, Pair<String, Boolean>> proposedProblems) {
        this.proposedProblems = proposedProblems;
    }
}
