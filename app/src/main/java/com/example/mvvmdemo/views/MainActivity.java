package com.example.mvvmdemo.views;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mvvmdemo.R;
import com.example.mvvmdemo.models.GetUsers;
import com.example.mvvmdemo.viewmodels.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel mMainActivityViewModel;
    private ImageView mProfileImage;
    private TextView mName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initial method call
        initView();
        //get and set data on view, method call
        getSetDataOnView();
    }

    public void initView()
    {
        mProfileImage = findViewById(R.id.ivUserProfile);
        mName = findViewById(R.id.tvUserName);
        //view model
        mMainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        //api call using view model
        mMainActivityViewModel.queryRepo();
    }

    public void getSetDataOnView()
    {
        mMainActivityViewModel.getUser().observe(this, new Observer<GetUsers>() {
            @Override
            public void onChanged(GetUsers user) {
                mName.setText(user.getData().get(0).getName().getUserName());
                Log.e("MainActivity","---UserName----"+user);
            }
        });
    }
}
