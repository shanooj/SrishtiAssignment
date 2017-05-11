package com.karbaros.srishtiassignment;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.karbaros.srishtiassignment.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {
    ActivityProfileBinding binding;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        preferences = getSharedPreferences("SrishtiLogin", MODE_PRIVATE);

        binding.tvName.setText(preferences.getString("userName",null));
        binding.tvPhone.setText(preferences.getString("phone",null));
        binding.tvEmailId.setText(preferences.getString("email",null));
        binding.tvDob.setText(preferences.getString("dob",null));

    }
}
