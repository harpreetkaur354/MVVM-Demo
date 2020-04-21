package com.example.mvvmdemo.networks;

import com.example.mvvmdemo.models.GetUsers;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetDataService {
    @GET("users/{userId}/getResource")
    Call<GetUsers> getAllUsers(@Path(value = "userId") String val);
}
