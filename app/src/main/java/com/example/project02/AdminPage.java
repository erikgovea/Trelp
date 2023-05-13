package com.example.project02;

import static com.example.project02.MainActivity.PREFERENCES_KEY;
import static com.example.project02.MainActivity.USER_ID_KEY;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.project02.DB.AppDataBase;
import com.example.project02.DB.TrelpDAO;
import com.example.project02.databinding.ActivityMainBinding;

import java.util.List;

public class AdminPage extends AppCompatActivity {
    private TrelpDAO mTrelpDAO;
    private int mUserId = -1;
    public User mUser;
    private SharedPreferences mPreferences = null;



    private Button viewUsers;
    private List<User> mUserList;

    private TextView display;
    public static Intent sIntent(Context context){
        Intent intent = new Intent(context, AdminPage.class);
        return intent;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mTrelpDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build().TrelpDAO();

        setContentView(R.layout.activity_admin_user);

        CheckUsers();
    }

    private void CheckUsers(){
        viewUsers = findViewById(R.id.viewUsersButton);

        viewUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                display();

//                mUserId= mUser.getUserId();
                Intent intent = new Intent(getApplicationContext(), checkUsers.class);
                intent.putExtra(USER_ID_KEY, mUserId );
                startActivity(intent);
            }
        });


    }
    private void display(){
        mUserList = mTrelpDAO.getAllUsers();
        if(! mUserList.isEmpty()){
            StringBuilder sb = new StringBuilder();
            for(User user: mUserList){
                sb.append(user.toString());
            }
             viewUsers.setText(sb.toString());
        }else{
            viewUsers.setText(R.string.no_users);
        }
    }
    private void logout(){

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setMessage("Logout?");
        alertBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clearUserFromIntent();
                clearUserFromPref();
                mUserId = -1;
                checkForUser();
            }
        });

        alertBuilder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertBuilder.create().show();
    }
    @Override
    public  boolean onOptionsItemSelected(@NonNull MenuItem item){

        switch (item.getItemId()){
            case R.id.logoutbutton:
                logout();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
//        return true;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.topbutton,menu);
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Intent temp = getIntent();
        mUserId = temp.getIntExtra(USER_ID_KEY, - 1);
        mUser = mTrelpDAO.getUserById(mUserId);
        if(mUser != null){

            MenuItem item = menu.findItem(R.id.logoutbutton);
            item.setTitle(mUser.getUsername());
        }else{
            MenuItem item = menu.findItem(R.id.logoutbutton);
            item.setTitle("erro: user null");
        }

        return super.onPrepareOptionsMenu(menu);
    }


    private void clearUserFromPref() {
        addUserToPreference(-1);
    }

    private void addUserToPreference(int userId) {
        if(mPreferences == null){
            getPrefs();
        }
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(USER_ID_KEY, userId);
    }
    private void getPrefs() {
        mPreferences = this.getSharedPreferences(PREFERENCES_KEY,Context.MODE_PRIVATE);
    }
    private void clearUserFromIntent(){
        getIntent().putExtra(USER_ID_KEY, -1);
    }



    private void checkForUser() {

        Intent temp = getIntent();
        mUserId = temp.getIntExtra(USER_ID_KEY, - 1);
        //do we have a user in the preferences
        if(mUserId != -1){
            return;
        }

        if(mPreferences == null){
            getPrefs();
        }
        mUserId = mPreferences.getInt(USER_ID_KEY, -1);



        if(mUserId != -1){
            return;
        }


        Intent intent = MainActivity.intentFactory(getApplicationContext(), mUserId);
        startActivity(intent);


    }


}
