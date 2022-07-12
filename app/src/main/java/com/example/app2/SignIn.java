package com.example.app2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app2.Common.Common;
import com.example.app2.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {

    EditText edtphone, edtpassword;
    Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        edtphone = (EditText) findViewById(R.id.edtphone);
        edtpassword = (EditText) findViewById(R.id.edtpassword);
        signIn = (Button) findViewById(R.id.signIn);
        Toast.makeText(getApplicationContext(),"here",Toast.LENGTH_SHORT);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference customer = database.getReference("User");

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"here",Toast.LENGTH_SHORT);

//                ProgressDialog mDialog = new ProgressDialog(SignIn.this);
//                mDialog.sentMessage("Please wait...");
                    customer.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(edtphone.getText().toString()).exists()) {
                            User user = dataSnapshot.child(edtphone.getText().toString()).getValue(User.class);
                            user.setPhone(edtphone.getText().toString());
                            if (user.getPassword().equals(edtpassword.getText().toString())) {
                                Intent homeIntent = new Intent(SignIn.this, Home.class);
                                Common.currentUser = user;
                                startActivity(homeIntent);
                                finish();
                            } else {
                                Toast.makeText(SignIn.this, "Incorrect password.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(SignIn.this, "You're not registered yet. Sign up!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
