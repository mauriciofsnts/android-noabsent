package com.noabsent.activity;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.noabsent.R;
import com.noabsent.beans.Course;
import com.noabsent.dao.CourseDAO;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;


public class PresenceSuccess extends AppCompatActivity {

    Button buttonBack;
    TextView textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_presence_success);

        buttonBack = (Button)findViewById(R.id.buttonBack);
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            CourseDAO courseDAO = new CourseDAO();
            Course course = courseDAO.getCourseByName(extras.getString("course"));


            if (course != null){

                textView3 = findViewById(R.id.textView3);

                Calendar rightNow = Calendar.getInstance();
                String horario = rightNow.get(Calendar.HOUR_OF_DAY) + ":" + rightNow.get(Calendar.MINUTE);
                String dia = rightNow.get(Calendar.DAY_OF_MONTH) + "/" + rightNow.get(Calendar.MONTH) + "/" + rightNow.get(Calendar.YEAR);

                textView3.setText("Sua presença em " + course.getName() +
                        " foi registrada em " + dia +
                        " às " + horario);
            }

        }


        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PresenceSuccess.this, RegisterPresence.class));
            }
        });

        // localizacao TextView
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        else {
            try {

                LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

                LocationListener locationListener = new LocationListener() {
                    @Override
                    public void onLocationChanged(@NonNull Location location) {
                        TextView txtLatitude = findViewById(R.id.txtLatitude);
                        TextView txtLongitude = findViewById(R.id.txtLongitude);

                        txtLatitude.setText("Latitude: " + location.getLatitude());
                        txtLongitude.setText("Longitude: " + location.getLongitude());

                    }
                };

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, locationListener);

            }
            catch (SecurityException ex ) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
            }
        }

    }


}