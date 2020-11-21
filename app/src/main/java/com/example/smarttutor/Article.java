package com.example.smarttutor;

public class Article extends Question{
    private String short_description;

    public Article(int id, String title, String short_description, String long_description) {
        super(id, title, long_description);
        this.short_description = short_description;
    }

    public String getShort_description() {
        return short_description;
    }

    public void setShort_Description(String short_description) {
        this.short_description = short_description;
    }

}
