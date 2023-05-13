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
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.project02.DB.AppDataBase;
import com.example.project02.DB.TrelpDAO;

import java.util.List;

public class normalUser extends AppCompatActivity  {
    private Button reviewButton;
//    private static final String USER_ID_KEY = " com.example.project02.userIdKey";
//    private static final String PREFERENCES_KEY = " com.example.project02.PREFENCES_KEY";
    private SharedPreferences mPreferences = null;
    public User mUser;

    private int mUserId = -1;

    private TrelpDAO mTrelpDAO;

    private Button reviewB;

    private EditText searchbar;
    private String search;
    private Button searchButton;









    public static Intent sIntent(Context context){
        Intent intent = new Intent(context, normalUser.class);
        return intent;

    }




    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mTrelpDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build().TrelpDAO();

        setContentView(R.layout.activity_normal_user);

        reviewB = findViewById(R.id.normal_review_button);

        searchButton = findViewById(R.id.seachButtonNormal);


        leavingReview();
        searching();

    }

    private void searching(){
        searchbar = findViewById(R.id.normal_search_editText);
        search = searchbar.getText().toString();
        List<User> sellers = mTrelpDAO.getSellerUsers();
        for(User user: sellers){
            if(user.getTruckname() != null && user.getTruckname().equals(search)){
                searchButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), sellerViewablePage.class );
                        startActivity(intent);
//                        return;
                    }
                });

            }
//            else{
//                Toast.makeText(this, "No Food truck found!", Toast.LENGTH_SHORT).show();
//
//            }

        }
//        Toast.makeText(this, "No Food truck found!", Toast.LENGTH_SHORT).show();
    }

    private void leavingReview(){
        reviewB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(normalUser.this, reviewB );

                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                MenuItem item = popupMenu.getMenu().findItem(R.id.item1);
                List<User> addsellers = mTrelpDAO.getSellerUsers();

               int i = 1;
               for(User addseller: addsellers){
//                   item = popupMenu.getMenu().findItem(R.id.item1 + i);
//                   item.setTitle(addseller.getTruckname().toString());
//
//
//                     item = popupMenu.getMenu().add(0, R.id.item1 + i, i, addseller.getTruckname());
                   popupMenu.getMenu().add(0, R.id.item1 + i, i, addseller.getTruckname());

                   i++;

               }
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.item1:
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });


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

