package com.example.project02;

import static com.example.project02.MainActivity.USER_ID_KEY;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.project02.DB.AppDataBase;
import com.example.project02.DB.TrelpDAO;

import java.util.List;

public class SellerSignUp  extends AppCompatActivity {
    public EditText usernameSignup;
    public EditText passwordSignup;
    public EditText passwordConfirmSignup;
    public Button signupButtonSEller;
    private TrelpDAO mTrelpDAO;
    private String username;
    private String password;
    private String confirmpassword;

    public User mUser;
    List<User> users;
    private int mUserId = -1;
    private String truckname;
    private String desc;
    private EditText FTN;
    private EditText DescText;

    private EditText imageBox;
    private String image;








    private void signUp(){
        usernameSignup = findViewById(R.id.sellerUsernameText);
        passwordSignup = findViewById(R.id.sellerSPasswordText);
        passwordConfirmSignup = findViewById(R.id.confPasswordSellerText);


        signupButtonSEller = findViewById(R.id.SellerSignupButton);
        FTN = findViewById(R.id.FtruckNameText);
        DescText = findViewById(R.id.descSellerText);
        imageBox = findViewById(R.id.imagetext);



        signupButtonSEller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: check if paasswords match, check if username is taken
                username = usernameSignup.getText().toString();
                password = passwordSignup.getText().toString();
                confirmpassword = passwordConfirmSignup.getText().toString();
                truckname = FTN.getText().toString();
                desc = DescText.getText().toString();
                image = imageBox.getText().toString();


                User sellerUser = new User(false, true, username, password, false, truckname, desc, image);

                mTrelpDAO.insert(sellerUser);
                mUser = mTrelpDAO.getUserByUsername(username);

                mUserId= mUser.getUserId();
                Intent intent = new Intent(getApplicationContext(), sellerPage.class);
                intent.putExtra(USER_ID_KEY, mUserId );
                startActivity(intent);


            }
        });

    }
    public static Intent sIntent(Context context){
        Intent intent = new Intent(context, SellerSignUp.class);
        return intent;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mTrelpDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build().TrelpDAO();

        setContentView(R.layout.activity_signupseller);
        signUp();
    }
}
