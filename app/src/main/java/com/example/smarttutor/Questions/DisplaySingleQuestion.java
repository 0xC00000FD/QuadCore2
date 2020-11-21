package com.example.smarttutor.Questions;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.smarttutor.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class DisplaySingleQuestion extends AppCompatActivity {

    TextView tvTitle, tvStatement;
    CheckBox cbAns1, cbAns2, cbAns3, cbAns4;

    private FirebaseAuth auth;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_single_question);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvStatement = (TextView) findViewById(R.id.tvStatement);
        cbAns1 = (CheckBox) findViewById(R.id.cbAns1);
        cbAns2 = (CheckBox) findViewById(R.id.cbAns2);
        cbAns3 = (CheckBox) findViewById(R.id.cbAns3);
        cbAns4 = (CheckBox) findViewById(R.id.cbAns4);

        tvTitle.setText(GlobalValuesQuestions.title);
        tvStatement.setText(GlobalValuesQuestions.statement);

        cbAns1.setText(GlobalValuesQuestions.option1);
        cbAns2.setText(GlobalValuesQuestions.option2);
        cbAns3.setText(GlobalValuesQuestions.option3);
        cbAns4.setText(GlobalValuesQuestions.option4);

        if((cbAns1.isChecked() == GlobalValuesQuestions.ans1) &&
                (cbAns1.isChecked() == GlobalValuesQuestions.ans1) &&
                (cbAns1.isChecked() == GlobalValuesQuestions.ans1) &&
                (cbAns1.isChecked() == GlobalValuesQuestions.ans1)) {
            Log.d("DA", "EZ");
            /// corect
            correctAnswer();
        } else {
            wrongAnswer();
            Log.d("EZ", "Gresit");
        }
    }

    void correctAnswer() {
        FirebaseUser user = auth.getCurrentUser();
        Map<String, Pair< String, Boolean> > mapa = new HashMap<String, Pair< String, Boolean>>();
        Pair<String, Boolean> pair = new Pair<String, Boolean>(GlobalValuesQuestions.currentSubject, GlobalValuesQuestions.status);
        mapa.put(GlobalValuesQuestions.id, pair);
        database.child("users").child(user.getUid()).child("problemsSolved").setValue(mapa);

        database.child("users").child(auth.getUid()).child("problemsAttempted").child(GlobalValuesQuestions.id).removeValue();
    }

    void wrongAnswer() {
        FirebaseUser user = auth.getCurrentUser();
        Map<String, Pair< String, Boolean> > mapa = new HashMap<String, Pair< String, Boolean>>();
        Pair<String, Boolean> pair = new Pair<String, Boolean>(GlobalValuesQuestions.currentSubject, GlobalValuesQuestions.status);
        mapa.put(GlobalValuesQuestions.id, pair);
        database.child("users").child(user.getUid()).child("problemsAttempted").setValue(mapa);
    }
}