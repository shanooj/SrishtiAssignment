package com.karbaros.srishtiassignment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.karbaros.srishtiassignment.databinding.ActivityBoardingPassBinding;

public class BoardingPassActivity extends AppCompatActivity {

    ActivityBoardingPassBinding boardingPassBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boardingPassBinding = DataBindingUtil.setContentView(this, R.layout.activity_boarding_pass);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        menu.findItem(R.id.miLocation).setTitle("Bangalore");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.miChangePassword:
                Intent settingIntent = new Intent(getBaseContext(), ChangePasswordActivity.class);
                startActivity(settingIntent);
                break;
            case R.id.miProfile:
                Intent aboutUsIntent = new Intent(getBaseContext(), ProfileActivity.class);
                startActivity(aboutUsIntent);
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
