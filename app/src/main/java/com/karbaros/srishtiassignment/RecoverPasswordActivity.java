package com.karbaros.srishtiassignment;

import android.app.Activity;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.karbaros.srishtiassignment.databinding.ActivityRecoverPasswordBinding;

public class RecoverPasswordActivity extends Activity {
    ActivityRecoverPasswordBinding recoverPasswordBinding;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recoverPasswordBinding = DataBindingUtil.setContentView(this, R.layout.activity_recover_password);

        sharedPreferences = getSharedPreferences("SrishtiLogin", MODE_PRIVATE);
        phone = sharedPreferences.getString("phone", null);

        recoverPasswordBinding.btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               editor = sharedPreferences.edit();
                editor.putString("phone", recoverPasswordBinding.etConfrmPhone.getText().toString());
                editor.commit();
                finish();
            }
        });

        recoverPasswordBinding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recoverPasswordBinding.etConfrmPhone.getText().toString().equals(phone)){
                    recoverPasswordBinding.etConfrmPhone.setError("");
                    recoverPasswordBinding.etConfrmPhone.setVisibility(View.INVISIBLE);
                    recoverPasswordBinding.btnSubmit.setVisibility(View.INVISIBLE);
                    recoverPasswordBinding.etNewPassword.setVisibility(View.VISIBLE);
                    recoverPasswordBinding.btnReset.setVisibility(View.VISIBLE);
                }else {
                    recoverPasswordBinding.etConfrmPhone.setError("Invalid Phone");

                }
            }
        });
    }
}
