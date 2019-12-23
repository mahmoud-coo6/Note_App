package com.example.android.noteapp;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyFirebaseController {

   static FirebaseAuth  mFirebaseAuth;
   static FirebaseDatabase mFirebaseDatabase ;
//   static DatabaseReference mFirebaseDatabase ;

    public static   String getCurrentUserId(){
        if (mFirebaseAuth == null)
            mFirebaseAuth = FirebaseAuth.getInstance();

      return   mFirebaseAuth.getCurrentUser().getUid();
    }

    public static DatabaseReference getDatabaseReference (){
        if (mFirebaseDatabase == null) {
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mFirebaseDatabase .setPersistenceEnabled(true);
        }
       return  mFirebaseDatabase.getReference();
    }

}
