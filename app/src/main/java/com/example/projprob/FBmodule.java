package com.example.projprob;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FBmodule {
    MainActivity gameActivity;

    public FBmodule(MainActivity gameActivity) {
        this.gameActivity = gameActivity;

        // Initializes Firebase and listens for changes in the "size" node
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference Reference = firebaseDatabase.getReference("size");

        Reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String str = snapshot.getValue(String.class); // Retrieves the size from Firebase
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handles any errors during the data fetch
            }
        });
    }

    public void ChangeSizeInFirebase(String size) {
        // Updates the size value in Firebase
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference Reference = firebaseDatabase.getReference("size");
        Reference.setValue(size); // Saves the new size value
    }
}
