package com.example.smarttutor.Subjects;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.smarttutor.Articles.Article;
import com.example.smarttutor.Articles.DisplaySingleArticle;
import com.example.smarttutor.Articles.GlobalValuesArticles;
import com.example.smarttutor.Home;
import com.example.smarttutor.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Philosophy extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_philosophy);

        ListView list;
        ArrayList<Article> articleList = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("articles").child("philosophy");

        list = (ListView) findViewById(R.id.liQuestions);
        ArrayList<String> titleList = new ArrayList<>();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    Article article = ds.getValue(Article.class);

                    articleList.add(article);
                    titleList.add(article.title);
                }

                ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(Philosophy.this, android.R.layout.simple_list_item_1, titleList);
                list.setAdapter(itemsAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GlobalValuesArticles.parseValues(articleList.get(i), "Philosophy");
                Intent intent = new Intent(Philosophy.this, DisplaySingleArticle.class);
                Philosophy.this.startActivity(intent);
            }
        });

        ImageView imageView = (ImageView)findViewById(R.id.imageView4);
        imageView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event){
                Intent intent = new Intent(Philosophy.this, Home.class);
                Philosophy.this.startActivity(intent);
                return false;
            }
        });
    }
}
