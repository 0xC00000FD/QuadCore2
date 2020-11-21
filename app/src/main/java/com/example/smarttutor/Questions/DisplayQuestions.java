package com.example.smarttutor.Questions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.LinearGradient;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.smarttutor.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DisplayQuestions extends AppCompatActivity {

    private FirebaseAuth auth;
    private DatabaseReference database;

    private ListView list;

    ArrayList<Pair< Question, String >> fullList = new ArrayList< Pair<Question, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_questions);

        database = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();

        list = (ListView) findViewById(R.id.liQuestions);
        /**
         * Depeding on the filter there will be displayed the questions.
         */

        String subject = "Mathematics";
        Boolean unapprovedQuestons = false;

        Log.d("Am ajuns si aci" , "12345");
        FirebaseUser user = auth.getCurrentUser();

        ArrayList< String > titleList  = new ArrayList< String >();
        database.child("questions").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()) {
                    Question question = ds.getValue(Question.class);
                    if(question.open.equals(unapprovedQuestons) && question.subject.equals(subject) && (solved(user, ds.getKey()) == false)) {
                        Pair<Question, String> pair = new Pair<Question, String>(question, ds.getKey());
                        fullList.add(pair);
                        titleList.add(question.title);
                        Log.d("Question", question.title);
                    }
                }
                ArrayAdapter< String > itemsAdapter = new ArrayAdapter< String>(DisplayQuestions.this, android.R.layout.simple_list_item_1, titleList);
                list.setAdapter(itemsAdapter);
                Log.d("AM IESIT CSF", "SUNT NUB");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                ///Aici e ceva in caz ce da eroare
            }
        });
        Log.d("Am ajuns si aci" , "1234");

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                parseValues(fullList.get(i));
                Intent intent = new Intent(DisplayQuestions.this, DisplaySingleQuestion.class);
                DisplayQuestions.this.startActivity(intent);
                Log.d("ASTA ESTE", "AM AJUNS AICI");
            }
        });
    }

    void parseValues(Pair<Question, String > item) {
        GlobalValuesQuestions.title     = item.first.title;
        GlobalValuesQuestions.statement = item.first.problemStatement;

        GlobalValuesQuestions.option1   = item.first.Option1;
        GlobalValuesQuestions.option2   = item.first.Option2;
        GlobalValuesQuestions.option3   = item.first.Option3;
        GlobalValuesQuestions.option4   = item.first.Option4;

        GlobalValuesQuestions.ans1      = item.first.Answer1;
        GlobalValuesQuestions.ans2      = item.first.Answer2;
        GlobalValuesQuestions.ans3      = item.first.Answer3;
        GlobalValuesQuestions.ans4      = item.first.Answer4;

        GlobalValuesQuestions.currentSubject = item.first.subject;

        GlobalValuesQuestions.status = item.first.open;
        GlobalValuesQuestions.id     = item.second;
    }

    public Boolean ok = false;

    private Boolean solved(FirebaseUser user, String key) {
        ok = false;
        database.child("users").child(user.getUid()).child("problemsSolved").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()) {
                    String newKey = ds.getKey();
                    if(newKey.equals(key)) {
                        ok = true;
                        return;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return ok;
    }
}