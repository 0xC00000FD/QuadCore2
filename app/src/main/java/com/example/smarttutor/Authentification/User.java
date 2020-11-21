package com.example.smarttutor.Authentification;

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
    public Map<String, Boolean> problemsSolved_Mathematics   = new HashMap<>();
    public Map<String, Boolean> problemsAttepted_Mathematics = new HashMap<>();
    public Map<String, Boolean> problemsSolved_Informatics   = new HashMap<>();
    public Map<String, Boolean> problemsAttepted_Informatics = new HashMap<>();
    public Map<String, Boolean> proposedProblems = new HashMap<>();

    public User() {

    }



    public User(String email, boolean profileCompletition, boolean type, String schoolYear, String telephoneNumber, String school, Map<String, Boolean> problemsSolved_Mathematics, Map<String, Boolean> problemsAttepted_Mathematics, Map<String, Boolean> problemsSolved_Informatics, Map<String, Boolean> problemsAttepted_Informatics, Map<String, Boolean>proposedProblems) {
        this.email = email;
        this.type = type;
        this.profileCompletition = profileCompletition;
        this.telephoneNumber = telephoneNumber;
        this.schoolYear = schoolYear;
        this.school = school;
        this.problemsSolved_Mathematics = problemsSolved_Mathematics;
        this.problemsAttepted_Mathematics = problemsAttepted_Mathematics;
        this.problemsSolved_Informatics = problemsSolved_Informatics;
        this.problemsAttepted_Informatics = problemsAttepted_Informatics;
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

    public Map<String, Boolean> getProblemsSolved_Mathematics() {
        return problemsSolved_Mathematics;
    }

    public void setProblemsSolved_Mathematics(Map<String, Boolean> problemsSolved_Mathematics) {
        this.problemsSolved_Mathematics = problemsSolved_Mathematics;
    }

    public Map<String, Boolean> getProblemsAttepted_Mathematics() {
        return problemsAttepted_Mathematics;
    }

    public void setProblemsAttepted_Mathematics(Map<String, Boolean> problemsAttepted_Mathematics) {
        this.problemsAttepted_Mathematics = problemsAttepted_Mathematics;
    }

    public Map<String, Boolean> getProblemsSolved_Informatics() {
        return problemsSolved_Informatics;
    }

    public void setProblemsSolved_Informatics(Map<String, Boolean> problemsSolved_Informatics) {
        this.problemsSolved_Informatics = problemsSolved_Informatics;
    }

    public Map<String, Boolean> getProblemsAttepted_Informatics() {
        return problemsAttepted_Informatics;
    }

    public void setProblemsAttepted_Informatics(Map<String, Boolean> problemsAttepted_Informatics) {
        this.problemsAttepted_Informatics = problemsAttepted_Informatics;
    }

    public Map<String, Boolean> getProposedProblems() {
        return proposedProblems;
    }

    public void setProposedProblems(Map<String, Boolean> proposedProblems) {
        this.proposedProblems = proposedProblems;
    }
}
