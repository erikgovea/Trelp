package com.example.project02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SellerSignUp  extends AppCompatActivity {
    public static Intent sIntent(Context context){
        Intent intent = new Intent(context, SellerSignUp.class);
        return intent;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupseller);

    }
}