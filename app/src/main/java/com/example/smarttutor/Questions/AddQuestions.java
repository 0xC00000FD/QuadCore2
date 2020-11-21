package com.example.smarttutor.Questions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.smarttutor.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class AddQuestions extends AppCompatActivity {

    private Button btnSubmit;
    private EditText etProblemName;
    private EditText etProblemStatement;
    private EditText etOption1, etOption2, etOption3, etOption4;
    private CheckBox cbOption1, cbOption2, cbOption3, cbOption4;

    private FirebaseAuth auth;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_questions);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        etProblemName = (EditText) findViewById(R.id.etProblemName);
        etProblemStatement = (EditText) findViewById(R.id.etProblemStatement);

        etOption1 = (EditText) findViewById(R.id.etOption1);
        etOption2 = (EditText) findViewById(R.id.etOption2);
        etOption3 = (EditText) findViewById(R.id.etOption3);
        etOption4 = (EditText) findViewById(R.id.etOption4);

        cbOption1 = (CheckBox) findViewById(R.id.cbOption1);
        cbOption2 = (CheckBox) findViewById(R.id.cbOption2);
        cbOption3 = (CheckBox) findViewById(R.id.cbOption3);
        cbOption4 = (CheckBox) findViewById(R.id.cbOption4);

        auth     = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name, statement, op1, op2, op3, op4;
                Boolean ans1, ans2, ans3, ans4;

                name = etProblemName.getText().toString().trim();
                statement = etProblemStatement.getText().toString().trim();

                op1 = etOption1.getText().toString().trim();
                op2 = etOption2.getText().toString().trim();
                op3 = etOption3.getText().toString().trim();
                op4 = etOption4.getText().toString().trim();

                ans1 = (cbOption1.isChecked()) ? true : false;
                ans2 = (cbOption2.isChecked()) ? true : false;
                ans3 = (cbOption3.isChecked()) ? true : false;
                ans4 = (cbOption4.isChecked()) ? true : false;

                Question question = new Question(name, statement, op1, ans1, op2, ans2, op3, ans3, op4, ans4);
                createQuestion(question);
            }
        });


    }

    private void createQuestion(Question question) {
        String id = getValue();
        database.child("questions").child(id).setValue(question);

        FirebaseUser user = auth.getCurrentUser();
        Map< String, Object > updates = new HashMap<>();
        updates.put(id, false);
        database.child("users").child(user.getUid()).child("proposedProblems").updateChildren(updates);
    }
    private String getValue() {
        String ans = new String();

        FirebaseUser user = auth.getCurrentUser();

        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();

        ans = user.getUid() + " _ " + ts;

        return ans;
    }
}