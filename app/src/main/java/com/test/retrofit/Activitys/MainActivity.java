package com.test.retrofit.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.test.retrofit.Adapter.UserAdapter;
import com.test.retrofit.Apiurl.APIUrl;
import com.test.retrofit.Dataclass.APIService;
import com.test.retrofit.Dataclass.UserList;
import com.test.retrofit.Dataclass.Users;
import com.test.retrofit.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewUsers;
    private RecyclerView.Adapter adapter;

    List<UserList.Datum> datumList = new ArrayList<>();

    int pages = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewUsers = findViewById(R.id.recyclerViewUsers);
        recyclerViewUsers.setHasFixedSize(true);
        recyclerViewUsers.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        adapter = new UserAdapter(datumList, MainActivity.this);
        recyclerViewUsers.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        callservice(retrofit);
    }

    public void  callservice(final Retrofit retrofit)
    {
        APIService service = retrofit.create(APIService.class);
        Call<UserList> call = service.doGetUserList(String.valueOf(pages));

        call.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {

                UserList userList = response.body();
                assert userList != null;
                datumList.addAll(userList.data);

                adapter.notifyDataSetChanged();
                pages++;
                if (pages <= 3) {
                    callservice(retrofit);
                }

            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {

            }
        });
    }


}
