package com.example.project02;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.DialogInterface;
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
    private EditText username_main_editText;
    private EditText password_main_editText;
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



        //private void getDatabase(){
            mTrelpDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME).allowMainThreadQueries().build().TrelpDAO();
        //}

        signup_main_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                //builder.setCancelable(true);
                builder.setTitle("Select what kind of user: ");

               builder.setPositiveButton("Normal", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                      Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);

                       startActivity(intent);
                   }

               });
                builder.setNegativeButton("Seller", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, SellerSignUp.class);

                        startActivity(intent);
                    }

                });

            AlertDialog dialog = builder.create();
            dialog.show();
            }

        });

//        login_main_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startApp();
//            }
//        });

    }
    public static Intent sIntent(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        return intent;

    }
    public void startApp(){
        Intent intent = new Intent(getApplicationContext(), normalUser.class);
        startActivity(intent);
    }

    private void login(){
        username_main_editText = findViewById(R.id.username_main);
        password_main_editText = findViewById(R.id.password_main);

        login_main_button = findViewById(R.id.login_button_main);

        login_main_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startApp();
            }
        });

    }







}