package com.example.smarttutor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;

import com.example.smarttutor.Subjects.Biology;
import com.example.smarttutor.Subjects.Chemistry;
import com.example.smarttutor.Subjects.Compsci;
import com.example.smarttutor.Subjects.Economy;
import com.example.smarttutor.Subjects.History;
import com.example.smarttutor.Subjects.Mathematics;
import com.example.smarttutor.Subjects.Philosophy;
import com.example.smarttutor.Subjects.Physics;

public class Home extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button button1 = (Button) findViewById(R.id.button);
        Button button4 = (Button) findViewById(R.id.button4); // DONE
        Button button5 = (Button) findViewById(R.id.button5); // DONE
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);
        Button button10 = (Button) findViewById(R.id.button10); // DONE
        Button button11 = (Button) findViewById(R.id.button11);

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
                Intent i = new Intent(Home.this, ProfileST.class);
                Home.this.startActivity(i);
                button5.setVisibility(View.INVISIBLE);
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
    }

}