package com.karbaros.srishtiassignment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.karbaros.srishtiassignment.databinding.ActivityBoardingPassBinding;
import com.karbaros.srishtiassignment.model.BoardingPass;

public class BoardingPassActivity extends AppCompatActivity {

    ActivityBoardingPassBinding boardingPassBinding;
    private DatabaseReference mDatabase;
    private DatabaseReference mRefBoardingPass;
    BoardingPass boardingPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boardingPassBinding = DataBindingUtil.setContentView(this, R.layout.activity_boarding_pass);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mRefBoardingPass = mDatabase.child("boardingPass");
        /*String userId = mRefBoardingPass.push().getKey();
        BoardingPass pass = new BoardingPass("10:40 AM", "00:15", "02:10 PM", "02:40 PM", "LAX", "58B", "SFO", "24A", "2");
        mRefBoardingPass.child(userId).setValue(pass);*/

        mRefBoardingPass.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot boardingSnapshot : dataSnapshot.getChildren()) {
                    boardingPass = boardingSnapshot.getValue(BoardingPass.class);
                    Toast.makeText(getBaseContext(), boardingPass.getArrival(), Toast.LENGTH_SHORT).show();
                }


            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("error", databaseError.toString());

            }
        });


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
