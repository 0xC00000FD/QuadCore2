package com.example.smarttutor.Subjects;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.smarttutor.Questions.DisplayQuestions;
import com.example.smarttutor.Questions.DisplaySingleQuestion;
import com.example.smarttutor.Questions.GlobalValuesQuestions;
import com.example.smarttutor.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class Compsci extends AppCompatActivity {

    private ListView list;
    DatabaseReference reference;
    public ArrayList<Article> articleList = new ArrayList< Article >();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compsci);
        reference = FirebaseDatabase.getInstance().getReference();

        list = (ListView) findViewById(R.id.liQuestions);
        ArrayList<String> titleList = new ArrayList<String>();

        reference.child("articles").child("compsci").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("ACI", "AM ajuns si aci");
                for(DataSnapshot ds : snapshot.getChildren()){
                    Article article = ds.getValue(Article.class);

                    articleList.add(article);
                    titleList.add(article.title);
                    //Log.d("1234", article.short_description);
                    Log.d("AICI", "Am ajuns aici");
                }
                ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(Compsci.this, android.R.layout.simple_list_item_1, titleList);
                list.setAdapter(itemsAdapter);

                Log.d("Lista0", String.valueOf(articleList.size()));

                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Log.d("Lista1", String.valueOf(articleList.size()));
                        parseItems(i);
                        //Log.d("SUBJECT",GlobalValuesArticles.currentSubject);
                        //Log.d("Title", GlobalValuesArticles.title);
                        Intent intent = new Intent(Compsci.this, DisplaySingleArticle.class);
                        Compsci.this.startActivity(intent);
                        Log.d("Apasat", "DA");
                    }
                });
            }

            void parseItems(int i) {
                Log.d("Lista2", String.valueOf(articleList.size()));
                Log.d("FMM", String.valueOf(articleList.get(0).long_description));
                Log.d("Value", String.valueOf(i));
                GlobalValuesArticles.title     = articleList.get(i).title;
                Log.d("NewValue", GlobalValuesArticles.title);
                GlobalValuesArticles.Description = articleList.get(i).long_description;
                GlobalValuesArticles.currentSubject = "Compsci";
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Log.d("Lista3", String.valueOf(articleList.size()));
        //Log.d("Lista", articleList.get(0).title);



        ImageView imageView = (ImageView)findViewById(R.id.imageView4);
        imageView.setOnTouchListener(new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event){
                Intent intent = new Intent(Compsci.this, Home.class);
                Compsci.this.startActivity(intent);
                return false;
            }
        });

    }
}
