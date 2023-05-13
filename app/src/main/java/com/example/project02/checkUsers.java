package com.example.project02;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.project02.DB.AppDataBase;
import com.example.project02.DB.TrelpDAO;

import java.util.List;

public class checkUsers extends AppCompatActivity {
    private Button viewUsers;
    private List<User> mUserList;


    private TrelpDAO mTrelpDAO;


    public static Intent sIntent(Context context){
        Intent intent = new Intent(context, checkUsers.class);
        return intent;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mTrelpDAO = Room.databaseBuilder(this, AppDataBase.class, AppDataBase.DATABASE_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build().TrelpDAO();

        setContentView(R.layout.activity_admin_user);
        display();
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
}
