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
import android.widget.Toast;

import com.example.project02.DB.AppDataBase;
import com.example.project02.DB.TrelpDAO;
import com.example.project02.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    static final String USER_ID_KEY = " com.example.project02.userIdKey";
    static final String PREFERENCES_KEY = " com.example.project02.PREFENCES_KEY";
    ActivityMainBinding binding;
    private EditText username_main_editText;
    private EditText password_main_editText;
    Button login_main_button;
    Button signup_main_button;

    private TrelpDAO mTrelpDAO;
    String username;
    String password;
    public User mUser;
    List<User> users;
    private int mUserId = -1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTrelpDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build().TrelpDAO();
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        signup_main_button = binding.signupButtonMain;
        User adminUser1 = new User(true, false, "Erik", "Trelp", false, null, null);
        User sellerUser1 = new User(false, true, "El_Huarache", "csumb", false, "El Huarache", "mexican");
       mTrelpDAO.insert(adminUser1, sellerUser1);
        users = mTrelpDAO.getAllUsers();
        login();




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
        mUserId= mUser.getUserId();
        Intent intent = new Intent(getApplicationContext(), normalUser.class);
        intent.putExtra(USER_ID_KEY, mUserId );
        startActivity(intent);
    }

    private void login(){
        username_main_editText = findViewById(R.id.username_main);
        password_main_editText = findViewById(R.id.password_main);

        login_main_button = findViewById(R.id.login_button_main);

        login_main_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = username_main_editText.getText().toString();
                password = password_main_editText.getText().toString();
                if(checkForUsersInDB()){
                    if(! mUser.getPassword().equals(password)){
                        Toast.makeText(MainActivity.this, "wrong password", Toast.LENGTH_SHORT).show();
                    }else{
                        if(mUser.isAdmin()){
                           Intent intent = new Intent(getApplicationContext(), AdminPage.class);
                           startActivity(intent);

                        }else if(mUser.isSeller()){
                            Intent intent = new Intent(getApplicationContext(), sellerPage.class);
                            startActivity(intent);

                        }
                        else if(username.equals("")  || password.equals("")){
                            Toast.makeText(MainActivity.this, "no info", Toast.LENGTH_SHORT).show();

                        }else{
                            startApp();

                        }
                    }
                }

            }
        });

    }

    public boolean checkForUsersInDB(){

        mUser = mTrelpDAO.getUserByUsername(username);
        if(mUser == null){
            Toast.makeText(this, " no user " + username + " found", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void wireup(){

    }

    private void logout(){
//        AlertDialog.
    }


    public static Intent intentFactory(Context context,int userId){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(USER_ID_KEY, userId);
        return intent;
    }








}