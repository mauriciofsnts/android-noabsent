package com.noabsent.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.noabsent.R;
import com.noabsent.beans.Course;
import com.noabsent.dao.CourseDAO;

import java.text.ParseException;


public class RegisterPresence extends AppCompatActivity implements LocationListener {

    AutoCompleteTextView autoCompleteTextView;
    Button buttonPresence;
    TextView courseTextHourInfo;
    TextView txtLatitude;
    TextView txtLongitude;

    private final int GPS_REQUEST = 100;
    private LocationManager locationManager;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register_presence);

        CourseDAO courseDAO = new CourseDAO();
        buttonPresence = (Button) findViewById(R.id.buttonPresence);
        autoCompleteTextView = findViewById(R.id.dropdownSelectClass);
        courseTextHourInfo = (TextView) findViewById(R.id.courseTextHourInfo);
        txtLatitude = findViewById(R.id.txtLatitude);
        txtLongitude = findViewById(R.id.txtLongitude);


        String[] option = {"FUNDAMENTOS DE INTELIGÊNCIA ARTIFICIAL", "TRABALHO DE GRADUAÇÃO INTERDISCIPLINAR I", "LINGUAGENS FORMAIS E AUTÔMATOS", "PROGRAMAÇÃO PARA DISPOSITIVOS MÓVEIS"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.option, option);

        autoCompleteTextView.setAdapter(arrayAdapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Course course = courseDAO.getCourseByName(option[position]);


                courseTextHourInfo.setText(course.getDayOfWeek() + " , " + course.getStartTime() + " - " + course.getEndTime());
            }
        });

        buttonPresence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Editable selectedAutoComplete = autoCompleteTextView.getText();

                if (selectedAutoComplete != null && selectedAutoComplete.length() > 0) {
                    Course course = courseDAO.getCourseByName(selectedAutoComplete.toString());

                    try {
                        if (courseDAO.checkCourse(course)) {

                            String faculLat = "-23.536";
                            String faculLong = "-46.560";
                            String userLat = (String) txtLatitude.getText();
                            String userLong = (String) txtLongitude.getText();

                            if (userLat != null && userLong != null) {
                                if (userLat.substring(10, 17).equals(faculLat) && userLong.substring(11, 18).equals(faculLong)) {
                                    Intent intent = new Intent(RegisterPresence.this, PresenceSuccess.class);
                                    intent.putExtra("course", course.getName());
                                    startActivity(intent);
                                } else {
                                    Context context = getApplicationContext();
                                    CharSequence text = "Você não está na faculdade";
                                    int duration = Toast.LENGTH_SHORT;

                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.show();
                                }
                            }


                        } else {
                            Context context = getApplicationContext();
                            CharSequence text = "Não há aula nesse horário";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                } else {
                    Context context = getApplicationContext();
                    CharSequence text = "Escolha uma matéria";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

            }
        });


        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, GPS_REQUEST);
        }else{
            getLocation();
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case GPS_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    getLocation();
                }
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    1000, 0, this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onLocationChanged(@NonNull Location location) {
        buttonPresence.setEnabled(true);
        txtLatitude.setText("Latitude: " + location.getLatitude());
        txtLongitude.setText("Longitude: " + location.getLongitude());
    }
}