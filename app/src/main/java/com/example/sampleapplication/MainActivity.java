package com.example.sampleapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    private APIInterface apiInterface;
    RecyclerView recyclerView;
    UsersAdapter usersAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        textView = findViewById(R.id.text_1);
        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
        usersAdapter = new UsersAdapter();

        /**
         * only for logging
         * */
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        /**
         *
         */

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        apiInterface = retrofit.create(APIInterface.class);

//        getPosts();
        getComments();

    }

    private void getComments() {
        //passing value
        Call<List<Comments>> call = apiInterface.getComments(3);

        //Passing Url
//        Call<List<Comments>> call = apiInterface.getComments(3);

        call.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Result: " + response.code());
                    return;
                }
                Log.e("MainClass","Success: "+response.body().toString());
                List<Comments> commentList = response.body();

                //for normal textview

//                for (Comments comments : commentList) {
//                    String content = "";
//                    content = content + "postId: " + comments.getPostId() + "\n";
//                    content = content + "id: " + comments.getId() + "\n";
//                    content = content + "name: " + comments.getName() + "\n";
//                    content = content + "email: " + comments.getEmail() + "\n";
//                    content = content + "Body: " + comments.getBody() + "\n\n";
//
//                    textView.append(content);
//                }

                //recyclerview
                usersAdapter.setdata(commentList);
                recyclerView.setAdapter(usersAdapter);

            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {
                textView.setText(t.getMessage());

            }
        });
    }

    // normal passing the arguments (without using maps)
 /*   private void getPosts() {

        Call<List<POJO>> call = apiInterface.getPosts(new Integer[]{4,5,6}, "id","desc");
        call.enqueue(new Callback<List<POJO>>() {
            @Override
            public void onResponse(Call<List<POJO>> call, Response<List<POJO>> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Result: " + response.code());
                    return;
                }
                List<POJO> data = response.body();
                for (POJO pojo : data) {
                    String content = "";
                    content = content + "ID: " + pojo.getId() + "\n";
                    content = content + "UserID: " + pojo.getUserId() + "\n";
                    content = content + "Title: " + pojo.getTitle() + "\n";
                    content = content + "Body: " +pojo.getBody() + "\n\n";

                    textView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<POJO>> call, Throwable t) {
                textView.setText(t.getMessage());

            }
        });
    }*/

    // Using HashMaps for passing values
    private void getPosts() {

        Map<String, String> params = new HashMap<>();
        params.put("userId", "6");
        params.put("_sort", "id");
        params.put("_order", "desc");

        Call<List<POJO>> call = apiInterface.getPosts(params);
        call.enqueue(new Callback<List<POJO>>() {
            @Override
            public void onResponse(Call<List<POJO>> call, Response<List<POJO>> response) {
                if (!response.isSuccessful()) {
                    textView.setText("Result: " + response.code());
                    return;
                }
                List<POJO> data = response.body();
                for (POJO pojo : data) {
                    String content = "";
                    content = content + "ID: " + pojo.getId() + "\n";
                    content = content + "UserID: " + pojo.getUserId() + "\n";
                    content = content + "Title: " + pojo.getTitle() + "\n";
                    content = content + "Body: " + pojo.getBody() + "\n\n";

                    textView.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<POJO>> call, Throwable t) {
                textView.setText(t.getMessage());

            }
        });
    }
} 