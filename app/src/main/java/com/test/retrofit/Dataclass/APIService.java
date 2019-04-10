package com.test.retrofit.Dataclass;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface APIService {


//    @FormUrlEncoded

    @GET("/api/users?")
    Call<UserList> doGetUserList(@Query("page") String page);

}