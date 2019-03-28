package com.example.firebase_recap;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

public class MainActivity extends AppCompatActivity {

    //constant
    private static final String TAG = "MainActivity";

    //member variable
    private EditText editText;
    String mText;
    DatabaseReference myRef;
    int number = 1;

    // Logger


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Logger.addLogAdapter(new AndroidLogAdapter());

        TextView text = findViewById(R.id.textView);
        Button saveBtn = findViewById(R.id.button);
        Button readBtn = findViewById(R.id.button2);
        Button deleteBtn = findViewById(R.id.button3);
        Button registration = findViewById(R.id.registration);
        editText = findViewById(R.id.editText);

        myRef = FirebaseDatabase.getInstance().getReference();

        // inserting data + update trick
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mText = editText.getText().toString();
                //myRef.child("message").push().child("no").setValue(mText);
                number = ++number;
                myRef.child("abc").child(String.valueOf(number)).setValue(mText);
                Logger.d("hello %s", "world");
                //update trick
                //myRef.child("abc").child("2").setValue("arafat");
            }
        });

        // reading data
        readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        //String value = dataSnapshot.getValue(String.class);
                        String value = (String) dataSnapshot.child("abc").child("4").getValue();
                        Log.d(TAG, "onDataChange: " + value);
                        Logger.d(dataSnapshot.toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.d(TAG, "onCancelled: " + databaseError.toException());
                    }
                });
            }
        });

        // delete data
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.child("abc").child("no").removeValue();
            }
        });

        //registration
        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });
    }
}
