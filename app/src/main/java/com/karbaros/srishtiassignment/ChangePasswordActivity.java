package com.karbaros.srishtiassignment;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.karbaros.srishtiassignment.databinding.ActivityChangePasswordBinding;

public class ChangePasswordActivity extends AppCompatActivity {
    ActivityChangePasswordBinding binding;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_change_password);

        preferences = getSharedPreferences("SrishtiLogin", MODE_PRIVATE);
        editor = preferences.edit();
        password = preferences.getString("password", null);

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String p = binding.etCPassword.getText().toString();
                if (validateInput()) {
                    if (binding.etCPassword.getText().toString().equals(password)) {
                        editor.putString("password", p);
                        editor.commit();
                        finish();
                    } else {
                        Snackbar.make(binding.rootLyout,"Wrong Password Try Again ",Snackbar.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    public boolean validateInput() {
        boolean flag1, flag2, flag3;
        if (binding.etCPassword.getText().toString().equals("")) {
            binding.tilCurrentPassword.setErrorEnabled(true);
            binding.tilCurrentPassword.setError("Enter Current Password");
            flag1 = false;
        } else {
            binding.tilCurrentPassword.setErrorEnabled(false);
            binding.tilCurrentPassword.setError("");
            flag1 = true;
        }

        if (binding.etNewPassword.getText().toString().equals("")) {
            binding.tilNewPassword.setErrorEnabled(true);
            binding.tilNewPassword.setError("Enter New Password");
            flag2 = false;
        } else {
            binding.tilNewPassword.setErrorEnabled(false);
            binding.tilNewPassword.setError("");
            flag2 = true;
        }

        if (binding.etRePassword.getText().toString().equals(binding.etNewPassword.getText().toString())) {
            binding.tilConfirmPassword.setErrorEnabled(false);
            binding.tilConfirmPassword.setError("");
            flag3 = true;
        } else {
            binding.tilConfirmPassword.setErrorEnabled(true);
            binding.tilConfirmPassword.setError("Wrong Password");
            flag3 = false;
        }

        if (flag1 && flag2 && flag3)
            return true;
        else
            return false;
    }
}
