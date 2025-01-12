package com.example.projprob;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FBmodule {
    MainActivity gameActivity;

    public FBmodule(MainActivity gameActivity) {
        this.gameActivity = gameActivity;
    }

    public FBmodule(SettingsActivity settingsActivity) {
    }

    public void ChangeSizeInFirebase(String size) {
        // Updates the size value in Firebase
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference sizeReference = firebaseDatabase.getReference("size");
        sizeReference.setValue(size); // Saves the new size value
    }

    public void ChangeBackgroundColorInFirebase(String color) {
        // Updates the background color in Firebase
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference colorReference = firebaseDatabase.getReference("backgroundColor");
        colorReference.setValue(color); // Saves the new background color value
    }
}
