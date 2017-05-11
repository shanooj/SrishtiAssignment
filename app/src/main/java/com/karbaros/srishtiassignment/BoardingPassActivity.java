package com.karbaros.srishtiassignment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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
    SharedPreferences sharedPreferences;
    LocationManager mLocationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boardingPassBinding = DataBindingUtil.setContentView(this, R.layout.activity_boarding_pass);
        sharedPreferences = getSharedPreferences("SrishtiLogin", MODE_PRIVATE);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mRefBoardingPass = mDatabase.child("boardingPass");
       /* String userId = mRefBoardingPass.push().getKey();
        BoardingPass pass = new BoardingPass("10:40 AM", "00:15", "02:10 PM", "02:40 PM", "LAX", "58B", "SFO", "24A", "2","UDA 2465");
        mRefBoardingPass.child(userId).setValue(pass);*/
        boardingPassBinding.textView3.setText("MR. " + sharedPreferences.getString("userName", null));
        mRefBoardingPass.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot boardingSnapshot : dataSnapshot.getChildren()) {
                    boardingPass = boardingSnapshot.getValue(BoardingPass.class);

                    boardingPassBinding.tvArrivalTime.setText(boardingPass.getArrival());
                    boardingPassBinding.tvBordingTime.setText(boardingPass.getBoardingTime());
                    boardingPassBinding.tvBordingInTime.setText(boardingPass.getBoardingIn());
                    boardingPassBinding.tvDepartureTime.setText(boardingPass.getDeparture());
                    boardingPassBinding.tvTerminal.setText(boardingPass.getTerminal());
                    boardingPassBinding.tvGate.setText(boardingPass.getGate());
                    boardingPassBinding.tvSeat.setText(boardingPass.getSeat());
                    boardingPassBinding.tvOrigin.setText(boardingPass.getOrigin());
                    boardingPassBinding.tvDestination.setText(boardingPass.getDestination());
                    boardingPassBinding.tvFlightCode.setText(boardingPass.getPlane());

                }


            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("error", databaseError.toString());

            }
        });

        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000,
                50, mLocationListener);


    }

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            //your code here

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
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
            case R.id.miLocation:
                Intent locationIntent = new Intent(getBaseContext(), MapActivity.class);
                startActivity(locationIntent);
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
