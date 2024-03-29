package com.example.smarttutor.Questions;

public class Question {
    public String  title;
    public String  problemStatement;

    public String  Option1;
    public Boolean Answer1;

    public String   Option2;
    public Boolean  Answer2;

    public String   Option3;
    public Boolean  Answer3;

    public String   Option4;
    public Boolean  Answer4;

    public String subject;
    public Boolean open;

    public Question() {

    }

    public Question(String title, String problemStatement, String option1, Boolean answer1, String option2, Boolean answer2, String option3, Boolean answer3, String option4, Boolean answer4, String subject, Boolean open) {
        this.title = title;
        this.problemStatement = problemStatement;
        this.Option1 = option1;
        this.Answer1 = answer1;
        this.Option2 = option2;
        this.Answer2 = answer2;
        this.Option3 = option3;
        this.Answer3 = answer3;
        this.Option4 = option4;
        this.Answer4 = answer4;
        this.subject = subject;
        this.open = open;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }
}
