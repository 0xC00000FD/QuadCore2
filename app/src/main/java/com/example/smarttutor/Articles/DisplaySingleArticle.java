package com.example.smarttutor.Articles;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smarttutor.Home;
import com.example.smarttutor.R;
import com.example.smarttutor.Subjects.Compsci;

import java.time.LocalDate;

public class DisplaySingleArticle extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_single_article);

        TextView title = (TextView) findViewById(R.id.Title);
        TextView paragraph = (TextView) findViewById(R.id.Paragraph);
        TextView pageTitle = (TextView) findViewById(R.id.pageArticles);

        //Log.d("Title", GlobalValuesArticles.title);
        //Log.d("Description", GlobalValuesArticles.Description);
        //Log.d("Subject", GlobalValuesArticles.currentSubject);

        title.setText(GlobalValuesArticles.title);
        paragraph.setText(GlobalValuesArticles.Description);
        pageTitle.setText(GlobalValuesArticles.currentSubject);

        ImageView imageView = (ImageView)findViewById(R.id.imageView3);
        imageView.setOnTouchListener(new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event){
                Intent intent = new Intent(DisplaySingleArticle.this, Home.class);
                DisplaySingleArticle.this.startActivity(intent);
                return false;
            }
        });
    }
}
