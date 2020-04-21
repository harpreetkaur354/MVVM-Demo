package com.example.mvvmdemo.repositories;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmdemo.models.GetUsers;
import com.example.mvvmdemo.networks.GetDataService;
import com.example.mvvmdemo.networks.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private static UserRepository instance;
    private MutableLiveData<GetUsers> mGetUsers = new MutableLiveData<>();

    //get repo instance
    public static UserRepository getInstance()
    {
        if(instance == null)
        {
            instance = new UserRepository();
        }
        return instance;
    }

    //api call method
    public LiveData<GetUsers> getUser()
    {
        return getUserAPICall();

    }

    /*Create handle for the RetrofitInstance interface*/
    private GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
    //get users api
    private LiveData<GetUsers> getUserAPICall(){
        Call<GetUsers> call = service.getAllUsers("2");
        call.enqueue(new Callback<GetUsers>() {
            @Override
            public void onResponse(@NonNull Call<GetUsers> call,@NonNull Response<GetUsers> response) {
                if (response.code()==200){
                    //post response values
                    mGetUsers.postValue(response.body());
                    Log.e("UserRepo","Data---"+mGetUsers.getValue());
                }
            }
            @Override
            public void onFailure(@NonNull Call<GetUsers> call,@NonNull Throwable t) {
                //post null values
                mGetUsers.postValue(null);
            }
        });
        return mGetUsers;
    }
}
