package com.example.wk01hw02solo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class landing_page extends AppCompatActivity {
    private TextView textViewResult;
    Context screen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        TextView t = findViewById(R.id.welcome);
        int id_holder = Integer.parseInt(getIntent().getStringExtra("username"));
        t.setText("welcome userid: "+getIntent().getStringExtra("username"));

        screen = this.getApplicationContext();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("Code: "+response.code());
                    return;
                }

                List<Post> posts = response.body();
                TextView post_text = findViewById(R.id.response);
                for(Post post : posts){
                    String content = "";
                    if (post.getUserId()==id_holder) {
                        content = content + "ID: " + post.getId() + "\n";
                        content = content + "User ID: " + post.getUserId() + "\n";
                        content = content + "Title: " + post.getTitle() + "\n";
                        content = content + "Text: " + post.getText() + "\n\n";

                    }
                    post_text.append(content);
                    Log.d("parent",post_text.getParent().toString());

                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}