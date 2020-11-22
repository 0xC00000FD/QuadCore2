package com.example.smarttutor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smarttutor.Articles.Article;
import com.example.smarttutor.Articles.DisplaySingleArticle;
import com.example.smarttutor.Articles.GlobalValuesArticles;
import com.example.smarttutor.Authentification.Login;
import com.example.smarttutor.Authentification.Make_Profile;
import com.example.smarttutor.Questions.AddQuestions;
import com.example.smarttutor.Questions.DisplayQuestions;
import com.example.smarttutor.Questions.GlobalValuesQuestions;
import com.example.smarttutor.Subjects.Biology;
import com.example.smarttutor.Subjects.Chemistry;
import com.example.smarttutor.Subjects.Compsci;
import com.example.smarttutor.Subjects.Economy;
import com.example.smarttutor.Subjects.History;
import com.example.smarttutor.Subjects.Mathematics;
import com.example.smarttutor.Subjects.Philosophy;
import com.example.smarttutor.Subjects.Physics;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class Home extends AppCompatActivity
{
    public static boolean nightmode = false;

    FirebaseAuth auth;

    private ImageView view;
    private EditText searchInput;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        Button button1 = (Button) findViewById(R.id.button);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);
        Button button10 = (Button) findViewById(R.id.button10);
        Button button11 = (Button) findViewById(R.id.button11);
        Button button69 = (Button) findViewById(R.id.button69);
        Button button420 = (Button) findViewById(R.id.button420);
        TextView search = (TextView) findViewById(R.id.textView);
        TextView pr_name = (TextView) findViewById(R.id.textView2);
        ImageView imageView = (ImageView) findViewById(R.id.imageView16);

        view = (ImageView) findViewById(R.id.ivSearchButton);
        searchInput = (EditText) findViewById(R.id.etSearch);

        auth = FirebaseAuth.getInstance();


        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(Home.this, History.class);
                Home.this.startActivity(i);
            }
        });

        button4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(Home.this, Compsci.class);
                Home.this.startActivity(i);
            }
        });

        button5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FirebaseUser user = auth.getCurrentUser();
                if(user != null) {
                    FirebaseAuth.getInstance().signOut();
                }
            }
        });

        button6.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(Home.this, Economy.class);
                Home.this.startActivity(i);
            }
        });

        button7.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(Home.this, Chemistry.class);
                Home.this.startActivity(i);
            }
        });

        button8.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(Home.this, Physics.class);
                Home.this.startActivity(i);
            }
        });

        button9.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(Home.this, Biology.class);
                Home.this.startActivity(i);
            }
        });

        button10.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(Home.this, Mathematics.class);
                Home.this.startActivity(i);
            }
        });

        button11.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(Home.this, Philosophy.class);
                Home.this.startActivity(i);
            }
        });

        button69.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent i = new Intent(Home.this, DisplayQuestions.class);
                Home.this.startActivity(i);
            }
        });

        button420.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                Intent i = new Intent(Home.this, AddQuestions.class);
                Home.this.startActivity(i);
            }
        });

        imageView.setOnTouchListener(new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event){
                Intent intent = new Intent(Home.this, Make_Profile.class);
                Home.this.startActivity(intent);
                return false;
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = searchInput.getText().toString().trim();
                find(input);
            }
        });
    }

    public static Boolean ok = false;

    public void find(String search) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        GlobalValuesQuestions.okSearch = false;

        database.child("articles").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot subjects : snapshot.getChildren()) {
                    for(DataSnapshot ds: subjects.getChildren()) {
                        Article article = ds.getValue(Article.class);
                        Log.d("Titlu", article.title);
                        //Log.d("Short", article.short_description);
                        if(searchAppereance(search, article.title) == true) {
                            /**
                             * Display article
                             */

                            GlobalValuesQuestions.okSearch = true;
                            Log.d("PLM", String.valueOf(GlobalValuesQuestions.okSearch));

                        }
                        if(searchAppereance(search, article.long_description) == true) {
                            /**
                             * Display article
                             */

                            GlobalValuesQuestions.okSearch = true;
                            Log.d("PLM", String.valueOf(GlobalValuesQuestions.okSearch));
                        }

                        if(GlobalValuesQuestions.okSearch == false) {
                            Toast.makeText(Home.this, "Not found",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            GlobalValuesArticles.title = article.title;
                            GlobalValuesArticles.Description = article.long_description;
                            GlobalValuesArticles.currentSubject = subjects.getKey();
                            Intent intent = new Intent(Home.this, DisplaySingleArticle.class);
                            Home.this.startActivity(intent);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private static Boolean searchAppereance(String input, String location) {
        return (location.toLowerCase().indexOf(input.toLowerCase()) != -1);
    }
}