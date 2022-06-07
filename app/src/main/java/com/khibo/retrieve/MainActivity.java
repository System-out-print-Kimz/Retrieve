package com.khibo.retrieve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView dispName, dispDept;
    Button takeData;
    private DatabaseReference getGoogleName, getGoogleDept;
    public static final String dbURL= "<Your firebase database url>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dispName = (TextView) findViewById(R.id.showName);
        dispDept = (TextView) findViewById(R.id.showDept);
        takeData = (Button) findViewById(R.id.bringContent);

        getGoogleName = FirebaseDatabase.getInstance(dbURL).getReference().child("Name");
        getGoogleDept = FirebaseDatabase.getInstance(dbURL).getReference().child("Dept");

        takeData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlData();
            }
        });
    }
    public void sqlData(){
        getGoogleName.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String dbName = dataSnapshot.getValue().toString();
                    dispName.setText(dbName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Connection error", Toast.LENGTH_SHORT).show();
            }
        });

        getGoogleDept.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    String dbDept = dataSnapshot.getValue().toString();
                    dispDept.setText(dbDept);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Connection error", Toast.LENGTH_SHORT).show();
            }
        });

        /*dispName.setText("Place holder Name");
        dispDept.setText("Place holder Dept");
        Toast.makeText(this, "This is temporary", Toast.LENGTH_SHORT).show();*/
    }
}