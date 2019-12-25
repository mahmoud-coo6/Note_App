package com.example.android.noteapp;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyFirebaseController {

    static FirebaseAuth mFirebaseAuth;
    //   static FirebaseDatabase mFirebaseDatabase ;
    static DatabaseReference mFirebaseDatabase;
    static FirebaseAuth auth;
//   static DatabaseReference mFirebaseDatabase ;

    public static FirebaseUser getCurrentUserId() {
        if (mFirebaseAuth == null)
            mFirebaseAuth = FirebaseAuth.getInstance();

        auth = mFirebaseAuth;
        return mFirebaseAuth.getCurrentUser();
    }

//    public static FirebaseUser getAuthtication(){
//        if (mFirebaseAuth == null)
//            mFirebaseAuth = FirebaseAuth.getInstance();
//
//      return   mFirebaseAuth.get();
//    }


// public static  String getCurrentUserId(){
//        if (mFirebaseAuth == null)
//            mFirebaseAuth = FirebaseAuth.getInstance();
//
//      return   mFirebaseAuth.getCurrentUser().getUid();
//    }

    public static DatabaseReference getDatabaseReference() {

        if (mFirebaseDatabase == null) {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            database.setPersistenceEnabled(true);
            mFirebaseDatabase = database.getReference();
        }

        return mFirebaseDatabase;
    }

}
