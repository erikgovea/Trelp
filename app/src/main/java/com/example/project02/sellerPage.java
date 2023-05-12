package com.example.project02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class sellerPage extends AppCompatActivity {
    public static Intent sIntent(Context context){
        Intent intent = new Intent(context, sellerPage.class);
        return intent;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_user);

    }
}
