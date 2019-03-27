package com.example.firebase_recap;

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

public class MainActivity extends AppCompatActivity {

    //constant
    private static final String TAG = "MainActivity";

    //member variable
    private EditText editText;
    String mText;
    DatabaseReference myRef;
    int number = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text = findViewById(R.id.textView);
        Button saveBtn = findViewById(R.id.button);
        Button readBtn = findViewById(R.id.button2);
        editText = findViewById(R.id.editText);

        myRef = FirebaseDatabase.getInstance().getReference();

        // inserting data
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mText = editText.getText().toString();
                //myRef.child("message").push().child("no").setValue(mText);
                number = ++number;
                myRef.child("abc").child(String.valueOf(number)).setValue(mText);
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
                        String value = (String) dataSnapshot.child("abc").child("no").getValue();
                        Log.d(TAG, "onDataChange: " + value);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.d(TAG, "onCancelled: " + databaseError.toException());
                    }
                });
            }
        });
    }
}
