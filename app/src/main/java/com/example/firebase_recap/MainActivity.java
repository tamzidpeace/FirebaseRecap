package com.example.firebase_recap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

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
        editText = findViewById(R.id.editText);

        myRef = FirebaseDatabase.getInstance().getReference();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mText = editText.getText().toString();
                //myRef.child("message").push().child("no").setValue(mText);
                number = ++number;
                myRef.child("abc").child(String.valueOf(number)).setValue(mText);
            }
        });
    }


}
