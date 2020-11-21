package com.example.smarttutor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Make_Profile extends AppCompatActivity
{
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_profile);

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
        _9_.setVisibility(0);
        _10_.setVisibility(0);
        _11_.setVisibility(0);
        _12_.setVisibility(0);
        text.setVisibility(0);
        phone.setVisibility(0);
        school.setVisibility(0);
        submit.setVisibility(0);

        if (student.isChecked())
        {
            teacher.setChecked(false);
            _9_.setVisibility(1);
            _10_.setVisibility(1);
            _11_.setVisibility(1);
            _12_.setVisibility(1);
            text.setVisibility(1);
            school.setVisibility(1);

            if (_9_.isChecked())
            {
                _10_.setChecked(false);
                _11_.setChecked(false);
                _12_.setChecked(false);
            }
            else if (_10_.isChecked())
            {
                _9_.setChecked(false);
                _11_.setChecked(false);
                _12_.setChecked(false);
            }
            else if (_11_.isChecked())
            {
                _9_.setChecked(false);
                _10_.setChecked(false);
                _12_.setChecked(false);
            }
            else if (_12_.isChecked())
            {
                _9_.setChecked(false);
                _10_.setChecked(false);
                _11_.setChecked(false);
            }

            submit.setVisibility(1);
            submit.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent i = new Intent(Make_Profile.this, Back_Home.class);
                    Make_Profile.this.startActivity(i);
                }
            });
        }

        if (teacher.isChecked())
        {
            student.setChecked(false);
            phone.setVisibility(1);
            school.setVisibility(1);
            submit.setVisibility(1);
            submit.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent i = new Intent(Make_Profile.this, Back_Home.class);
                    Make_Profile.this.startActivity(i);
                }
            });
        }
    }
}
