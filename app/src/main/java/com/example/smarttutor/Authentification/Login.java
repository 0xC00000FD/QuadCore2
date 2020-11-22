package com.example.smarttutor.Authentification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smarttutor.Home;
import com.example.smarttutor.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    private Button login;
    private TextView register, resetPassword, withOutAccount;
    private EditText Email, Password;

    private FirebaseAuth auth;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        /**
         * References between UI and code
         */

        login    = (Button) findViewById(R.id.btnSignIn);
        register = (TextView) findViewById(R.id.tvLoginRegister);
        Email    = (EditText) findViewById(R.id.etLoginEmail);
        Password = (EditText) findViewById(R.id.etLoginPassword);
        resetPassword = (TextView) findViewById(R.id.tvForgotPassword);
        withOutAccount= (TextView) findViewById(R.id.tvGoWithoutAccount);


        /**
         * Firebase initialize
         */

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

        /**
         * If the user does not have an account, he will click the button and go to the register screen
         */

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                Login.this.startActivity(intent);
            }
        });

        /**
         * Usernlogin method
         */

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email    = Email.getText().toString();
                String password = Password.getText().toString();
                signIn(email, password);
            }
        });


        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email    = Email.getText().toString();
                sendResetPasswordEmail(email);
            }
        });

        withOutAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Home.class);
                Login.this.startActivity(intent);
            }
        });
    }

    /**
     * The
     * @param email
     * @param passoword
     */

    private void signIn(String email, String passoword) {
        auth.signInWithEmailAndPassword(email, passoword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    FirebaseUser user = auth.getCurrentUser();
                    boolean emailVerified = user.isEmailVerified();


                    if(emailVerified == false) {
                        Toast.makeText(Login.this, "You have not verified your email",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }


                    database.child("users").child(user.getUid()).
                            addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    Boolean data = snapshot.child("profileCompletition").getValue().equals(false);
                                    if(data == true) {
                                        Intent intent = new Intent(Login.this, Make_Profile.class);
                                        Login.this.startActivity(intent);
                                        return;
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                    Intent intent = new Intent(Login.this, Home.class);
                    Login.this.startActivity(intent);
                } else {
                    Toast.makeText(Login.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     *
     */

    private void sendResetPasswordEmail(String email) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Log.d("EZ", "Email sent");
                }
            }
        });
    }
}