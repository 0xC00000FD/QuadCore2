package com.example.smarttutor;

public class Question {
    int id;
    String title;
    String long_description;

    public Question(int id, String title, String long_description) {
        this.id = id;
        this.title = title;
        this.long_description = long_description;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLong_description() {
        return long_description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLong_description(String long_description) {
        this.long_description = long_description;
    }

    public String idToString() {
        return "#" + id;
    }
}
