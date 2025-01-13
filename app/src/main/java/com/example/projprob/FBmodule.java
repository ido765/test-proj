package com.example.trivia2024inclass;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Fbmodule {
    private MainActivity mainActivity;

    public Fbmodule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        // Listener for background color
        DatabaseReference colorReference = firebaseDatabase.getReference("color");
        colorReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String color = snapshot.getValue(String.class);
                if (color != null) {
                    mainActivity.setBackgroundColor(color);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors if needed
            }
        });

        // Listener for grid size
        DatabaseReference sizeReference = firebaseDatabase.getReference("size");
        sizeReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String size = snapshot.getValue(String.class);
                if (size != null) {
                    mainActivity.setGridSize(size);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors if needed
            }
        });
    }

    public void changeBackgroundColorInFirebase(String color) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference colorReference = firebaseDatabase.getReference("color");
        colorReference.setValue(color);
    }

    public void changeSizeInFirebase(String size) {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference sizeReference = firebaseDatabase.getReference("size");
        sizeReference.setValue(size);
    }
}
