package com.karbaros.srishtiassignment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.karbaros.srishtiassignment.databinding.ActivitySignInBinding;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    ActivitySignInBinding signInBinding;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signInBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in);

        signInBinding.btnLogin.setOnClickListener(this);
        signInBinding.tvNewUser.setOnClickListener(this);
        signInBinding.tvForgetPassword.setOnClickListener(this);
        sharedPreferences = getSharedPreferences("SrishtiLogin", MODE_PRIVATE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                doLogin();
                break;
            case R.id.tvForgetPassword:
                Intent recoverIntent = new Intent(this, RecoverPasswordActivity.class);
                startActivity(recoverIntent);
                break;
            case R.id.tvNewUser:
                Intent newUserIntent = new Intent(this, SignUpActivity.class);
                startActivity(newUserIntent);
                break;
        }

    }


    private void doLogin() {

        String username = sharedPreferences.getString("userName", null);
        String password = sharedPreferences.getString("password", null);
        String phone = sharedPreferences.getString("phone", null);


        if (username.equals(signInBinding.etUserName.getText().toString()) ||
                phone.equals(signInBinding.etUserName.getText().toString())) {
            signInBinding.textInputLayoutName.setErrorEnabled(false);
            signInBinding.textInputLayoutName.setError("");
            if (password.equals(signInBinding.etPassword.getText().toString())) {
                Intent homeIntent = new Intent(this,BoardingPassActivity.class);
                startActivity(homeIntent);
                finish();
                signInBinding.textInputLayoutPassword.setErrorEnabled(false);
                signInBinding.textInputLayoutPassword.setError("");
            } else {
                signInBinding.textInputLayoutPassword.setErrorEnabled(true);
                signInBinding.textInputLayoutPassword.setError("Invalid Password");
            }

        } else {
            signInBinding.textInputLayoutName.setErrorEnabled(true);
            signInBinding.textInputLayoutName.setError("Invalid User Name");
        }
    }
}