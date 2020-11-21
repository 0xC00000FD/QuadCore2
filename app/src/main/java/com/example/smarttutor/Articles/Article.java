package com.example.smarttutor.Articles;

import com.example.smarttutor.Questions.GlobalValuesQuestions;
import com.example.smarttutor.Questions.Question;

public class Article{
    int id;
    public String title;
    public String long_description;
    public String short_description;

    public Article(){

    }

    public Article(int id, String title, String short_description, String long_description) {
        this.id = id;
        this.title = title;
        this.long_description = long_description;
        this.short_description = short_description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String idToString() {
        return String.valueOf(id);
    }
}
