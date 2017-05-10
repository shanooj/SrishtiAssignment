package com.karbaros.srishtiassignment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.karbaros.srishtiassignment.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        mainBinding.btnSignUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        
    }
}
