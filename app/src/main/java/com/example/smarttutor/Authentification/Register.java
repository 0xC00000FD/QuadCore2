package com.example.smarttutor.Authentification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.smarttutor.Home;
import com.example.smarttutor.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    private  Button createAccount;
    private  EditText Email, Password, ConfirmPassword;
    private FirebaseAuth auth;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ImageButton back = (ImageButton) findViewById(R.id.imageButton);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this, Login.class);
                Register.this.startActivity(i);
            }
        });

        /**
         * All references
         */

        createAccount = (Button)   findViewById(R.id.btnRegisterRegister);

        Email         = (EditText) findViewById(R.id.etRegisterEmail);
        Password      = (EditText) findViewById(R.id.etRegisterPassword);
        ConfirmPassword = (EditText) findViewById(R.id.editTextTextPersonName);

        /**
         * Firebase initialize
         */

        auth     = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

        /**
         * CreateAccount
         */

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email    = Email   .getText().toString();
                String password = Password.getText().toString();
                String confirm_password = ConfirmPassword.getText().toString();

                if (!password.equals(confirm_password))
                {
                    Context context = getApplicationContext();
                    CharSequence text = "Passwords don't match!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else
                {
                    registerAccount(email, password);
                }

            }
        });
    }

    private void registerAccount(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    FirebaseUser user = auth.getCurrentUser();
                    String uid = user.getUid();
                    sendEmailVerification(user);
                    writeNewUser(email, uid);

                    Intent intent = new Intent(Register.this, Login.class);
                    Register.this.startActivity(intent);
                } else {
                    Toast.makeText(Register.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendEmailVerification(FirebaseUser user) {
        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Log.d("55555", "Email sent.");
                }
            }
        });
    }

    private void writeNewUser(String email, String uid) {
        Map<String, Pair<String, Boolean>> mapa = new HashMap<>();
        Pair< String , Boolean > pair = new Pair<String, Boolean>("Nothing", false);
        mapa.put("0", pair);
        User user = new User(email, false, false, " ", " ", " ", mapa, mapa, mapa);
        database.child("users").child(uid).setValue(user);
    }
}