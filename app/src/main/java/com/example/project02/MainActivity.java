package com.example.project02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.project02.DB.AppDataBase;
import com.example.project02.DB.TrelpDAO;
import com.example.project02.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    EditText username_main_editText;
    EditText password_main_editText;
    Button login_main_button;
    Button signup_main_button;

    TrelpDAO mTrelpDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        username_main_editText = binding.usernameMain;
        password_main_editText = binding.passwordMain;
        login_main_button = binding.loginButtonMain;
        signup_main_button = binding.signupButtonMain;




        mTrelpDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME).allowMainThreadQueries().build().TrelpDAO();




        login_main_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startApp();
            }
        });

    }
    public static Intent sIntent(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        return intent;

    }
    public void startApp(){
        Intent intent = new Intent(getApplicationContext(), normalUser.class);
        startActivity(intent);
    }






}