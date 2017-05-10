package com.karbaros.srishtiassignment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.karbaros.srishtiassignment.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    ActivitySignUpBinding signUpBinding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor spEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);

        sharedPreferences = getSharedPreferences("SrishtiLogin", MODE_PRIVATE);
        spEditor = sharedPreferences.edit();
        signUpBinding.btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (inputValidation()) {
            spEditor.putString("userName", signUpBinding.etName.getText().toString());
            spEditor.putString("dob", signUpBinding.etDob.getText().toString());
            spEditor.putString("phone", signUpBinding.etPhone.getText().toString());
            spEditor.putString("email", signUpBinding.etEmailId.getText().toString());
            spEditor.putString("password", signUpBinding.etPassword.getText().toString());
            spEditor.commit();

            Snackbar.make(signUpBinding.rootLayout,"Registered",Snackbar.LENGTH_LONG).show();
            Intent loginIntent = new Intent(this,SignInActivity.class);
            startActivity(loginIntent);
        }
    }

    private boolean inputValidation() {
        boolean name, dob, phone, email, password, repassword;

        if (signUpBinding.etName.getText().toString().equals("")) {
            signUpBinding.tilName.setErrorEnabled(true);
            signUpBinding.tilName.setError("Enter Name");
            name = false;
        } else {
            signUpBinding.tilName.setErrorEnabled(false);
            signUpBinding.tilName.setError("");
            name = true;
        }

        if (signUpBinding.etDob.getText().toString().equals("")) {
            signUpBinding.tilDob.setErrorEnabled(true);
            signUpBinding.tilDob.setError("Enter Date Of Birth");
            dob = false;
        } else {
            signUpBinding.tilDob.setErrorEnabled(false);
            signUpBinding.tilDob.setError("");
            dob = true;
        }

        if (signUpBinding.etPhone.getText().toString().length() < 10) {
            signUpBinding.tilNumber.setErrorEnabled(true);
            signUpBinding.tilNumber.setError("Enter Valid Number");
            phone = false;
        } else {
            signUpBinding.tilNumber.setErrorEnabled(false);
            signUpBinding.tilNumber.setError("");
            phone = true;
        }

        if (!isValidEmail(signUpBinding.etEmailId.getText().toString())) {
            signUpBinding.tilEmailId.setErrorEnabled(true);
            signUpBinding.tilEmailId.setError("Enter Valid Email");
            email = false;
        } else {
            signUpBinding.tilEmailId.setErrorEnabled(false);
            signUpBinding.tilEmailId.setError("");
            email = true;
        }

        if (signUpBinding.etPassword.getText().toString().equals("")) {
            signUpBinding.tilPassword.setErrorEnabled(true);
            signUpBinding.tilPassword.setError("Enter Password");
            password = false;
        } else {
            signUpBinding.tilPassword.setErrorEnabled(false);
            signUpBinding.tilPassword.setError("");
            password = true;
        }


        if (signUpBinding.etRePassword.getText().toString().equals(signUpBinding.etPassword.getText().toString())) {
            signUpBinding.tilRePassword.setErrorEnabled(false);
            signUpBinding.tilRePassword.setError("");
            repassword = true;
        } else {
            signUpBinding.tilRePassword.setErrorEnabled(true);
            signUpBinding.tilRePassword.setError("Re-Enter Password");
            repassword = true;
        }
        if (name && dob && phone && email && password && repassword)
            return true;
        else
            return false;
    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
