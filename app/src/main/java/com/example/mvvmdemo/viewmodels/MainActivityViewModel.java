package com.example.mvvmdemo.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmdemo.models.GetUsers;
import com.example.mvvmdemo.repositories.UserRepository;

public class MainActivityViewModel extends ViewModel {
    private LiveData<GetUsers> user;
    private UserRepository userRepo;

    public void queryRepo()
    {
        //get user repo
        userRepo = UserRepository.getInstance();
        this.user = userRepo.getUser();
    }

    //get users
    public LiveData<GetUsers> getUser()
    {
        return user;
    }
}
