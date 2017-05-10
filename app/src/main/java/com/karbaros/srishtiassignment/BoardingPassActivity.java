package com.karbaros.srishtiassignment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.karbaros.srishtiassignment.databinding.ActivityBoardingPassBinding;

public class BoardingPassActivity extends AppCompatActivity {

    ActivityBoardingPassBinding boardingPassBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boardingPassBinding = DataBindingUtil.setContentView(this, R.layout.activity_boarding_pass);
    }
}
