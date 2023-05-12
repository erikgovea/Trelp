package com.example.project02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.project02.DB.AppDataBase;
import com.example.project02.DB.TrelpDAO;

import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    public EditText usernameSignup;
    public EditText passwordSignup;
    public EditText passwordConfirmSignup;
    public Button signupButtonNormal;
    private TrelpDAO mTrelpDAO;
    private String username;
    private String password;
    private String confirmpassword;

    public User mUser;
    List<User> users;



    public void signUp(){
        usernameSignup = findViewById(R.id.signupNormalUsrnameText);
        passwordSignup = findViewById(R.id.sellerNPasswordText);
        passwordConfirmSignup = findViewById(R.id.confPasswordNormalText);

        signupButtonNormal = findViewById(R.id.normalSignupButton);

//        username = usernameSignup.getText().toString();
//        password = passwordSignup.getText().toString();
//        confirmpassword = passwordConfirmSignup.getText().toString();
//
//        User normalUser = new User(false, false, username, password);
//
//        mTrelpDAO.insert(normalUser);
//        users = mTrelpDAO.getAllUsers();

        signupButtonNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: check if paasswords match, check if username is taken
                username = usernameSignup.getText().toString();
                password = passwordSignup.getText().toString();
                confirmpassword = passwordConfirmSignup.getText().toString();

                User normalUser = new User(false, false, username, password);

                mTrelpDAO.insert(normalUser);
                users = mTrelpDAO.getAllUsers();
                Intent intent = new Intent(getApplicationContext(), normalUser.class);
                startActivity(intent);
            }
        });



    }




















    public static Intent sIntent(Context context){
        Intent intent = new Intent(context, SignUpActivity.class);
        return intent;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mTrelpDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build().TrelpDAO();

        setContentView(R.layout.activity_signup);
        signUp();
    }

}
