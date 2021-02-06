package com.example.wk01hw02solo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
class creds{
    String username;
    String password;
    int userid;
    public creds(String username,String password,int userid){
        this.username = username;
        this.password = password;
        this.userid = userid;
    }

}
public class MainActivity extends AppCompatActivity {

    private TextView username;
    private TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        username = findViewById(R.id.username);
        password = findViewById(R.id.Password);
        Button log_in = findViewById(R.id.login);
        log_in.setOnClickListener(v1->{
            TextView t = findViewById(R.id.login_info);
            String success = signin(username.getText().toString(),password.getText().toString());
            if (success.contains("success")){
                Intent intent = new Intent(this.getApplicationContext(),landing_page.class);
                success = success.replace("success","");
                intent.putExtra("username",success);
                startActivity(intent);
            }else{
                password.setTextColor(Color.BLACK);
                username.setTextColor(Color.BLACK);
                if(success.equals("wrong password")){
                    password.setTextColor(Color.RED);
                }else{
                    username.setTextColor(Color.RED);
                }

            }
        });

    }

    public static String signin(String Username,String Password){
        ArrayList<creds> user_list = new ArrayList<creds>();
        user_list.add(new creds("test","1234",1));
        user_list.add(new creds("test1","12345",2));
        user_list.add(new creds("test2","12346",3));

        for(creds c : user_list){
            if(c.username.equals(Username) && c.password.equals(Password)){
                Log.d("flag","Successlogin");
                if(true)
                return "success"+Integer.toString(c.userid);
            }else if (c.username.equals(Username)){
                return "wrong password";
            }
        }

        return "wrong username";
    }
}