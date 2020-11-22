package com.example.smarttutor.Authentification;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smarttutor.Home;
import com.example.smarttutor.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Make_Profile extends AppCompatActivity
{
    private FirebaseAuth auth;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_profile);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

        CheckBox student = (CheckBox) findViewById(R.id.checkBox);
        CheckBox teacher = (CheckBox) findViewById(R.id.checkBox2);
        RadioButton _9_ = (RadioButton) findViewById(R.id.radioButton4);
        RadioButton _10_ = (RadioButton) findViewById(R.id.radioButton);
        RadioButton _11_ = (RadioButton) findViewById(R.id.radioButton2);
        RadioButton _12_ = (RadioButton) findViewById(R.id.radioButton3);
        TextView text = (TextView) findViewById(R.id.textView4);
        EditText phone = (EditText) findViewById(R.id.editTextTextPersonName6);
        EditText school = (EditText) findViewById(R.id.editTextTextPersonName4);
        Button submit = (Button) findViewById(R.id.button2);
        _9_     .setVisibility(View.INVISIBLE);
        _10_    .setVisibility(View.INVISIBLE);
        _11_    .setVisibility(View.INVISIBLE);
        _12_    .setVisibility(View.INVISIBLE);
        text    .setVisibility(View.INVISIBLE);
        phone   .setVisibility(View.INVISIBLE);
        school  .setVisibility(View.INVISIBLE);
        submit  .setVisibility(View.INVISIBLE);

        student.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                teacher .setChecked(false);
                _9_     .setVisibility(View.VISIBLE);
                _10_    .setVisibility(View.VISIBLE);
                _11_    .setVisibility(View.VISIBLE);
                _12_    .setVisibility(View.VISIBLE);
                text    .setVisibility(View.VISIBLE);
                school  .setVisibility(View.VISIBLE);
                phone   .setVisibility(View.INVISIBLE);
                submit.setVisibility(View.VISIBLE);
            }
        });
        teacher.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                student .setChecked(false);
                phone   .setVisibility(View.VISIBLE);
                school  .setVisibility(View.VISIBLE);
                _9_     .setVisibility(View.INVISIBLE);
                _10_    .setVisibility(View.INVISIBLE);
                _11_    .setVisibility(View.INVISIBLE);
                _12_    .setVisibility(View.INVISIBLE);
                text    .setVisibility(View.INVISIBLE);
                submit.setVisibility(View.VISIBLE);
            }
        });

        _9_.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                _10_.setChecked(false);
                _11_.setChecked(false);
                _12_.setChecked(false);
                submit.setVisibility(View.VISIBLE);
            }
        });
        _10_.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                _9_.setChecked(false);
                _11_.setChecked(false);
                _12_.setChecked(false);
                submit.setVisibility(View.VISIBLE);
            }
        });
        _11_.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                _10_.setChecked(false);
                _9_.setChecked(false);
                _12_.setChecked(false);
                submit.setVisibility(View.VISIBLE);
            }
        });
        _12_.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                _10_.setChecked(false);
                _11_.setChecked(false);
                _9_.setChecked(false);
                submit.setVisibility(View.VISIBLE);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String School, schoolYear = "8", telephoneNumber;
                Boolean type;

                School = school.getText().toString().trim();
                if(_9_.isChecked()) schoolYear = "9";
                if(_10_.isChecked()) schoolYear = "10";
                if(_11_.isChecked()) schoolYear = "11";
                if(_12_.isChecked()) schoolYear = "12";

                telephoneNumber = phone.getText().toString().trim();

                FirebaseUser user = auth.getCurrentUser();
                if(teacher.isChecked()) {
                    database.child("users").child(user.getUid()).child("profileCompletition").setValue(true);
                    database.child("users").child(user.getUid()).child("school").setValue(School);
                    database.child("users").child(user.getUid()).child("telephoneNumber").setValue(telephoneNumber);
                    database.child("users").child(user.getUid()).child("type").setValue(true);
                } else {
                    database.child("users").child(user.getUid()).child("profileCompletition").setValue(true);
                    database.child("users").child(user.getUid()).child("schoolYear").setValue(schoolYear);
                    database.child("users").child(user.getUid()).child("school").setValue(School);
                    database.child("users").child(user.getUid()).child("type").setValue(true);
                }

                Intent i = new Intent(Make_Profile.this, Home.class);
                Make_Profile.this.startActivity(i);
            }
        });
    }
}
