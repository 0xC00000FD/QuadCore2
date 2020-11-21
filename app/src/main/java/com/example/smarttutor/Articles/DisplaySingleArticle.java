package com.example.smarttutor.Articles;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smarttutor.R;

public class DisplaySingleArticle extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_single_article);

        TextView title = (TextView) findViewById(R.id.Title);
        TextView paragraph = (TextView) findViewById(R.id.Paragraph);
        TextView pageTitle = (TextView) findViewById(R.id.pageArticles);

        title.setText(GlobalValuesArticles.title);
        paragraph.setText(GlobalValuesArticles.Description);
        pageTitle.setText(GlobalValuesArticles.currentSubject);
    }
}
