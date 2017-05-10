package com.karbaros.srishtiassignment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.karbaros.srishtiassignment.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding mainBinding;
    SharedPreferences sharedPreferences;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        sharedPreferences = getSharedPreferences("SrishtiLogin", MODE_PRIVATE);
        userName = sharedPreferences.getString("userName",null);

        if (userName == null){
            mainBinding.btnSignUp.setText(R.string.sign_up);
        } else {
            mainBinding.btnSignUp.setText(R.string.sign_in);
        }

        mainBinding.btnSignUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        if (mainBinding.btnSignUp.getText().equals(getResources().getString(R.string.sign_in))){
            Intent signinIntent = new Intent(this,SignInActivity.class);
            startActivity(signinIntent);

        } else {
            Intent signupIntent = new Intent(this,SignUpActivity.class);
            startActivity(signupIntent);

        }

    }
}
