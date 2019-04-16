package com.android.firebasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase myDatabase;
    DatabaseReference myRef;
    EditText reference;
    EditText value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reference = findViewById(R.id.key_field);
        value = findViewById(R.id.value_field);

        myDatabase = FirebaseDatabase.getInstance();
    }

    public void writeToDatabase(View v)
    {
        myRef = myDatabase.getReference(reference.getText().toString());
        myRef.setValue(value.getText().toString());
    }

    public void readFromDatabase(View v)
    {
        myRef = myDatabase.getReference(reference.getText().toString());

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again whenever data at this location is updated.
                String loadedData = dataSnapshot.getValue(String.class);
                value.setText(loadedData.toString());
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(MainActivity.this, "Error loading Firebase", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
