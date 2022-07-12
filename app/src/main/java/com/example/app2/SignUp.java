package com.example.app2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.app2.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    MaterialEditText btnname, btnpassword, btnphone, btnmail;
    Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnname = (MaterialEditText) findViewById(R.id.edtname);
        btnpassword = (MaterialEditText) findViewById(R.id.edtpassword);
        btnphone = (MaterialEditText) findViewById(R.id.edtphone);
        btnmail = (MaterialEditText) findViewById(R.id.edtmail);
        signup = (Button) findViewById(R.id.signUp);

        final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%*^]).{8,15})"; //1 digit from 0-9, 1 lowercase char, 1 uppercase char, 1 special symbol, length min = 8, max = 15

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference customer = database.getReference("User");

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                customer.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(btnphone.getText().toString()).exists()) {
                            Toast.makeText(SignUp.this, "This phone number is registered.", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            if(!isEmailValid((btnmail.getText().toString())))
                            {
                                Toast.makeText(SignUp.this, "Invalid email address.", Toast.LENGTH_SHORT).show();
                            }
                            else if(!isPasswordValid(btnpassword.getText().toString())) {
                                Toast.makeText(SignUp.this, "Invalid password.\nPassword should contain:\n1 digit, 1 lowercase letter, 1 uppercase letter, 1 special symbol\nMinimum length = 8 characters", Toast.LENGTH_LONG).show();
                            }
                            else {
                                User user = new User(btnname.getText().toString(), btnpassword.getText().toString(), btnmail.getText().toString());
                                customer.child(btnphone.getText().toString()).setValue(user);
                                Toast.makeText(SignUp.this, "You've successfully signed up!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                    public boolean isPasswordValid (final String password){
                        Pattern pattern;
                        Matcher matcher;
                        pattern = Pattern.compile(PASSWORD_PATTERN);
                        matcher = pattern.matcher(password);
                        return matcher.matches();

                    }

                    boolean isEmailValid (CharSequence email)
                    {
                        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
                    }

                });
            }
        });
    }
}